package com.wzd.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.entity.History;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.web.dto.history.ActivityDto;
import com.wzd.web.dto.history.SignDto;
import com.wzd.web.dto.history.WelfareDto;
import com.wzd.web.param.PageParam;

/**
 * 历史业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class HistoryService {
	@Autowired
	private HistoryDao historyDao;

	/**
	 * 获取积分记录
	 */
	public PageInfo<SignDto> findScore(PageParam param, String userId, DeleteType del) {
		return new PageInfo<SignDto>(historyDao.getSignList(param, userId,
				Arrays.asList(HistoryType.积分签到.getValue(), HistoryType.券票福利.getValue(), HistoryType.红包福利.getValue(), HistoryType.工会活动.getValue(), HistoryType.健身活动.getValue()),
				del));
	}

	/**
	 * 获取未读消息
	 */
	public PageInfo<History> findMsg(PageParam param, String userid) {
		return new PageInfo<History>(historyDao.list(param, userid, HistoryType.系统消息, DeleteType.未删除));
	}

	/**
	 * 获取我的活动历史记录
	 */
	public PageInfo<ActivityDto> findActivity(PageParam param, String openId, DeleteType parse) {
		return new PageInfo<ActivityDto>(historyDao.findActivity(param, openId, ActivityType.工会活动, DeleteType.未删除));
	}

	/**
	 * 获取签到记录
	 */
	public List<History> getSign(String userid) {
		return historyDao.getSign(userid);
	}

	/**
	 * 获取签到记录列表
	 */
	public PageInfo<SignDto> getSignList(PageParam param, String userId, DeleteType del) {
		return new PageInfo<SignDto>(historyDao.getSignList(param, userId, Arrays.asList(HistoryType.积分签到.getValue()), del));
	}

	/**
	 * 获取我的福利
	 */
	public PageInfo<WelfareDto> findWelfare(PageParam param, String userId, DeleteType del) {
		return new PageInfo<WelfareDto>(historyDao.findWelfare(param, userId, Arrays.asList(HistoryType.券票福利.getValue(), HistoryType.红包福利.getValue()), del));
	}

	/**
	 * 删除历史记录
	 */
	public void delete(String id, DeleteType del) {
		historyDao.delete(id, del);
	}
}
