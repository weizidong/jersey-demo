package com.lifesense.healthcenter.service.wechat.dto.device_text.request;

import java.io.Serializable;

/**
 * 1.设备通过微信发消息给第三方 2.绑定/解绑设备消息
 */
@SuppressWarnings("serial")
public class DeviceMessage implements Serializable {

	/**
	 * 消息创建时间
	 */
	private long CreateTime;

	/**
	 * 消息类型：device_text
	 */
	private String MsgType;

	/**
	 * 事件：bind-绑定设备，unbind-解除绑定
	 */
	private String Event;

	/**
	 * 设备类型，目前即为微信公众号ID
	 */
	private String DeviceType;

	/**
	 * 设备ID
	 */
	private String DeviceID;

	/**
	 * 消息内容，BASE64编码
	 */
	private String Content;

	/**
	 * 普通微信用户的openid
	 */
	private String OpenID;

	/**
	 * 消息id
	 */
	private long MsgId;
	
	/**
	 * 微信客户端生成的ID
	 */
	private String SessionID;

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getOpenID() {
		return OpenID;
	}

	public void setOpenID(String openID) {
		OpenID = openID;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

}
