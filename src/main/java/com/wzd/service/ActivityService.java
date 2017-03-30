package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.ActivityDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
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
	public Activity create(Activity activity, Admin admin) {
		return dao.create(activity, admin);
	}

	/**
	 * 修改活动
	 */
	public void update(Activity activity) {
		dao.update(activity);
	}

	/**
	 * 删除活动
	 */
	public void delete(String id, DeleteType del) {
		dao.delete(id, del);
	}

	/**
	 * 根据id查询
	 */
	public Activity findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 条件查询所有
	 */
	public PageInfo<Activity> find(PageParam param) {
		return new PageInfo<Activity>(dao.find(param));
	}

	/**
	 * 根据adminId删除
	 */
	public void deleteByAdmin(String adminId, DeleteType del) {
		dao.deleteByAdmin(adminId, del);
	}
}
