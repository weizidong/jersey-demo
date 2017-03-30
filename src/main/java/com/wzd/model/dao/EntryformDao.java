package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Entryform;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.SignType;
import com.wzd.model.mapper.EntryformMapper;
import com.wzd.utils.UUIDUtil;

/**
 * 报名表数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class EntryformDao {
	@Autowired
	private EntryformMapper mapper;

	/**
	 * 报名
	 */
	public void entry(Entryform ef) {
		ef.setId(UUIDUtil.get());
		ef.setDeleted(DeleteType.未删除.getValue());
		ef.setStatus(SignType.未签到.getValue());
		mapper.insertSelective(ef);
	}
}
