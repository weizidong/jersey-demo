package com.wzd.web.dto.session;

import com.lifesense.communityhospital.model.entity.authority.Account;
import com.lifesense.communityhospital.model.entity.authority.enums.RoleType;
import com.lifesense.communityhospital.model.entity.doctor.CommunityDoctor;

/**
 * 登录状态信息
 */
public class LoginSession {
	// sessionId
	private String sessionId;
	// doctor
	private CommunityDoctor doctor;
	// 管理员
	private Account account;
	// 权限
	private int[] role = { RoleType.普通医生.getCode() };
	// accessToken
	private String accessToken;
	// autologin
	private boolean autologin = false;
	// 时间戳
	private long ts;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public CommunityDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(CommunityDoctor doctor) {
		this.doctor = doctor;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public boolean isAutologin() {
		return autologin;
	}

	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int[] getRole() {
		return role;
	}

	public void setRole(int[] role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginSession [sessionId=" + sessionId + ", doctor=" + doctor + ", account=" + account + ", role=" + role + ", accessToken=" + accessToken
				+ ", autologin=" + autologin + ", ts=" + ts + "]";
	}

}
