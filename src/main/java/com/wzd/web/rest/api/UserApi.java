package com.wzd.web.rest.api;

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
	 * 修改资料
	 */
	@POST
	@Path("/update")
	public void update(User user) {
		service.update(user);
	}

	/**
	 * 签到
	 */
	@POST
	@Path("/sign")
	public User sign(@Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.sign(user.getId());
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
	 * 查询指定id用户
	 */
	@GET
	@Path("/get/{id}")
	public User getById(@PathParam("id") String id) {
		return service.getById(id);
	}

	/**
	 * 条件查询列表
	 */
	@POST
	@Path("/find")
	public PageInfo<User> find(PageParam param) {
		return service.find(param);
	}

	/**
	 * 审核
	 */
	@POST
	@Path("/audit/{userId}/{type}")
	public void auditing(@PathParam("userId") String userId, @PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.auditing(userId, AuditType.parse(type), (Admin) SessionUtil.getUser(request));
	}
}
