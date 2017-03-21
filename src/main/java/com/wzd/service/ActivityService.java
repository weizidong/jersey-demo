package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.ActivityDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.enums.DeleteType;
import com.wzd.web.dto.PageDto;
import com.wzd.web.param.PageParam;

/**
 * 活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class ActivityService {
	@Autowired
	private ActivityDao dao;

	/**
	 * 创建活动
	 */
	public void create(Activity activity) {
		dao.create(activity);
	}

	/**
	 * 删除活动
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO 删除活动
	}

	/**
	 * 修改活动
	 */
	public void update(Activity activity) {
		// TODO 修改活动
	}

	/**
	 * 根据id查询
	 */
	public Activity findById(Integer id, DeleteType type) {
		// TODO 根据id查询
		return null;
	}

	/**
	 * 条件查询所有
	 */
	public PageDto find(PageParam param) {
		// TODO 条件查询所有
		return null;
	}
}
