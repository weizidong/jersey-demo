package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Article;
import com.wzd.model.mapper.ArticleMapper;
import com.wzd.utils.UUIDUtil;

/**
 * 文章数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class ArticleDao {
	@Autowired
	private ArticleMapper mapper;

	public Article create(Article a) {
		a.setId(UUIDUtil.get());
		a.setCreated(new Date());
		mapper.insertSelective(a);
		return a;
	}

}
