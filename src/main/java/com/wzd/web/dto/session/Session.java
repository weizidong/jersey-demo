package com.wzd.web.dto.session;

import java.io.Serializable;

/**
 * 用户登录状态
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Session implements Serializable {
	// sessionId
	private String sessionId;
	// accessToken
	private String accessToken;
	// 微信openId
	private String openId;
	// 时间戳
	private Long ts;
	// 登录用户
	private Object user;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", accessToken=" + accessToken + ", openId=" + openId + ", ts=" + ts
				+ ", user=" + user + "]";
	}

}
