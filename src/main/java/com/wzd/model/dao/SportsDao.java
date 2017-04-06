package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Sports;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.SportsMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 健身活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class SportsDao {
	@Autowired
	private SportsMapper mapper;

	/**
	 * 创建
	 */
	public Sports create(Sports s) {
		s.setId(UUIDUtil.get());
		s.setCreated(new Date());
		s.setDeleted(DeleteType.未删除);
		s.setStatus(StateType.进行中);
		mapper.insert(s);
		return s;
	}

	/**
	 * 修改活动
	 */
	public void update(Sports s) {
		mapper.updateByPrimaryKeySelective(s);
	}

	/**
	 * 删除活动
	 */
	public void delete(String id, DeleteType del) {
		Sports s = new Sports();
		s.setId(id);
		if (del == DeleteType.永久删除) {
			mapper.delete(s);
		} else {
			s.setDeleted(del);
			update(s);
		}
	}

	/**
	 * 获取健身活动列表
	 */
	public PageInfo<Sports> list(PageParam param) {
		Example e = new Example(Sports.class);
		PageParam.setCondition(e, "created", param, Sports.class);
		PageParam.setOrderByClause(e, "created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Sports>(mapper.selectByExample(e));
	}

	/**
	 * 根据id获取详情
	 */
	public Sports getById(String id) {
		Sports s = new Sports();
		s.setId(id);
		return mapper.selectOne(s);
	}
}
