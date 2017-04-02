package com.wzd.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Department;
import com.wzd.model.enums.DeleteType;
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
		dep.setDeleted(DeleteType.未删除);
		mapper.insertSelective(dep);
	}

	/**
	 * 根据ID获取
	 */
	public Department getById(Integer id, DeleteType del) {
		Department dep = new Department();
		dep.setId(id);
		if (del != null) {
			dep.setDeleted(del);
		}
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
		if (getById(dep.getId(), null) == null) {
			create(dep);
		} else {
			update(dep);
		}

	}

	/**
	 * 获取全部
	 */
	public List<Department> findAll(DeleteType del) {
		Example e = new Example(Department.class);
		e.setOrderByClause("parentid ASC,orders ASC");
		e.createCriteria().andEqualTo("deleted", del.getValue());
		return mapper.selectByExample(e);
	}

	/**
	 * 根据父id查询列表
	 */
	public List<Department> findByParentid(Integer parentid) {
		Example e = new Example(Department.class);
		e.createCriteria().andEqualTo("parentid", parentid);
		e.setOrderByClause("orders ASC");
		return mapper.selectByExample(e);
	}

	/**
	 * 删除
	 */
	public void delete(Integer id, DeleteType del) {
		Department dep = new Department();
		dep.setId(id);
		if (del == DeleteType.永久删除) {
			mapper.delete(dep);
		} else {
			dep.setDeleted(del);
			update(dep);
		}
	}
}
