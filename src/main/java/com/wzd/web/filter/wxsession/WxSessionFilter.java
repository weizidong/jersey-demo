//package com.wzd.web.filter.wxsession;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.lifesense.framework.model.util.MyBatisUtil;
//import com.lifesense.framework.util.StringUtil;
//import com.lifesense.framework.util.log.LoggerAdapter;
//import com.lifesense.framework.util.log.LoggerAdapterFacory;
//import com.lifesense.healthcenter.service.wechat.WechartServiceNoService;
//import com.lifesense.healthcenter.service.wechat.oauth.WechartOauthService;
//import com.lifesense.healthcenter.service.wechat.oauth.dto.AccessToken;
//import com.lifesense.healthcenter.web.utils.IpUtil;
//
///**
// * Servlet Filter implementation class WxSessionFilter
// */
//public class WxSessionFilter implements Filter {
//
//	private static LoggerAdapter log = LoggerAdapterFacory.getLogger(WxSessionFilter.class);
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		try {
//			log.debug("请求地址：" + getRequestUrl(httpRequest));
//
//			String redirect = getRequestUrl(httpRequest);
//
//			long startTs = System.currentTimeMillis();
//
//			if (redirect.endsWith("/view/")) {
//
//				log.debug("/view/跳转=" + redirect);
//
//				request.getRequestDispatcher("/index.html").forward(request, response);
//
//				log.debug("/view/跳转成功。" + (System.currentTimeMillis() - startTs));
//
//				return;
//			}
//
//			String code = request.getParameter("code");
//
//			if (!StringUtil.isEmpty(code)) {// 授权成功，回调,
//
//				String state = request.getParameter("state");
//				long startAuthorizeTs = 0;
//				try {
//					startAuthorizeTs = Long.valueOf(state);
//				} catch (Exception e) {
//				}
//				long endAuthorizeTs = System.currentTimeMillis();
//				long authorizeTs = endAuthorizeTs - startAuthorizeTs;
//
//				log.debug("微信授权成功");
//
//				AccessToken accessToken = WechartOauthService.getAccessTokenByCode(WechartServiceNoService.getAppid(), WechartServiceNoService.getSecret(), code);
//
//				if (null == accessToken) {
//					log.debug("通过code换取网页授权access_token失败");
//					failure(httpRequest, httpResponse);
//					return;
//				}
//				
//				long endGetOpenIdTs = System.currentTimeMillis();
//				long getOpenIdTs = endGetOpenIdTs - endAuthorizeTs;
//
//				log.debug("授权成功-获取微信用户信息成功。" + (System.currentTimeMillis() - startTs));
//
//				startTs = System.currentTimeMillis();
//
//				log.debug("微信授权成功-保存会话信息");
//
//				String sessionId = WxSessionUtil.saveWxSession(accessToken, httpRequest, httpResponse);
//
//				long saveWxSessionTs = System.currentTimeMillis() - endGetOpenIdTs;
//
//				log.debug("微信授权成功-保存会话信息成功。" + (System.currentTimeMillis() - startTs));
//
//				startTs = System.currentTimeMillis();
//
//				log.debug("微信授权成功-redirect");
//
//				// MyBatisUtil.commitSession();
//
//				// wxJSApi不支持h5，将 /view/member/list 重定向为 /view/#/member/list
//				Map<String, String> queryParamMap = new HashMap<String, String>();
//				queryParamMap.put("sessionId", sessionId);
//				queryParamMap.put("authorizeTs", Long.toString(authorizeTs));
//				queryParamMap.put("getOpenIdTs", Long.toString(getOpenIdTs));
//				queryParamMap.put("saveWxSessionTs", Long.toString(saveWxSessionTs));
//
//				redirect(httpRequest, httpResponse, queryParamMap);
//
//				log.debug("微信授权成功-redirect成功。" + (System.currentTimeMillis() - startTs));
//
//				return;
//
//			}
//
//			// 获取用户信息
//
//			log.debug("获取用户信息【缓存】");
//
//			startTs = System.currentTimeMillis();
//
//			WxSession wxSession = null;
//			try {
//				wxSession = WxSessionUtil.getWxSession(httpRequest);
//			} catch (Exception e) {
//				log.error(e);
//				wxSession = null;
//			}
//
//			log.debug("获取用户信息【缓存】成功。" + (System.currentTimeMillis() - startTs));
//
//			// 如果缓存key值为空,获取缓存UserInfo为空,重定向到 微信 授权
//			if (wxSession == null) {
//
//				log.debug("重定向到微信授权");
//
//				// 授权
//				authorize(httpRequest, httpResponse);
//				return;
//
//			}
//
//			if (!redirect.endsWith("/view/")) {
//				redirect(httpRequest, httpResponse, null);
//				return;
//			}
//
//			log.debug("更新用户信息【缓存】");
//
//			startTs = System.currentTimeMillis();
//
//			WxSessionUtil.saveWxSession(wxSession, httpRequest, httpResponse);
//
//			log.debug("更新用户信息【缓存】成功。" + (System.currentTimeMillis() - startTs));
//
//			log.debug("RequestDispatcher...............");
//
//			startTs = System.currentTimeMillis();
//
//			httpRequest.getRequestDispatcher("/index.html").forward(httpRequest, httpResponse);
//
//			log.debug("RequestDispatcher...............。" + (System.currentTimeMillis() - startTs));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			failure(httpRequest, httpResponse);
//		} finally {
//			 MyBatisUtil.closeSession();
//		}
//	}
//
//	@Override
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//
//	private void redirect(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Map<String, String> queryParamMap) throws IOException, ServletException {
//
//		String redirect = getRequestUrl(httpRequest);
//		redirect = redirect.replaceFirst("/view", "/view/#");
//
//		if (queryParamMap != null) {
//
//			boolean isFirst = true;
//			for (String key : queryParamMap.keySet()) {
//
//				if (isFirst && httpRequest.getQueryString() == null) {
//					redirect += "?";
//					isFirst = false;
//				} else {
//					redirect += "&";
//				}
//
//				redirect = redirect + key + "=" + queryParamMap.get(key);
//			}
//			
//		}
//
//		log.debug("redirect=" + redirect);
//		// TODO:这里会耗时？？
//		httpResponse.sendRedirect(redirect);
//	}
//
//	/**
//	 * 获取请求全路径
//	 * 
//	 * @param request
//	 * @return
//	 */
//	private String getRequestUrl(HttpServletRequest request) {
//		String hostname = IpUtil.getServerHostname(request);
//		String requestUrl = hostname + request.getRequestURI().substring(1);
//		String queryString = request.getQueryString();
//		if (!StringUtil.isEmpty(queryString)) {
//			requestUrl += "?" + queryString;
//		}
//		return requestUrl;
//	}
//
//	/**
//	 * 授权
//	 * 
//	 * @param appid
//	 * @param serviceNo
//	 * @param httpRequest
//	 * @param httpResponse
//	 * @throws IOException
//	 */
//	private void authorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
//		String redirectUri = URLEncoder.encode(getRequestUrl(httpRequest), "utf-8");
//
//		log.debug("回调地址:" + redirectUri);
//
//		String getCodeUrl = WechartOauthService.getAuthorizeUrl(WechartServiceNoService.getAppid(), redirectUri, Long.toString(System.currentTimeMillis()));
//
//		httpResponse.sendRedirect(getCodeUrl);
//	}
//
//	private void failure(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
//
//		String requestUrl = getRequestUrl(httpRequest);
//
//		log.debug("请求地址：" + requestUrl);
//
//		httpRequest.getRequestDispatcher("/failure.html").forward(httpRequest, httpResponse);
//
//	}
//
//}
