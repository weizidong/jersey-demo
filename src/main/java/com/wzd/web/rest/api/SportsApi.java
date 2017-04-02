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
import com.wzd.model.entity.Sports;
import com.wzd.service.SportsService;
import com.wzd.web.dto.session.SessionUtil;

/**
 * 运动接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/sports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SportsApi {
	@Autowired
	private SportsService service;

	/**
	 * 创建健身活动
	 */
	@POST
	@Path("/create")
	public Sports create(Sports s, @Context HttpServletRequest request) {
		return service.create(s, (Admin) SessionUtil.getUser(request));
	}
}
