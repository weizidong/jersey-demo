package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.SportsDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Sports;

/**
 * 健身活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class SportsService {
	@Autowired
	private SportsDao sportsDao;

	/**
	 * 创建健身活动
	 */
	public Sports create(Sports s, Admin admin) {
		s.setAdminId(admin.getId());
		return sportsDao.create(s);
	}

	/**
	 * 开启或关闭健身活动
	 */
	public Sports pause(Sports s, Admin admin) {
		// TODO 开启或关闭健身活动
		return null;
	}

	/**
	 * 删除健身活动
	 */
	public Sports delete(Sports s, Admin admin) {
		// TODO 删除健身活动
		return null;
	}

	/**
	 * 获取健身活动列表
	 */
	public Sports list(Sports s, Admin admin) {
		// TODO 获取健身活动列表
		return null;
	}

	/**
	 * 获取健身活动报名列表
	 */
	public Sports entryList(Sports s, Admin admin) {
		// TODO 获取健身活动报名列表
		return null;
	}

}
