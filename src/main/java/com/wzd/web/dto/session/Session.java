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
	// 时间戳
	private Long ts;
	// 登录用户
	private Object user;
	// 请求来源
	private String appType;

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

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Override
	public String toString() {
		return "{sessionId:" + sessionId + ", accessToken:" + accessToken + ", ts:" + ts + ", user:" + user + ", appType:" + appType + "}";
	}

}
