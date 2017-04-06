package com.wzd.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.web.dto.history.HistoryDto;
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
	@Autowired
	private UserDao userDao;

	/**
	 * 获取积分记录
	 */
	public HistoryDto findScore(PageParam param, String userid, DeleteType del) {
		HistoryDto dto = new HistoryDto();
		dto.setUser(userDao.getById(userid));
		dto.setHistorys(historyDao.list(param, userid,
				Arrays.asList(HistoryType.积分签到.getValue(), HistoryType.券票福利.getValue(), HistoryType.红包福利.getValue(), HistoryType.活动.getValue()), del));
		return dto;
	}

	/**
	 * 获取未读消息
	 */
	public PageInfo<History> findMsg(PageParam param, String userid) {
		return new PageInfo<History>(historyDao.list(param, userid, HistoryType.系统消息, DeleteType.未删除));
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
	public PageInfo<SignDto> getSignList(PageParam param) {
		return historyDao.getSignList(param);
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
