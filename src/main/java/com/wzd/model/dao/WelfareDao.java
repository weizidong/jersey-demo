package com.wzd.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.mapper.WelfareMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 福利数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class WelfareDao {
	@Autowired
	private WelfareMapper mapper;

	/**
	 * 创建
	 */
	public Welfare create(Welfare wel) {
		wel.setId(UUIDUtil.get());
		wel.setDeleted(DeleteType.未删除.getValue());
		wel.setCurrent(0);
		mapper.insert(wel);
		return wel;
	}

	/**
	 * 根据类型查找福利
	 */
	public List<Welfare> getByType(HistoryType type, DeleteType del) {
		Welfare w = new Welfare();
		w.setType(type.getValue());
		w.setDeleted(del.getValue());
		return mapper.select(w);
	}

	/**
	 * 条件查询列表
	 */
	public PageInfo<Welfare> find(PageParam param) {
		Example e = new Example(Welfare.class);
		// 设置条件
		PageParam.setCondition(e, param, Welfare.class);
		// 开始分页
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Welfare>(mapper.selectByExample(e));
	}

	/**
	 * 根据ID查询福利
	 */
	public Welfare getById(String welfareId, DeleteType type) {
		Welfare w = new Welfare();
		w.setId(welfareId);
		w.setDeleted(type.getValue());
		return mapper.selectOne(w);
	}
}
