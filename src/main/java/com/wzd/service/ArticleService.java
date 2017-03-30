package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.ArticleDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Article;
import com.wzd.model.enums.ArticleType;
import com.wzd.model.enums.DeleteType;
import com.wzd.web.param.PageParam;

/**
 * 文章业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public Article create(Article a, Admin user) {
		// TODO 发布文章
		return articleDao.create(a);
	}

	/**
	 * 获取文章列表
	 */
	public PageInfo<Article> find(PageParam param, ArticleType parse, DeleteType parse2, Admin user) {
		// TODO 获取文章列表
		return null;
	}

}
