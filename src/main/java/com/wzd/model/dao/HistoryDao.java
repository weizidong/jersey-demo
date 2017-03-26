package com.wzd.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.mapper.HistoryMapper;
import com.wzd.utils.DateUtil;
import com.wzd.utils.UUIDUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 用户历史数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class HistoryDao {
	@Autowired
	private HistoryMapper mapper;

	/**
	 * 查询相应消息种类的数量
	 */
	public Integer getCount(String userId, HistoryType type, DeleteType del) {
		History history = new History();
		history.setUserId(userId);
		history.setType(type.getValue());
		if (del != null) {
			history.setDeleled(del.getValue());
		}
		return mapper.selectCount(history);
	}

	/**
	 * 查询指定人员的历史记录
	 */
	public List<History> list(String userId, DeleteType del) {
		Example e = new Example(History.class);
		e.setOrderByClause("recording desc");
		e.createCriteria().andEqualTo("userId", userId).andEqualTo("deleled", del.getValue());
		return mapper.selectByExample(e);
	}

	/**
	 * 创建
	 */
	public void create(History h) {
		h.setId(UUIDUtil.get());
		h.setDeleled(DeleteType.未删除.getValue());
		h.setRecording(new Date());
		mapper.insert(h);
	}

	/**
	 * 查询该用户已兑换该福利的次数
	 */
	public Integer isDraw(String welfareId, String userId) {
		History h = new History();
		h.setUserId(userId);
		h.setWelfareId(welfareId);
		return mapper.selectCount(h);
	}

	/**
	 * 获取当前自然周签到历史
	 */
	public List<History> getSign(String userid) {
		Example e = new Example(History.class);
		e.setOrderByClause("recording asc");
		e.createCriteria().andEqualTo("userId", userid).andGreaterThan("recording", DateUtil.getFirstDayOfWeek(new Date(), 1));
		return mapper.selectByExample(e);
	}

	/**
	 * 是否已经签到
	 */
	public Boolean isSign(String userid) {
		Example e = new Example(History.class);
		e.createCriteria().andEqualTo("userId", userid).andGreaterThan("recording", DateUtil.getDayStartTime(System.currentTimeMillis()));
		return mapper.selectCountByExample(e) >= 1;
	}
}
