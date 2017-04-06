package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Recruit;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.RecruitMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 招聘信息数据库操作类
 * 
 * @author WeiZiDong
 *
 */
@Component
public class RecruitDao {
	@Autowired
	private RecruitMapper mapper;

	/**
	 * 创建
	 */
	public void create(Recruit r) {
		r.setId(UUIDUtil.get());
		r.setCreated(new Date());
		r.setDeleted(DeleteType.未删除);
		r.setStatus(StateType.未开始);
		mapper.insertSelective(r);
	}

	/**
	 * 获取详情
	 */
	public Recruit getById(String id) {
		Recruit r = new Recruit();
		r.setId(id);
		return mapper.selectOne(r);
	}

	/**
	 * 获取列表
	 */
	public PageInfo<Recruit> find(PageParam param) {
		Example e = new Example(Recruit.class);
		PageParam.setCondition(e, "created", param, Recruit.class);
		e.setOrderByClause(e.getOrderByClause() + ",created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Recruit>(mapper.selectByExample(e));
	}
}
