package com.lifesense.healthcenter.service.wechat.dto.message.response;

import java.io.Serializable;

/**
 * 文本消息回复
 */
@SuppressWarnings("serial")
public class TextMessageReply implements Serializable {

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
	
	/**
	 * 消息类型
	 */
	private String msgType;

	/**
	 * 文本消息内容
	 */
	private String content;

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
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
