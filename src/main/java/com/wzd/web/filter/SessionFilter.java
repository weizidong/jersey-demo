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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 请求
		String requestUrl = httpRequest.getRequestURI();
		// 加载静态文件
		if (requestUrl.startsWith("/view/")) {
			request.getRequestDispatcher("/index.html?" + Configs.version).forward(request, response);
			return;
		}
		// 域名
		String hostname = IpUtil.getServerHostname(httpRequest);
		log.debug("域名：" + hostname);
		log.debug("请求：" + requestUrl);
		String debug = request.getParameter("debug");
		if (debug != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		// 参数
		String queryString = httpRequest.getQueryString();
		if (!StringUtil.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		// 回调授权code
		String code = request.getParameter("code");
		// 授权成功，回调,
		if (!StringUtil.isEmpty(code)) {
			// String state = request.getParameter("state");
			// long startAuthorizeTs = 0;
			// try {
			// startAuthorizeTs = Long.valueOf(state);
			// } catch (Exception e) {
			// }
			// long endAuthorizeTs = System.currentTimeMillis();
			// long authorizeTs = endAuthorizeTs - startAuthorizeTs;
			//
			// log.debug("微信授权成功!");
			//
			// AccessToken accessToken =
			// WechartOauthService.getAccessTokenByCode(WechartServiceNoService.getAppid(),
			// WechartServiceNoService.getSecret(), code);
			//
			// if (null == accessToken) {
			// log.debug("通过code换取网页授权access_token失败");
			// failure(httpRequest, httpResponse);
			// return;
			// }
			//
			// long endGetOpenIdTs = System.currentTimeMillis();
			// long getOpenIdTs = endGetOpenIdTs - endAuthorizeTs;
			//
			// log.debug("授权成功-获取微信用户信息成功。" + (System.currentTimeMillis() -
			// startTs));
			//
			// startTs = System.currentTimeMillis();
			//
			// log.debug("微信授权成功-保存会话信息");
			//
			// String sessionId = WxSessionUtil.saveWxSession(accessToken,
			// httpRequest, httpResponse);
			//
			// long saveWxSessionTs = System.currentTimeMillis() -
			// endGetOpenIdTs;
			//
			// log.debug("微信授权成功-保存会话信息成功。" + (System.currentTimeMillis() -
			// startTs));
			//
			// startTs = System.currentTimeMillis();
			//
			// log.debug("微信授权成功-redirect");
			//
			// // MyBatisUtil.commitSession();
			//
			// // wxJSApi不支持h5，将 /view/member/list 重定向为 /view/#/member/list
			// Map<String, String> queryParamMap = new HashMap<String,
			// String>();
			// queryParamMap.put("sessionId", sessionId);
			// queryParamMap.put("authorizeTs", Long.toString(authorizeTs));
			// queryParamMap.put("getOpenIdTs", Long.toString(getOpenIdTs));
			// queryParamMap.put("saveWxSessionTs",
			// Long.toString(saveWxSessionTs));
			//
			// httpResponse.sendRedirect(redirect);
			//
			// log.debug("微信授权成功-redirect成功。" + (System.currentTimeMillis() -
			// startTs));

			return;

		}
		// app识别标志,服务号：S,企业号：B
		String appType = request.getParameter("appType");
		// 获取session
		Session session = SessionUtil.getSession(httpRequest);
		if (appType == null && session == null) { // web端未登录
			throw new WebException(ResponseCode.未授权, "未登录");
		}
		if (appType.equals("S") && session == null) {// 企业号未授权
			authorize(Configs.sCorpID, hostname + requestUrl, httpRequest, httpResponse);
			return;
		}
		if (appType.equals("B") && session == null) {// 服务号未授权
			authorize(Configs.bAppid, hostname + requestUrl, httpRequest, httpResponse);
			return;
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
	 * 
	 * @param appid
	 *            appId
	 * @param redirectUri
	 *            回调地址
	 * @param httpRequest
	 * @param httpResponse
	 * @throws IOException
	 */
	private void authorize(String appid, String redirectUri, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		String getCodeUrl = MessageFormat.format(FwAPI.AUTHORIZE_URL, appid, URLEncoder.encode(redirectUri, "utf-8"),
				Long.toString(System.currentTimeMillis()));
		log.debug("授权:" + getCodeUrl);
		httpResponse.sendRedirect(getCodeUrl);
	}

}
