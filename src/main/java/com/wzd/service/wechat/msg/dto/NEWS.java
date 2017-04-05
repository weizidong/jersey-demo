package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;
import java.util.List;

/**
 * news消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class NEWS implements Serializable {
	private List<ARTICLE> articles;

	public NEWS() {
		super();
	}

	public NEWS(List<ARTICLE> articles) {
		this.articles = articles;
	}

	public List<ARTICLE> getArticles() {
		return articles;
	}

	public void setArticles(List<ARTICLE> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "[articles=" + articles + "]";
	}

}
