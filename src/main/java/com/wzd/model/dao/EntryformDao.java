package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Entryform;
import com.wzd.model.mapper.EntryformMapper;

/**
 * 报名表数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class EntryformDao {
	@Autowired
	private EntryformMapper mapper;

	/**
	 * 报名
	 */
	public void entry(Entryform entryform) {
		mapper.insertSelective(entryform);
	}
}
