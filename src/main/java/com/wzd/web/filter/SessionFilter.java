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

import com.wzd.model.enums.APPType;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.utils.Configs;
import com.wzd.utils.IpUtil;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;
import com.wzd.web.dto.session.SessionUtil;

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
		// 域名
		String hostname = IpUtil.getServerHostname(httpRequest);
		// 请求
		String requestUrl = httpRequest.getRequestURI().substring(1);
		// 参数
		String queryString = httpRequest.getQueryString();
		if (!StringUtil.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		// 请求来源
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
			Session session = null;
			// 企业号换取Token
			if (appType.equals(APPType.企业号.getValue())) {
				session = qyService.getUserInfo(code);
			}
			// 服务号换取Token
			if (appType.equals(APPType.服务号.getValue())) {
				session = fwService.getUserInfo(code);
			}
			// 保存会话信息
			SessionUtil.saveSession(session, httpRequest, httpResponse);
			return;
		}
		// 获取SessionId
		String SessinId = SessionUtil.getSessionIdByCookie(httpRequest);
		// 企业号未授权
		if (APPType.企业号.getValue().equals(appType) && SessinId == null) {
			authorize(QyAPI.AUTHORIZE, Configs.sCorpID, hostname + requestUrl, httpResponse);
			return;
		}
		// 服务号未授权
		if (APPType.服务号.getValue().equals(appType) && SessinId == null) {
			authorize(FwAPI.AUTHORIZE_URL, Configs.bAppid, hostname + requestUrl, httpResponse);
			return;
		}
		// 管理平台未登录
		if (APPType.管理平台.getValue().equals(appType) && SessinId == null) {
			throw new WebException(ResponseCode.未登录, "未登录");
		}
		Session session;
		// 网站主页创建Session
		if (APPType.网站主页.getValue().equals(appType) && SessinId == null) {
			session = SessionUtil.generateSession(appType, null, null, null);
			SessionUtil.saveSession(session, httpRequest, httpResponse);
		} else {
			session = SessionUtil.getSession(httpRequest);
		}
		// 加载静态文件
		if (requestUrl.startsWith("/view/")) {
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		// 非网站主页需要检测数据签名
		if (!APPType.网站主页.getValue().equals(appType) && session != null && !SessionUtil.isDebug(httpRequest)) {
			SessionUtil.checkSignature(session, httpRequest, httpResponse);
		}
		// 管理平台需要检测Session超时
		if (APPType.管理平台.getValue().equals(appType) && session != null && !SessionUtil.isDebug(httpRequest)) {
			SessionUtil.checkTs(session, httpRequest, httpResponse);
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
