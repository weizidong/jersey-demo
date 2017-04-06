package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.RecruitDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Recruit;

/**
 * 招聘信息业务类
 * 
 * @author WeiZiDong
 *
 */
@Service
public class RecruitService {
	@Autowired
	private RecruitDao recruitDao;

	/**
	 * 创建
	 */
	public Recruit create(Recruit r, Admin admin) {
		r.setAdminId(admin.getId());
		return null;
	}
}
