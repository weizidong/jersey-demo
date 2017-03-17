package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.mapper.WxactivityMapper;

/**
 * 微信活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WxActivityDao {
	@Autowired
	private WxactivityMapper mapper;
}
