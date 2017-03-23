package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;
import java.util.List;

/**
 * mpNews消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class MPNEWS implements Serializable {
	private List<ARTICLE> articles;

	public MPNEWS(List<ARTICLE> articles) {
		super();
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
