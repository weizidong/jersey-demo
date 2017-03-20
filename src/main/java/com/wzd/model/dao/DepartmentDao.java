package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Department;
import com.wzd.model.mapper.DepartmentMapper;

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
	public void create(Department department) {
		mapper.insert(department);
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
}
