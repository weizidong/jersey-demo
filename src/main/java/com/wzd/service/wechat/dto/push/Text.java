package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 文本消息对象
 */
@SuppressWarnings("serial")
public class Text implements Serializable {

	/**
	 * 文本消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
