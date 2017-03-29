package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.AdminService;
import com.wzd.utils.PoiExcelUtils;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

/**
 * 管理员接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminApi {
	@Autowired
	private AdminService service;

	/**
	 * 登录
	 */
	@Path("/login")
	@POST
	public void login(Admin admin, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		service.login(admin, request, response);
	}

	/**
	 * 创建
	 */
	@Path("/create")
	@POST
	public void create(Admin admin) {
		service.create(admin);
	}

	/**
	 * 删除
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/delete/{userid}/{type}")
	@POST
	public void delete(@PathParam("userid") String userid, @PathParam("type") Integer type) {
		service.delete(userid, DeleteType.parse(type));
	}

	/**
	 * 批量删除成员
	 */
	@Path("/deleteBatch")
	@POST
	public void delete(IdListParam<String> param) {
		service.delete(param);
	}

	/**
	 * 修改
	 */
	@Path("/update")
	@POST
	public void update(Admin admin) {
		service.update(admin);
	}

	/**
	 * 查询指定id用户
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/get/{id}/{type}")
	@POST
	public Admin getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	/**
	 * 条件查询列表
	 */
	@Path("/find")
	@POST
	public PageInfo<Admin> getById(PageParam param) {
		return service.find(param);
	}

	/**
	 * 审核
	 * 
	 * @param type
	 *            审核类型,1：审核通过；2：审核未通过
	 */
	@Path("/audit/{type}")
	@POST
	public void auditing(@PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.auditing(AuditType.parse(type), (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 批量导出管理员
	 */
	@Path("/export")
	@POST
	public void export(IdListParam<Integer> param, @Context HttpServletResponse response) {
		PoiExcelUtils.writeWorkbook(response, service.export(param.getIds(), response));
	}
}
