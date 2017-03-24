package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.web.param.PageParam;

/**
 * 用户业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private HistoryDao historyDao;

	/**
	 * 获取我的信息
	 */
	public User findMine(User user) {
		Integer num = historyDao.getCount(user.getId(), HistoryType.系统消息, DeleteType.未删除);
		user.setMsgNum(num);
		num = historyDao.getCount(user.getId(), HistoryType.福利, DeleteType.未删除);
		user.setWelfNum(num);
		num = historyDao.getCount(user.getId(), HistoryType.活动, DeleteType.未删除);
		user.setActNum(num);
		return user;
	}

	/**
	 * 修改
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 创建
	 */
	public void create(User user) {
		userDao.create(user);

	}

	/**
	 * 删除
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO 删除

	}

	/**
	 * 查询指定id用户
	 */
	public User findById(Integer id, DeleteType type) {
		// TODO 查询指定id用户
		return null;
	}

	/**
	 * 条件查询列表
	 */
	public PageInfo<User> find(PageParam param) {
		// TODO 条件查询列表
		return null;
	}

	/**
	 * 审核
	 */
	public void auditing(AuditType parse, Admin user) {
		// TODO 审核
	}

}
