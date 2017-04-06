package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.RecruitDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.Recruit;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.PageParam;

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
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryformDao entryformDao;

	/**
	 * 创建
	 */
	public Recruit create(Recruit r, Admin admin) {
		r.setAdminId(admin.getId());
		recruitDao.create(r);
		return r;
	}

	/**
	 * 报名
	 */
	public void entry(String id, User u) {
		Entryform ef = new Entryform(u.getOpenid(), id, ActivityType.招聘活动);
		if (entryformDao.isEntry(ef)) {
			throw new WebException(ResponseCode.已报名);
		}
		userDao.update(u);
		entryformDao.entry(ef);
	}

	/**
	 * 获取详情
	 */
	public Recruit getById(String id) {
		return recruitDao.getById(id);
	}

	/**
	 * 获取列表
	 */
	public PageInfo<Recruit> find(PageParam param) {
		return recruitDao.find(param);
	}
}
