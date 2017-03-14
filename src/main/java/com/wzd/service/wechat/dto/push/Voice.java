package com.wzd.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 语音消息对象
 * 
 * @author lyb
 * 
 */
@SuppressWarnings("serial")
public class Voice implements Serializable {

	/**
	 * 语音消息内容
	 */
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}


}
