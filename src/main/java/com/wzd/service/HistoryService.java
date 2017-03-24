package com.wzd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.HistoryDao;
import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;

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
	 * 获取指定用户的历史记录
	 */
	public List<History> findAll(String userid) {
		return historyDao.list(userid, DeleteType.未删除);
	}

	/**
	 * 获取当前自然周历史记录
	 */
	public List<History> getSign(String userid) {
		return historyDao.getSign(userid);
	}

}
