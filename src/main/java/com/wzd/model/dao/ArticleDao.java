package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.mapper.ArticleMapper;

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

}
