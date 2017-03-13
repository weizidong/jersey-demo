package com.lifesense.healthcenter.service.wechat.dto.message.response;

import java.io.Serializable;

/**
 * 关注/取消关注事件消息回复
 */
@SuppressWarnings("serial")
public class SubscribeReply implements Serializable {

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
	 * 事件
	 */
	private String event;

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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
