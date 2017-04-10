package com.wzd.model.mapper;

import java.util.List;
import java.util.Map;

import com.wzd.model.entity.History;
import com.wzd.web.dto.history.ActivityDto;
import com.wzd.web.dto.history.SignDto;
import com.wzd.web.dto.history.WelfareDto;

public interface HistoryMapper extends MyMapper<History> {

	/**
	 * 自定义查询积分列表
	 */
	List<SignDto> getSignList(Map<String, Object> param);

	/**
	 * 获取我的福利
	 */
	List<WelfareDto> findWelfare(Map<String, Object> param);

	/**
	 * 获取我的活动
	 */
	List<ActivityDto> findActivity(Map<String, Object> map);
}