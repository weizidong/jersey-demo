package com.wzd.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.DepartmentDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Department;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.wechat.department.WxDepService;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.session.SessionUtil;

/**
 * 部门业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class DepartmentService {
	private static final Logger log = LogManager.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentDao dao;
	@Autowired
	private WxDepService wxService;

	/**
	 * 创建部门
	 */
	public void create(Department dep, HttpServletRequest request) {
		Admin admin = (Admin) SessionUtil.getUser(request);
		dep = wxService.create(dep);
		dep.setAdmin(admin.getUserid());
		dao.create(dep);
	}

	/**
	 * 删除部门
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO Auto-generated method stub
	}

	/**
	 * 修改部门
	 */
	public void update(Department dep) {
		// TODO Auto-generated method stub
	}

	/**
	 * 根据id查询
	 */
	public Activity findById(Integer id, DeleteType type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取部门列表(全部)
	 */
	public List<Department> findAll() {
		return dao.findAll();
	}

	/**
	 * 获取部门列表(树形)
	 */
	public Department findTree(Department dep) {
		if (dep == null || dep.getId() == null) {
			return null;
		}
		if (StringUtil.isEmpty(dep.getName())) {
			dep = dao.getById(dep.getId() == null ? 0 : dep.getId());
		}
		if (dep == null) {
			return null;
		}
		List<Department> deps = dao.findByParentid(dep.getId());
		if (deps != null && deps.size() > 0) {
			deps.forEach(d -> {
				d = findTree(d);
			});
			dep.setChild(deps);
		}
		return dep;
	}
}
