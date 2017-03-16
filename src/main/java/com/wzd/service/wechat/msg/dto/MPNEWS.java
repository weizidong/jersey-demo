package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;
import java.util.List;

/**
 * mpnews消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class MPNEWS implements Serializable {
	private List<ARTICLE> articles;

	public List<ARTICLE> getArticles() {
		return articles;
	}

	public void setArticles(List<ARTICLE> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "MPNEWS [articles=" + articles + "]";
	}

}
