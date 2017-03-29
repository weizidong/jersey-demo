package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Activity;
import com.wzd.model.mapper.ActivityMapper;

/**
 * 线下活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class ActivityDao {
	@Autowired
	private ActivityMapper mapper;

	/**
	 * 创建
	 */
	public void create(Activity activity) {
		mapper.insertSelective(activity);
	}
}
