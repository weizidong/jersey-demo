package com.lifesense.healthcenter.service.wechat.dto.device_text.response;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.constants.XmlResp;
import com.lifesense.healthcenter.service.wechat.dto.BaseMsg;

/**
 * 来自Wechat端的消息(所有字段)
 */
public class DeviceTextMsgResp extends BaseMsg {

	public DeviceTextMsgResp() {
		this.setMsgType(MsgType.DEVICE_TEXT);
	}

	/** ==========文本消息内容 **/
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toXml() {
		return XmlResp.buildDeviceText(getFromUserName(), getToUserName(),
				getDeviceType(), getDeviceID(), getContent(), getSessionID());
	}

}
