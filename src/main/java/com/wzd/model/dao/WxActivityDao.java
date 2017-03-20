package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Wxactivity;
import com.wzd.model.mapper.WxactivityMapper;

/**
 * 微信活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class WxActivityDao {
	@Autowired
	private WxactivityMapper mapper;

	/**
	 * 创建
	 */
	public void create(Wxactivity wxactivity) {
		mapper.insert(wxactivity);
	}
}
