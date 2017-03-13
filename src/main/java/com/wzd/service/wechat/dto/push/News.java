package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;
import java.util.List;

/**
 * 图文消息对象
 */
@SuppressWarnings("serial")
public class News implements Serializable {

	/**
	 * 图文消息列表
	 */
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
