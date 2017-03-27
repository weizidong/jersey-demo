package com.wzd.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.HistoryDao;
import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;

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
	public List<History> findScore(String userid) {
		return historyDao.list(userid, Arrays.asList(HistoryType.积分签到.getValue(), HistoryType.券票福利.getValue(), HistoryType.红包福利.getValue(), HistoryType.活动.getValue()),
				DeleteType.未删除);
	}

	/**
	 * 获取未读消息
	 */
	public List<History> findMsg(String userid) {
		return historyDao.list(userid, HistoryType.系统消息, DeleteType.未删除);
	}

	/**
	 * 获取当前自然周历史记录
	 */
	public List<History> getSign(String userid) {
		return historyDao.getSign(userid);
	}

}
