package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

/**
 * text消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class TEXT implements Serializable {
	private String content; // 消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "[content=" + content + "]";
	}

}
