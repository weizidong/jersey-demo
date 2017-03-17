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

import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.utils.IpUtil;
import com.wzd.utils.SessionUtil;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;

/**
 * Session校验
 * 
 * @author WeiZiDong
 *
 */
public class SessionFilter implements Filter {
	private static final Logger log = LogManager.getLogger(SessionFilter.class);

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
		log.debug("域名：" + hostname);
		log.debug("请求：" + requestUrl);
		// 加载静态文件
		if (requestUrl.startsWith("/view/")) {
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		String debug = request.getParameter("debug");
		if (debug != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		// 回调授权code
		String code = request.getParameter("code");
		// app识别标志,服务号：S,企业号：B
		String appType = request.getParameter("appType");
		// 授权成功，回调,
		if (!StringUtil.isEmpty(code)) {
			// 授权用时
			Long authorizeTs = System.currentTimeMillis() - Long.valueOf(request.getParameter("state"));
			log.debug("微信授权成功!\t用时：" + authorizeTs);
			// 换取Token
			Token token = null;
			if (appType.equals("S")) {// 企业号换取Token
				token = Token.getAccessTokenByCode(WechartServiceNoService.getAppid(), WechartServiceNoService.getSecret(), code);
			}
			if (appType.equals("B")) {// 服务号换取Token
				token = Token.getAccessTokenByCode(WechartServiceNoService.getAppid(), WechartServiceNoService.getSecret(), code);
			}
			if (null == token) {
				log.debug("通过code换取网页授权access_token失败");
				return;
			}
			// 保存会话信息
			return;
		}
		// 获取session
		Session session = SessionUtil.getSession(httpRequest);
		if (appType.equals("S") && session == null) {// 企业号未授权
			authorize(QyAPI.AUTHORIZE, Configs.sCorpID, hostname + requestUrl, httpResponse);
			return;
		}
		if (appType.equals("B") && session == null) {// 服务号未授权
			authorize(FwAPI.AUTHORIZE_URL, Configs.bAppid, hostname + requestUrl, httpResponse);
			return;
		}
		// 更新Session
		SessionUtil.updateSession(session);
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
