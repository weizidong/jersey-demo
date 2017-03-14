package com.wzd.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 发送客服文本消息
 */
@SuppressWarnings("serial")
public class TextMessage implements Serializable {

	/**
	 * 普通微信用户的openid or 集合
	 * 修改前是String 修改后Object 应对String 跟 String[]
	 * 2014-12-23 22:04:59
	 * @author wangguang
	 */
	private Object touser;
	
	/**
	 * 消息类型，text
	 */
	private String msgtype;

	/**
	 * 文本消息对象
	 */
	private Text text;

	public Object getTouser() {
		return touser;
	}

	public void setTouser(Object touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Text getText() {
		if (null == text)
			text = new Text();
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	
}
