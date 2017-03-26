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
import com.wzd.model.entity.User;
import com.wzd.model.entity.Welfare;
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
		return service.create(w);
	}

	/**
	 * 条件查询列表
	 */
	@POST
	@Path("/find")
	public PageInfo<Welfare> find(PageParam param, @Context HttpServletRequest request) {
		return service.find(param, SessionUtil.getSession(request));
	}

	/**
	 * 查询福利详情
	 */
	@GET
	@Path("/find/{welfareId}/{delType}")
	public Welfare findById(@PathParam("welfareId") String welfareId, @PathParam("delType") Integer delType) {
		return service.findById(welfareId, delType);
	}

	/**
	 * 兑换福利
	 */
	@POST
	@Path("/convert/{welfareId}")
	public void convert(@PathParam("welfareId") String welfareId, @Context HttpServletRequest request) {
		service.convert(welfareId, (User) SessionUtil.getUser(request));
	}
}
