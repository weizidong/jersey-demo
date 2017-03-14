package com.wzd.service.wechat.dto.message.response;

import java.io.Serializable;

import com.wzd.service.wechat.dto.push.Image;



/**
 * 图片消息回复
 */
@SuppressWarnings("serial")
public class ImageMessageReply implements Serializable {

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
	 * 图片
	 */
	private Image image;
	
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

	public Image getImage() {
		
		if (null == image) {
			image = new Image();
		}
		
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
