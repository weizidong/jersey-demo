package com.wzd.model.mapper;

import java.util.List;
import java.util.Map;

import com.wzd.model.entity.History;
import com.wzd.web.dto.history.SignDto;

public interface HistoryMapper extends MyMapper<History> {

	/**
	 * 自定义查询积分列表
	 */
	List<SignDto> getSignList(Map<String, Object> param);
}