package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Recruit;
import com.wzd.service.RecruitService;
import com.wzd.web.dto.session.SessionUtil;

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
}
