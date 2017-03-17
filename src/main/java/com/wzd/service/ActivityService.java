package com.wzd.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger log = LogManager.getLogger(ActivityService.class);
	@Autowired
	private ActivityDao dao;

	/**
	 * 创建活动
	 */
	public void create(Activity activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * 删除活动
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO Auto-generated method stub
	}

	/**
	 * 修改活动
	 */
	public void update(Activity activity) {
		// TODO Auto-generated method stub
	}

	/**
	 * 根据id查询
	 */
	public Activity findById(Integer id, DeleteType type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 条件查询所有
	 */
	public PageDto find(PageParam param) {
		// TODO Auto-generated method stub
		return null;
	}
}
