package com.lifesense.healthcenter.service.wechat.dto;

import java.io.Serializable;

/**
 * 来自Wechat端的消息(所有字段)
 */
@SuppressWarnings("serial")
public class WechatMessageReply implements Serializable {

	/**
	 * 微信公众号ID
	 */
	private String toUserName;

	/**
	 * 发送方微信号(OpenID)
	 */
	private String fromUserName;

	/**
	 * 消息创建时间
	 */
	private long createTime;

	/** ==========文本消息内容 **/
	private String content;
	/**
	 * 消息类型
	 */
	private String msgType;

	/**
	 * 事件
	 */
	private String event;
	/**
	 * 微信客户端生成的id
	 */
	private String sessionID;

	/**
	 * 设备类型，目前即为微信公众号ID
	 */
	private String deviceType;

	/**
	 * 设备ID
	 */
	private String deviceID;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

}
