package com.wzd.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.utils.Configs;
import com.wzd.utils.IpUtil;
import com.wzd.utils.SessionUtil;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.session.Session;

/**
 * Session校验
 * 
 * @author WeiZiDong
 *
 */
public class SessionFilter implements Filter {
	private static final Logger log = LogManager.getLogger(SessionFilter.class);
	@Autowired
	private QyWxService qyService;
	@Autowired
	private FwWxService fwService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 请求
		String requestUrl = httpRequest.getRequestURI();
		// 域名
		String hostname = IpUtil.getServerHostname(httpRequest);
		// 参数
		String queryString = httpRequest.getQueryString();
		if (!StringUtil.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		// app识别标志,服务号：S,企业号：B
		String appType = request.getParameter("appType");
		// 回调授权code
		String code = request.getParameter("code");
		log.debug("域名：" + hostname);
		log.debug("请求：" + requestUrl);
		log.debug("appType：" + appType);
		log.debug("code：" + code);
		// 授权成功，回调,
		if (!StringUtil.isEmpty(code) && !StringUtil.isEmpty(appType)) {
			// 授权用时
			Long authorizeTs = System.currentTimeMillis() - Long.valueOf(request.getParameter("state"));
			log.debug("微信授权成功!\t用时：" + authorizeTs);
			// 换取Token
			Session session = null;
			// 企业号换取Token
			if (appType.equals("S")) {
				session = qyService.getUserInfo(code);
			}
			// 服务号换取Token
			if (appType.equals("B")) {
				session = fwService.getUserInfo(code);
			}
			// 保存会话信息
			if (session != null) {
				SessionUtil.saveSession(session, httpRequest, httpResponse);
			}
			return;
		}
		// 获取session
		Session session = SessionUtil.getSession(httpRequest);
		if ("S".equals(appType) && session == null) {// 企业号未授权
			authorize(QyAPI.AUTHORIZE, Configs.sCorpID, hostname + requestUrl, httpResponse);
			return;
		}
		if ("B".equals(appType) && session == null) {// 服务号未授权
			authorize(FwAPI.AUTHORIZE_URL, Configs.bAppid, hostname + requestUrl, httpResponse);
			return;
		}
		// 加载静态文件
		if (requestUrl.startsWith("/view/")) {
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		// 更新Session
		SessionUtil.updateSession(session, httpRequest);
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	/**
	 * 授权
	 */
	private void authorize(String path, String appid, String redirectUri, HttpServletResponse httpResponse) throws IOException {
		String getCodeUrl = MessageFormat.format(path, appid, URLEncoder.encode(redirectUri, "utf-8"), Long.toString(System.currentTimeMillis()));
		log.debug("授权:" + getCodeUrl);
		httpResponse.sendRedirect(getCodeUrl);
	}
}
