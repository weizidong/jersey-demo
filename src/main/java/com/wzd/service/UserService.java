package com.wzd.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.History;
import com.wzd.model.entity.Setting;
import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.utils.Configs;
import com.wzd.utils.DateUtil;
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
	@Autowired
	private SystemService systemService;

	/**
	 * 获取我的信息
	 */
	public User findMine(User user) {
		if (user.getDeleted() == DeleteType.回收站.getValue()) {
			throw new WebException(ResponseCode.用户被冻结);
		}
		Integer num = historyDao.getCount(user.getId(), HistoryType.系统消息, DeleteType.未删除);
		user.setMsgNum(num);
		num = historyDao.getCount(user.getId(), Arrays.asList(HistoryType.券票福利.getValue(), HistoryType.红包福利.getValue()), DeleteType.未删除);
		user.setWelfNum(num);
		num = historyDao.getCount(user.getId(), HistoryType.活动, DeleteType.未删除);
		user.setActNum(num);
		user.setIsSign(historyDao.isSign(user.getId()));
		return user;
	}

	/**
	 * 修改用户
	 */
	public void update(User user) {
		if (user.getDepId() != null || StringUtils.isNotBlank(user.getIdCard()) || StringUtils.isNotBlank(user.getName()) || StringUtils.isNotBlank(user.getPosition())) {
			user.setAudit(AuditType.审核中.getValue());
		}
		userDao.update(user);
	}

	/**
	 * 创建用户
	 */
	public void create(User user) {
		userDao.create(user);
	}

	/**
	 * 删除用户
	 */
	public void delete(String id, DeleteType type) {
		if (type != DeleteType.未删除 || type != DeleteType.回收站) {
			throw new WebException(ResponseCode.错误请求, "del类型错误");
		}
		User u = new User();
		u.setId(id);
		u.setDeleted(type.getValue());
		userDao.update(u);
	}

	/**
	 * 查询指定id用户
	 */
	public User getById(String id) {
		return userDao.getById(id);
	}

	/**
	 * 获取用户列表
	 */
	public PageInfo<User> find(PageParam param) {
		return new PageInfo<User>(userDao.find(param));
	}

	/**
	 * 审核
	 */
	public void auditing(String userId, AuditType audit, Admin admin) {
		User user = userDao.getById(userId);
		if (user == null) {
			throw new WebException(ResponseCode.用户不存在, userId);
		}
		user.setAudit(audit.getValue());
		user.setAuditor(admin.getId());
		userDao.update(user);
		History h;
		if (audit == AuditType.审核成功) {
			h = new History(userId, AuditType.审核成功.name(), Configs.get("audit.success"), null, null, HistoryType.系统消息, null);
		} else {
			h = new History(userId, AuditType.审核失败.name(), Configs.get("audit.fail"), null, null, HistoryType.系统消息, null);
		}
		historyDao.create(h);
	}

	/**
	 * 积分签到
	 */
	public User sign(String id) {
		User user = userDao.getById(id);
		if (user == null) {
			throw new WebException(ResponseCode.用户不存在, id);
		}
		List<History> hList = historyDao.getSign(id);
		History last = null;
		if (hList != null && hList.size() > 0) {
			last = hList.get(0);
		} else {
			user.setSignNum(0);
		}
		if (last != null && !DateUtil.isBeforeToday(last.getRecording())) { // 已经签到
			throw new WebException(ResponseCode.已经签到);
		}
		Date d = new Date();
		if (DateUtil.getCurrentDayStartTime() == DateUtil.getFirstDayOfWeek(d, 1)) { // 星期一
			user.setSignNum(0);
		}
		if (last != null && DateUtil.getPreviousDay(d).getTime() >= last.getRecording().getTime()) { // 签到断掉
			user.setSignNum(0);
		}
		Setting s = systemService.getSetting();
		user.setSignNum(user.getSignNum() + 1);
		user.setScore(user.getScore() + s.getSign() * user.getSignNum());
		userDao.update(user);
		History h = new History(id, "积分签到", null, s.getSign() * user.getSignNum(), null, HistoryType.积分签到, null);
		historyDao.create(h);
		return user;
	}

}
