package com.wzd.service.wechat.dto.device;

import java.io.Serializable;

/**
 * 绑定或解绑用户和设备的请求对象
 */
@SuppressWarnings("serial")
public class BindRequest implements Serializable {

	/**
	 * jsapi的ticket
	 */
	private String ticket;

	/**
	 * 设备ID，字段命名跟微信一致
	 */
	private String device_id;

	/**
	 * 微信用户openid，字段命名跟微信一致
	 */
	private String openid;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
