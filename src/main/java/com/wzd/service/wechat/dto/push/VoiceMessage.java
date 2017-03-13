package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 发送客服语音消息
 * 
 * @author lyb
 * 
 */
@SuppressWarnings("serial")
public class VoiceMessage implements Serializable {

	/**
	 * 普通微信用户的openid
	 */
	private String touser;

	/**
	 * 消息类型，voice
	 */
	private String msgtype;

	/**
	 * 图文消息对象
	 */
	private Voice voice;

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
	

	public Voice getVoice() {
		if (null == this.voice)
			this.voice = new Voice();
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
