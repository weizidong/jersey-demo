package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 发送客服图片消息
 */
@SuppressWarnings("serial")
public class ImageMessage implements Serializable {

	/**
	 * 普通微信用户的openid
	 */
	private String touser;

	/**
	 * 消息类型，image
	 */
	private String msgtype;

	/**
	 * 图片对象
	 */
	private Image image;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Image getImage() {
		if (null == image)
			image = new Image();
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
