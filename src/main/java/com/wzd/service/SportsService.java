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

}
