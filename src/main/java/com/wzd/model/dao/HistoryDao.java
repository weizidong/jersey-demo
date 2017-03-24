package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.mapper.HistoryMapper;

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
	public Integer getCount(Integer userid, HistoryType type, DeleteType del) {
		History history = new History();
		history.setUser_id(userid);
		history.setType(type.getValue());
		if (del != null) {
			history.setDeleled(del.getValue());
		}
		return mapper.selectCount(history);
	}
}
