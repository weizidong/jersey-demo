//package com.wzd.service.wechat.sns;
//
//import java.io.IOException;
//import java.text.MessageFormat;
//
//import com.lifesense.framework.util.StringUtil;
//import com.lifesense.framework.util.jackson.JacksonUtils;
//import com.lifesense.framework.util.log.LoggerAdapter;
//import com.lifesense.framework.util.log.LoggerAdapterFacory;
//import com.lifesense.healthcenter.service.wechat.WeixinAPI;
//import com.lifesense.healthcenter.service.wechat.sns.dto.AccessToken;
//import com.lifesense.healthcenter.service.wechat.sns.dto.UserInfo;
//import com.lifesense.healthcenter.utils.HttpClientManager;
//
//public class WechartSnsService {
//
//	private static LoggerAdapter log = LoggerAdapterFacory.getLogger(WechartSnsService.class);
//
//	/**
//	 * 获取重定向微信的授权url
//	 * 
//	 * @param appid
//	 * @param redirectUri
//	 * @param state
//	 * @return
//	 */
//	public static String getAuthorizeUrl(String appid, String redirectUri, String state) {
//
//		String stateUrl = StringUtil.isEmpty(state) ? "" : "&state=" + state;
//
//		String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base" + stateUrl + "#wechat_redirect";
//
//		return getCodeUrl;
//	}
//
//	/**
//	 * 通过授权获取的code获取accessToken
//	 * 
//	 * @param appid
//	 * @param secret
//	 * @param code
//	 * @return
//	 */
//	public static AccessToken getAccessTokenByCode(String appid, String secret, String code) {
//
//		String body = HttpClientManager.get(MessageFormat.format(WeixinAPI.GET_ACCESS_TOKEN_URL, appid, secret, code));
//
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("===========get authorize access token  start ====================");
//		sb.append("appid = " + appid);
//		sb.append("secret = " + secret);
//		sb.append("code = " + code);
//		sb.append("retult = " + body);
//		sb.append("===========get authorize access token  end   ====================");
//
//		log.debug(sb.toString());
//
//		if (StringUtil.isEmpty(body)) {
//			return null;
//		}
//
//		AccessToken accessToken = null;
//
//		try {
//			accessToken = JacksonUtils.toBean(body, AccessToken.class);
//		} catch (IOException e) {
//			throw new RuntimeException("通过code获取accessToken，返回结果将json转换为" + AccessToken.class + "发生异常。", e);
//		}
//
//		if (accessToken == null) {
//			throw new RuntimeException("通过code获取accessToken，返回结果为null");
//		}
//
//		// 调用接口返回错误
//		if (accessToken.getErrcode() != null) {
//			throw new RuntimeException("通过code获取accessToken，返回结果为错误" + accessToken);
//		}
//
//		log.debug(accessToken.toString());
//
//		// 网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止
//		return accessToken;
//
//	}
//
//	/**
//	 * 通过access_token和openid拉取用户信息
//	 * 
//	 * @param accessToken
//	 * @param openId
//	 * @return
//	 */
//	public static UserInfo getUserInfo(String accessToken, String openId) {
//
//		String body = HttpClientManager.get(MessageFormat.format(WeixinAPI.GET_USERINFO_URL, accessToken, openId));
//
//		if (StringUtil.isEmpty(body)) {
//			throw new RuntimeException("通过access_token和openid拉取用户信息，返回结果为null");
//		}
//		UserInfo userInfo = null;
//
//		try {
//			userInfo = JacksonUtils.toBean(body, UserInfo.class);
//		} catch (IOException e) {
//			throw new RuntimeException("通过access_token和openid拉取用户信息，返回结果将json转换为" + UserInfo.class + "发生异常。", e);
//		}
//
//		if (userInfo == null) {
//			throw new RuntimeException("通过access_token和openid拉取用户信息，返回结果为null");
//		}
//
//		// 调用接口返回错误
//		if (userInfo.getErrcode() != null) {
//			throw new RuntimeException("通过access_token和openid拉取用户信息，返回结果为错误, errcode = " + userInfo.getErrcode() + ",errmsg = " + userInfo.getErrmsg());
//		}
//
//		log.debug(userInfo.toString());
//
//		return userInfo;
//	}
//}
