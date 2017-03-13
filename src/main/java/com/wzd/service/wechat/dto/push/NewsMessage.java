package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 发送客服图文消息
 */
@SuppressWarnings("serial")
public class NewsMessage implements Serializable {

	/**
	 * 普通微信用户的openid
	 */
	private String touser;

	/**
	 * 消息类型，news
	 */
	private String msgtype;

	/**
	 * 图文消息对象
	 */
	private News news;

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

	public News getNews() {
		if (null == news)
			news = new News();
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
