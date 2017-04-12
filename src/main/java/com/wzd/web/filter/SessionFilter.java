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
import org.springframework.stereotype.Component;

import com.wzd.model.enums.APPType;
import com.wzd.model.enums.ViewPage;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.utils.Configs;
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
@Component("SessionFilter")
public class SessionFilter implements Filter {
	private static final Logger log = LogManager.getLogger(SessionFilter.class);
	@Autowired
	private QyWxService qyService;
	@Autowired
	private FwWxService fwService;
	@Autowired
	private SessionUtil sessionUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 请求
		String requestUrl = httpRequest.getRequestURI().substring(1);
		// 请求来源
		String appType = request.getParameter("appType");
		// 回调授权code
		String code = request.getParameter("code");
		// 微信回调不检测
		if (requestUrl.startsWith("rest/wechat/")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		if (httpRequest.getQueryString() != null) {
			requestUrl += "?" + httpRequest.getQueryString();
		}
		log.debug("请求：" + requestUrl);
		log.debug("appType：" + appType);
		log.debug("code：" + code);
		Session session = null;
		// debug模式
		if (SessionUtil.isDebug(httpRequest)) {
			log.debug("开启debug模式！");
			session = sessionUtil.openDebug(httpRequest);
		} else {
			session = SessionUtil.getSession(httpRequest);
		}
		// 授权成功，回调,
		if (!StringUtil.isEmpty(code) && session == null) {
			// 授权用时
			Long time = Long.parseLong(request.getParameter("state"));
			log.debug("微信授权成功!\t用时：" + (System.currentTimeMillis() - time) + "ms");
			// 企业号换取Token
			if (appType.equals(APPType.企业号.getValue())) {
				session = qyService.getUserInfo(code);
			}
			// 管理平台换取Token
			if (appType.equals(APPType.二维码登录.getValue())) {
				session = qyService.getUserInfo(requestUrl.substring(requestUrl.lastIndexOf("/") + 1), code);
				if (session == null) {
					httpResponse.sendRedirect("/" + ViewPage.loginError);
				} else {
					httpResponse.sendRedirect("/" + ViewPage.loginSuccess);
				}
				return;
			}
			// 服务号换取Token
			if (appType.equals(APPType.服务号.getValue())) {
				session = fwService.getUserInfo(code);
			}
			// 保存会话信息
			SessionUtil.saveSession(session, httpRequest, httpResponse);
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		// 企业号未授权
		if ((APPType.企业号.getValue().equals(appType) && session == null) || APPType.二维码登录.getValue().equals(appType)) {
			authorize(QyAPI.AUTHORIZE, Configs.sCorpID, requestUrl, httpResponse);
			return;
		}
		// 服务号未授权
		if (APPType.服务号.getValue().equals(appType) && session == null) {
			authorize(FwAPI.AUTHORIZE, Configs.bAppid, requestUrl, httpResponse);
			return;
		}
		// 网站主页创建Session
		if (APPType.网站主页.getValue().equals(appType) && session == null) {
			session = SessionUtil.generateSession(appType, null, null, null);
			SessionUtil.saveSession(session, httpRequest, httpResponse);
		}
		// 管理平台未登录
		if (APPType.管理平台.getValue().equals(appType) && session == null && !requestUrl.startsWith("rest/admin/login")) {
			throw new WebException(ResponseCode.未登录, "未登录");
		}
		// 加载静态文件
		if (StringUtil.isEmpty(requestUrl) || requestUrl.endsWith(".html") || requestUrl.startsWith("view/")) {
			log.debug("加载静态页面。。。");
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		// 非网站主页需要检测数据签名
		// if (!APPType.网站主页.getValue().equals(appType) && session != null) {
		// SessionUtil.checkSignature(session, httpRequest, httpResponse);
		// }

		// 管理平台需要检测Session超时
		if (APPType.管理平台.getValue().equals(appType) && session != null) {
			SessionUtil.checkTs(session, httpRequest, httpResponse);
		}
		// 更新Session
		if (session != null) {
			SessionUtil.updateSession(session, httpRequest);
		}
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
		String getCodeUrl = MessageFormat.format(path, appid, URLEncoder.encode(Configs.hostname + redirectUri, "utf-8"), String.valueOf(System.currentTimeMillis()));
		log.debug("授权:" + getCodeUrl);
		httpResponse.sendRedirect(getCodeUrl);
	}
}
