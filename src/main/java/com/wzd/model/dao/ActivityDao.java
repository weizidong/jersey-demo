package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.mapper.ActivityMapper;

/**
 * 活动数据库操作
 * 
 * @author WeiZiDong
 *
 */

@Service
public class ActivityDao {
	@Autowired
	private ActivityMapper mapper;
}
