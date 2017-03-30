package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.WelfareService;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

/**
 * 福利接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/welfare")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WelfareApi {
	@Autowired
	private WelfareService service;

	/**
	 * 创建福利
	 */
	@POST
	@Path("create")
	public Welfare create(Welfare w, @Context HttpServletRequest request) {
		return service.create(w, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 条件查询列表
	 */
	@POST
	@Path("/find/{del}")
	public PageInfo<Welfare> find(PageParam param, @PathParam("del") Integer del, @Context HttpServletRequest request) {
		return service.find(param, DeleteType.parse(del), SessionUtil.getSession(request));
	}

	/**
	 * 查询福利详情
	 */
	@GET
	@Path("/find/{id}/{del}")
	public Welfare findById(@PathParam("id") String id, @PathParam("del") Integer del) {
		return service.findById(id, del);
	}

	/**
	 * 兑换福利
	 */
	@POST
	@Path("/convert/{id}")
	public void convert(@PathParam("id") String id, @Context HttpServletRequest request) {
		service.convert(id, (User) SessionUtil.getUser(request));
	}

	/**
	 * 修改福利
	 */
	@PUT
	@Path("/update")
	public void update(Welfare wel) {
		service.update(wel);
	}

	/**
	 * 删除福利
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void delete(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}
}
