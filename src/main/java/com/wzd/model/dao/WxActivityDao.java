package com.wzd.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Wxactivity;
import com.wzd.model.mapper.WxactivityMapper;

import tk.mybatis.mapper.entity.Example;

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

	/**
	 * 根据ID批量查询
	 */
	public List<Wxactivity> findByIds(List<Integer> ids) {
		Example example = new Example(Wxactivity.class);
		example.createCriteria().andIn("id", ids);
		return mapper.selectByExample(example);
	}
}
