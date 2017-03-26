package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.History;
import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.utils.Configs;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
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
		num = historyDao.getCount(user.getId(), HistoryType.券票福利, DeleteType.未删除);
		Integer num1 = historyDao.getCount(user.getId(), HistoryType.红包福利, DeleteType.未删除);
		user.setWelfNum(num + num1);
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

	/**
	 * 签到
	 */
	public User sign(String userid) {
		User user = userDao.getById(userid);
		if (user == null) {
			throw new WebException(ResponseCode.用户不存在, userid);
		}
		Boolean isSign = historyDao.isSign(userid);
		if (isSign) {
			throw new WebException(ResponseCode.已经签到);
		}
		user.setScore(user.getScore() + Integer.parseInt(Configs.get("score")));
		userDao.update(user);
		History h = new History(userid, "积分签到", "积分签到", Integer.parseInt(Configs.get("score")), null,
				HistoryType.积分签到.getValue(), null);
		historyDao.create(h);
		return user;
	}

}
