package com.wzd.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.wzd.model.entity.Entryform;
import com.wzd.model.enums.ActivityType;
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
		ef.setDeleted(DeleteType.未删除);
		ef.setStatus(SignType.未签到);
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
	 * 获取报名
	 */
	public Entryform get(Entryform ef) {
		return mapper.selectOne(ef);
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
	 * 删除报名名单
	 */
	public void delete(Entryform ef, DeleteType del) {
		if (del == DeleteType.永久删除) {
			mapper.delete(ef);
		} else {
			Example e = new Example(Entryform.class);
			e.createCriteria().andEqualTo(ef);
			ef.setDeleted(del);
			mapper.updateByExampleSelective(ef, e);
		}
	}

	/**
	 * 获取报名列表
	 */
	public List<EntryFormDto> entryList(PageParam param, String id) {
		Map<String, Object> p = PageParam.getCondition(param, EntryFormDto.class);
		p.put("activityId", id);
		if (param.getPageSize() != null) {
			PageHelper.startPage(param.getPage(), param.getPageSize());
		}
		return mapper.getSignList(p);
	}

	/**
	 * 获取报名列表
	 */
	public List<Entryform> list(Entryform ef) {
		return mapper.select(ef);
	}

	/**
	 * 获取报名列表
	 */
	public List<Entryform> list(Entryform ef, Date time) {
		Example e = new Example(Entryform.class);
		e.createCriteria().andGreaterThan("end", time).andLessThan("start", time).andEqualTo(ef);
		e.setOrderByClause("created DESC");
		return mapper.selectByExample(e);
	}

	/**
	 * 获取参加活动的数量
	 */
	public Integer getCount(String openId, ActivityType type, DeleteType del) {
		Entryform ef = new Entryform(openId, type, del, SignType.未签到.getValue());
		return mapper.selectCount(ef);
	}
}
