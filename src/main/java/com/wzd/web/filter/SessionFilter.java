package com.wzd.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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

import com.wzd.service.wechat.WechartServiceNoService;
import com.wzd.service.wechat.WeixinAPI;
import com.wzd.service.wechat.sns.dto.AccessToken;
import com.wzd.utils.IpUtil;
import com.wzd.utils.PropertiesUtil;
import com.wzd.utils.SessionUtil;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.response.ResponseCodeType;
import com.wzd.web.dto.session.Session;

/**
 * Session校验
 * 
 * @author WeiZiDong
 *
 */
public class SessionFilter implements Filter {
	private static final Logger log = LogManager.getLogger(SessionFilter.class);
	public static String PROPERTIES = "configs/jdbc.properties";
	public static String version = "1.0.0";
	public static String S_appID = "";
	public static String S_serviceNo = "";
	public static String S_secret = "";
	public static String B_appID = "";
	public static String B_serviceNo = "";
	public static String B_secret = "";

	static {
		try {
			Map<String, String> propMap = PropertiesUtil.readPropertiesForMap(PROPERTIES);
			version = propMap.get("version");
			S_appID = propMap.get("s.appId");
			S_serviceNo = propMap.get("s.service_no");
			S_secret = propMap.get("s.secret");
			B_appID = propMap.get("b.appId");
			B_serviceNo = propMap.get("b.service_no");
			B_secret = propMap.get("b.secret");
		} catch (IOException e) {
			throw new RuntimeException("初始化服务器配置发生异常。", e);
		} finally {
			log.info("启动成功！版本：" + version);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 请求
		String requestUrl = httpRequest.getRequestURI().substring(1);
		// 加载静态文件
		if (requestUrl.startsWith("/view/")) {
			request.getRequestDispatcher("/index.html?" + version).forward(request, response);
			return;
		}
		// 域名
		String hostname = IpUtil.getServerHostname(httpRequest);
		log.debug("请求：" + requestUrl);
		// 参数
		String queryString = httpRequest.getQueryString();
		if (!StringUtil.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		// 回调授权code
		String code = request.getParameter("code");
		// 授权成功，回调,
		if (!StringUtil.isEmpty(code)) {
			String state = request.getParameter("state");
			long startAuthorizeTs = 0;
			try {
				startAuthorizeTs = Long.valueOf(state);
			} catch (Exception e) {
			}
			long endAuthorizeTs = System.currentTimeMillis();
			long authorizeTs = endAuthorizeTs - startAuthorizeTs;

			log.debug("微信授权成功!");

			AccessToken accessToken = WechartOauthService.getAccessTokenByCode(WechartServiceNoService.getAppid(),
					WechartServiceNoService.getSecret(), code);

			if (null == accessToken) {
				log.debug("通过code换取网页授权access_token失败");
				failure(httpRequest, httpResponse);
				return;
			}

			long endGetOpenIdTs = System.currentTimeMillis();
			long getOpenIdTs = endGetOpenIdTs - endAuthorizeTs;

			log.debug("授权成功-获取微信用户信息成功。" + (System.currentTimeMillis() - startTs));

			startTs = System.currentTimeMillis();

			log.debug("微信授权成功-保存会话信息");

			String sessionId = WxSessionUtil.saveWxSession(accessToken, httpRequest, httpResponse);

			long saveWxSessionTs = System.currentTimeMillis() - endGetOpenIdTs;

			log.debug("微信授权成功-保存会话信息成功。" + (System.currentTimeMillis() - startTs));

			startTs = System.currentTimeMillis();

			log.debug("微信授权成功-redirect");

			// MyBatisUtil.commitSession();

			// wxJSApi不支持h5，将 /view/member/list 重定向为 /view/#/member/list
			Map<String, String> queryParamMap = new HashMap<String, String>();
			queryParamMap.put("sessionId", sessionId);
			queryParamMap.put("authorizeTs", Long.toString(authorizeTs));
			queryParamMap.put("getOpenIdTs", Long.toString(getOpenIdTs));
			queryParamMap.put("saveWxSessionTs", Long.toString(saveWxSessionTs));

			redirect(httpRequest, httpResponse, queryParamMap);

			log.debug("微信授权成功-redirect成功。" + (System.currentTimeMillis() - startTs));

			return;

		}
		// app识别标志,服务号：S,企业号：B
		String appType = request.getParameter("appType");
		// 获取session
		Session session = SessionUtil.getSession(httpRequest);
		if (appType == null && session == null) { // web端未登录
			throw new WebException(ResponseCode.未授权, "未登录");
		}
		if (appType.equals("B") && session == null) {// 企业号未授权
			authorize(B_appID, hostname + requestUrl, httpRequest, httpResponse);
			return;
		}
		if (appType.equals("S") && session == null) {// 服务号未授权
			authorize(B_appID, hostname + requestUrl, httpRequest, httpResponse);
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
		String getCodeUrl = MessageFormat.format(WeixinAPI.AUTHORIZE_URL, appid,
				URLEncoder.encode(redirectUri, "utf-8"), Long.toString(System.currentTimeMillis()));
		log.debug("授权:" + getCodeUrl);
		httpResponse.sendRedirect(getCodeUrl);
	}

}
