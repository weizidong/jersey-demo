package com.wzd.web.rest.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	@POST
	@Path("/login")
	public void login(Admin admin, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		service.login(admin, request, response);
	}

	/**
	 * 获取扫码登录二维码
	 */
	@GET
	@Path("/login2")
	public Map<String, Object> login2(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		return service.login2(request, response);
	}

	/**
	 * 创建
	 */
	@POST
	@Path("/create")
	public void create(Admin admin) {
		service.create(admin);
	}

	/**
	 * 删除
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void delete(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}

	/**
	 * 修改
	 */
	@POST
	@Path("/update")
	public void update(Admin admin) {
		service.update(admin);
	}

	/**
	 * 查询指定id用户
	 */
	@GET
	@Path("/get/{id}")
	public Admin getById(@PathParam("id") Integer id) {
		return service.findById(id);
	}

	/**
	 * 条件查询列表
	 */
	@POST
	@Path("/find")
	public PageInfo<Admin> getById(PageParam param) {
		return service.find(param);
	}

	/**
	 * 审核
	 */
	@POST
	@Path("/audit/{type}")
	public void auditing(@PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.auditing(AuditType.parse(type), (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 批量导出管理员
	 */
	@POST
	@Path("/export")
	public void export(IdListParam<Integer> param, @Context HttpServletResponse response) {
		PoiExcelUtils.writeWorkbook(response, service.export(param.getIds(), response));
	}
}
