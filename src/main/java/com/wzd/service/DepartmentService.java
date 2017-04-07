package com.wzd.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.DepartmentDao;
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
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private WxDepService wxService;

	/**
	 * 创建部门
	 */
	public void create(Department dep, HttpServletRequest request) {
		Admin admin = (Admin) SessionUtil.getUser(request);
		dep = wxService.create(dep);
		dep.setAdmin(admin.getId());
		departmentDao.create(dep);
	}

	/**
	 * 删除部门
	 */
	public void delete(Integer id, DeleteType type) {
		wxService.delete(id);
		departmentDao.delete(id, type);
	}

	/**
	 * 修改部门
	 */
	public void update(Department dep) {
		wxService.update(dep);
		departmentDao.update(dep);
	}

	/**
	 * 根据id查询
	 */
	public Department findById(Integer id) {
		return departmentDao.getById(id, null);
	}

	/**
	 * 获取部门列表(全部)
	 */
	public List<Department> findAll() {
		return departmentDao.findAll(DeleteType.未删除);
	}

	/**
	 * 获取部门列表(树形)
	 */
	public Department findTree(Department dep) {
		if (dep == null || dep.getId() == null) {
			return null;
		}
		if (StringUtil.isEmpty(dep.getName())) {
			dep = departmentDao.getById(dep.getId() == null ? 0 : dep.getId(), DeleteType.parse(dep.getDeleted()));
		}
		if (dep == null) {
			return null;
		}
		List<Department> deps = departmentDao.findByParentid(dep.getId());
		if (deps != null && deps.size() > 0) {
			deps.forEach(d -> {
				d = findTree(d);
			});
			dep.setChild(deps);
		}
		return dep;
	}

}
