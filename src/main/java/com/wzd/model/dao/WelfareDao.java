package com.wzd.model.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
		wel.setCreated(new Date());
		mapper.insertSelective(wel);
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
	public PageInfo<Welfare> find(PageParam param, DeleteType del) {
		Example e = new Example(Welfare.class);
		// 设置条件
		PageParam.setCondition(e, param, del, Welfare.class);
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
		if (type != null) {
			w.setDeleted(type.getValue());
		}
		return mapper.selectOne(w);
	}

	/**
	 * 根据ID查询福利
	 */
	public List<Welfare> getByAdmin(String adminId, DeleteType type) {
		Welfare w = new Welfare();
		w.setAdminId(adminId);
		if (type != null) {
			w.setDeleted(type.getValue());
		}
		return mapper.select(w);
	}

	/**
	 * 删除指定福利
	 */
	public void delete(String welfareId, String adminId, DeleteType del) {
		Welfare w = new Welfare();
		if (StringUtils.isNotBlank(welfareId)) {
			w.setId(welfareId);
		}
		if (StringUtils.isNotBlank(adminId)) {
			w.setAdminId(adminId);
		}
		if (del == DeleteType.永久删除) {
			mapper.delete(w);
		} else {
			w.setDeleted(del.getValue());
			mapper.updateByPrimaryKeySelective(w);
		}

	}

	/**
	 * 修改福利信息
	 */
	public void update(Welfare welfare) {
		mapper.updateByPrimaryKeySelective(welfare);
	}
}
