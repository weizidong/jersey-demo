package com.wzd.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Department;
import com.wzd.model.mapper.DepartmentMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 部门数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class DepartmentDao {
	@Autowired
	private DepartmentMapper mapper;

	/**
	 * 创建
	 */
	public void create(Department dep) {
		mapper.insert(dep);
	}

	/**
	 * 根据ID获取
	 */
	public Department getById(Integer id) {
		Department dep = new Department();
		dep.setId(id);
		return mapper.selectOne(dep);
	}

	/**
	 * 修改(更新不为null的值)
	 */
	public void update(Department dep) {
		mapper.updateByPrimaryKeySelective(dep);
	}

	/**
	 * 修改(更新全部字段)
	 */
	public void updateAll(Department dep) {
		mapper.updateByPrimaryKey(dep);
	}

	/**
	 * 修改或创建
	 */
	public void save(Department dep) {
		if (getById(dep.getId()) == null) {
			create(dep);
		} else {
			update(dep);
		}

	}

	/**
	 * 获取全部
	 */
	public List<Department> findAll() {
		Example e = new Example(Department.class);
		e.setOrderByClause("parentid asc,orders asc");
		return mapper.selectByExample(e);
	}

	public List<Department> findByParentid(Integer parentid) {
		Example e = new Example(Department.class);
		e.createCriteria().andEqualTo("parentid", parentid);
		e.setOrderByClause("orders asc");
		return mapper.selectByExample(e);
	}
}
