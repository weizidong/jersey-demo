package com.wzd.service.wechat.token;

import java.text.MessageFormat;

import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.utils.EhcacheUtil;
import com.wzd.web.dto.exception.WebException;

/**
 * 获取AccessToken
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Token extends BaseResp {
	// 访问令牌
	private String access_token;
	// 令牌有效时间，单位：秒
	private Long expires_in;
	// 获取令牌时间
	private Long timestamp;
	// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String openid;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return super.toString() + ", access_token=" + access_token + ", expires_in=" + expires_in + ", timestamp=" + timestamp + ", openid=" + openid + "}";
	}

	public static Token get(String path, String appid, String secrect) {
		Token token = (Token) EhcacheUtil.getInstance().get(EhcacheUtil.TOKEN, appid);
		if (token != null && System.currentTimeMillis() - token.getTimestamp() < token.getExpires_in() * 1000) {
			return token;
		}
		token = RestClientUtil.get(MessageFormat.format(path, appid, secrect), Token.class);
		if (token.getErrcode() != null) {
			throw new WebException(token.getErrcode(), token.getErrmsg());
		}
		token.setTimestamp(System.currentTimeMillis());
		EhcacheUtil.getInstance().put(EhcacheUtil.TOKEN, appid, token);
		return token;
	}
}
