package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import com.wzd.model.entity.Recruit;
import com.wzd.model.entity.User;
import com.wzd.service.RecruitService;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

/**
 * 招聘信息接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/recruit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecruitApi {
	@Autowired
	private RecruitService service;

	/***
	 * 创建
	 */
	@POST
	@Path("/create")
	public Recruit create(Recruit r, @Context HttpServletRequest request) {
		return service.create(r, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 报名
	 */
	@POST
	@Path("/entry/{id}")
	public void entry(User u, @PathParam("id") String id) {
		service.entry(id, u);
	}

	/**
	 * 获取招聘信息列表
	 */
	@POST
	@Path("/find")
	public PageInfo<Recruit> find(PageParam param) {
		return service.find(param);
	}

	/**
	 * 获取详情
	 */
	@GET
	@Path("/find/{id}")
	public Recruit getById(@PathParam("id") String id) {
		return service.getById(id);
	}
}
