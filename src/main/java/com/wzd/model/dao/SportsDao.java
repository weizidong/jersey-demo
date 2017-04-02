package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Sports;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.SportsMapper;
import com.wzd.utils.UUIDUtil;

/**
 * 健身活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class SportsDao {
	@Autowired
	private SportsMapper mapper;

	/**
	 * 创建
	 */
	public Sports create(Sports s) {
		s.setId(UUIDUtil.get());
		s.setCreated(new Date());
		s.setDeleted(DeleteType.未删除);
		s.setStatus(StateType.进行中);
		mapper.insert(s);
		return s;
	}
}
