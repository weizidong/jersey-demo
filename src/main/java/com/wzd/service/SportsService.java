package com.wzd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.SportsDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Sports;
import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.web.param.PageParam;

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
	 * 修改健身活动
	 */
	public Sports update(Sports s) {
		// TODO 修改健身活动
		return null;
	}

	/**
	 * 开启或关闭健身活动
	 */
	public void pause(String id) {
		// TODO 开启或关闭健身活动
	}

	/**
	 * 删除健身活动
	 */
	public void delete(String id, DeleteType del) {
		// TODO 删除健身活动
	}

	/**
	 * 获取健身活动列表
	 */
	public PageInfo<Sports> list(PageParam param) {
		// TODO 获取健身活动列表
		return null;
	}

	/**
	 * 获取健身活动报名列表
	 */
	public List<Sports> entryList(PageParam param) {
		// TODO 获取健身活动报名列表
		return null;
	}

	/**
	 * 报名健身活动
	 */
	public void entry(Sports s, User user) {
		// TODO 报名健身活动
	}

	/**
	 * 签到健身活动
	 */
	public void sign(User user) {
		// TODO 签到健身活动
	}

}
