package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Tag;
import com.wzd.model.mapper.TagMapper;

/**
 * 标签分组数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class TagDao {
	@Autowired
	private TagMapper mapper;

	/**
	 * 创建
	 */
	public void create(Tag tag) {
		mapper.insert(tag);
	}

}
