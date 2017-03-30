package com.wzd.web.rest.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Department;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.DepartmentService;

/**
 * 部门接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentApi {
	@Autowired
	private DepartmentService service;

	/**
	 * 获取部门列表(全部)
	 */
	@GET
	@Path("/findAll")
	public List<Department> findAll() {
		return service.findAll();
	}

	/**
	 * 创建部门
	 */
	@POST
	@Path("/create")
	public void create(Department dep, @Context HttpServletRequest request) {
		service.create(dep, request);
	}

	/**
	 * 删除部门
	 */
	@DELETE
	@Path("/delete/{id}/{type}")
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		service.delete(id, DeleteType.parse(type));
	}

	/**
	 * 修改部门
	 */
	@POST
	@Path("/update")
	public void update(Department dep) {
		service.update(dep);
	}

	/**
	 * 获取部门详情
	 */
	@GET
	@Path("/get/{id}")
	public Department getById(@PathParam("id") Integer id) {
		return service.findById(id);
	}

	/**
	 * 获取部门列表(树形)
	 */
	@GET
	@Path("/tree/{parentId}")
	public Department findTree(@PathParam("parentId") Integer parentId) {
		Department dep = new Department();
		dep.setId(parentId == null ? 1 : parentId);
		return service.findTree(dep);
	}

}
