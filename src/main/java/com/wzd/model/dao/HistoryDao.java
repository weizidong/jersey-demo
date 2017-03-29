package com.wzd.model.dao;

import java.util.ArrayList;
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
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
	public Integer getCount(String userId, List<Integer> types, DeleteType del) {
		Example e = new Example(History.class);
		e.createCriteria().andEqualTo("userId", userId).andEqualTo("deleled", del.getValue()).andIn("type", types);
		return mapper.selectCountByExample(e);
	}

	/**
	 * 查询相应消息种类的数量
	 */
	public Integer getCount(String userId, HistoryType type, DeleteType del) {
		List<Integer> types = new ArrayList<>();
		types.add(type.getValue());
		return getCount(userId, types, del);
	}

	/**
	 * 查询指定人员的历史记录
	 */
	public List<History> list(PageParam param, String userId, List<Integer> types, DeleteType del) {
		Example e = new Example(History.class);
		e.setOrderByClause("recording desc");
		Criteria c = PageParam.setCondition(e, "recording", param, History.class);
		c.andEqualTo("userId", userId).andIn("type", types);
		if (del != DeleteType.全部) {
			c.andEqualTo("deleled", del.getValue());
		}
		return mapper.selectByExample(e);
	}

	/**
	 * 查询指定人员的历史记录
	 */
	public List<History> list(PageParam param, String userId, HistoryType type, DeleteType del) {
		List<Integer> types = new ArrayList<>();
		types.add(type.getValue());
		return list(param, userId, types, del);
	}

	/**
	 * 创建
	 */
	public void create(History h) {
		h.setId(UUIDUtil.get());
		h.setDeleled(DeleteType.未删除.getValue());
		h.setRecording(new Date());
		mapper.insertSelective(h);
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
		e.createCriteria().andEqualTo("userId", userid).andEqualTo("type", HistoryType.积分签到.getValue()).andGreaterThan("recording", DateUtil.getFirstDayOfWeek(new Date(), 1));
		return mapper.selectByExample(e);
	}

	/**
	 * 是否已经签到
	 */
	public Boolean isSign(String userid) {
		Example e = new Example(History.class);
		e.createCriteria().andEqualTo("userId", userid).andEqualTo("type", HistoryType.积分签到.getValue()).andGreaterThan("recording",
				DateUtil.getDayStartTime(System.currentTimeMillis()));
		return mapper.selectCountByExample(e) >= 1;
	}
}
