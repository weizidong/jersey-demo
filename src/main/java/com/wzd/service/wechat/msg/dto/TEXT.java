package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * text消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class TEXT implements Serializable {
	private String content; // 消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）
	private String url; // 文本链接地址

	public TEXT() {
		super();
	}

	public TEXT(String content) {
		this.content = content;
	}

	public TEXT(String content, String url) {
		if (StringUtils.isNotBlank(url)) {
			this.content = "<a href=\"" + url + "\">" + content + "</a>";
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "[content=" + content + ", url=" + url + "]";
	}

}
