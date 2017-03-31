package com.wzd.model.dao;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Entryform;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.SignType;
import com.wzd.model.mapper.EntryformMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.dto.entryForm.EntryFormDto;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

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
		ef.setCreated(new Date());
		mapper.insertSelective(ef);
	}

	/**
	 * 是否报名
	 */
	public Boolean isEntry(Entryform ef) {
		return mapper.selectCount(ef) > 0;
	}

	/**
	 * 签到
	 */
	public void sign(Entryform ef) {
		Example e = new Example(Entryform.class);
		e.createCriteria().andEqualTo(ef);
		ef.setStatus(SignType.已签到);
		mapper.updateByExampleSelective(ef, e);
	}

	/**
	 * 获取签到列表
	 */
	public PageInfo<EntryFormDto> entryList(PageParam param, String id) {
		PageHelper.startPage(param.getPage(), param.getPageSize());
		Map<String, Object> p = PageParam.getCondition(param, EntryFormDto.class);
		p.put("activityId", id);
		return new PageInfo<EntryFormDto>(mapper.getSignList(p));
	}
}
