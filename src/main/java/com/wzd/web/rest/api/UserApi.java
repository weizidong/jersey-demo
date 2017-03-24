package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.UserService;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

/**
 * 用户接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApi {
	@Autowired
	private UserService service;

	/**
	 * 获取我的资料
	 */
	@GET
	@Path("/mine")
	public User findMine(@Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.findMine(user);
	}

	/**
	 * 修改我的资料
	 */
	@PUT
	@Path("/mine")
	public void update(User user) {
		service.update(user);
	}

	// 以上是实现的业务接口

	/**
	 * 创建
	 */
	@Path("/create")
	@POST
	public void create(User user) {
		service.create(user);
	}

	/**
	 * 删除
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/delete/{id}/{type}")
	@POST
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		service.delete(id, DeleteType.parse(type));
	}

	/**
	 * 查询指定id用户
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/get/{id}/{type}")
	@POST
	public User getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	/**
	 * 条件查询列表
	 */
	@Path("/find")
	@POST
	public PageInfo<User> getById(PageParam param) {
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
}
