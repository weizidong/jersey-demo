package com.wzd.web.dto.session;

import java.io.Serializable;

import com.wzd.model.entity.User;

/**
 * 用户登录状态
 * 
 * @author weizidong
 *
 */
@SuppressWarnings("serial")
public class Session implements Serializable {
	// sessionId
	private String sessionId;
	// accessToken
	private String accessToken;
	// 时间戳
	private long ts;
	// 登录用户
	private User user;

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

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", accessToken=" + accessToken + ", ts=" + ts + ", user=" + user
				+ "]";
	}

}
