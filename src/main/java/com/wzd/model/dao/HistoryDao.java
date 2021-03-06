package com.wzd.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.wzd.model.entity.History;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.mapper.HistoryMapper;
import com.wzd.utils.DateUtil;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.dto.history.ActivityDto;
import com.wzd.web.dto.history.SignDto;
import com.wzd.web.dto.history.WelfareDto;
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
		Criteria c = PageParam.setCondition(e, "recording", param, History.class);
		PageParam.setOrderByClause(e, "recording DESC");
		c.andEqualTo("userId", userId).andIn("type", types);
		if (del != DeleteType.全部) {
			c.andEqualTo("deleled", del.getValue());
		}
		PageHelper.startPage(param.getPage(), param.getPageSize());
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
		h.setDeleled(DeleteType.未删除);
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
	public List<History> getSign(String userId) {
		Example e = new Example(History.class);
		e.setOrderByClause("recording asc");
		e.createCriteria().andEqualTo("userId", userId).andEqualTo("type", HistoryType.积分签到.getValue()).andGreaterThan("recording", DateUtil.getFirstDayOfWeek(new Date(), 1));
		return mapper.selectByExample(e);
	}

	/**
	 * 是否已经签到
	 */
	public Boolean isSign(String userId) {
		Example e = new Example(History.class);
		e.createCriteria().andEqualTo("userId", userId).andEqualTo("type", HistoryType.积分签到.getValue()).andGreaterThan("recording",
				DateUtil.getDayStartTime(System.currentTimeMillis()));
		return mapper.selectCountByExample(e) >= 1;
	}

	/**
	 * 获取签到列表
	 */
	public List<SignDto> getSignList(PageParam param, String userId, List<Integer> types, DeleteType del) {
		Map<String, Object> map = PageParam.getCondition(param, WelfareDto.class);
		map.put("userId", userId);
		map.put("types", types);
		map.put("del", del.getValue());
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return mapper.getSignList(map);
	}

	/**
	 * 删除历史记录
	 */
	public void delete(String id, DeleteType del) {
		History h = new History();
		h.setId(id);
		if (del == DeleteType.永久删除) {
			mapper.delete(h);
		} else {
			h.setDeleled(del);
			mapper.updateByPrimaryKeySelective(h);
		}
	}

	/**
	 * 获取我的活动
	 */
	public List<ActivityDto> findActivity(PageParam param, String openId, ActivityType type, DeleteType del) {
		Map<String, Object> map = PageParam.getCondition(param, WelfareDto.class);
		map.put("openId", openId);
		map.put("type", type.getValue());
		map.put("del", del.getValue());
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return mapper.findActivity(map);
	}

	/**
	 * 获取我的福利
	 */
	public List<WelfareDto> findWelfare(PageParam param, String userId, List<Integer> types, DeleteType del) {
		Map<String, Object> map = PageParam.getCondition(param, WelfareDto.class);
		map.put("userId", userId);
		map.put("types", types);
		map.put("del", del.getValue());
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return mapper.findWelfare(map);
	}

	/**
	 * 根据id获取
	 */
	public History getById(String id) {
		History h = new History();
		h.setId(id);
		return mapper.selectOne(h);
	}

	/**
	 * 修改记录
	 */
	public void update(History h) {
		mapper.updateByPrimaryKeySelective(h);
	}
}
