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
import com.wzd.model.entity.Sports;
import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.SportsService;
import com.wzd.web.dto.entryForm.EntryFormDto;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

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

	/**
	 * 修改健身活动
	 */
	@POST
	@Path("/update")
	public void update(Sports s) {
		service.update(s);
	}

	/**
	 * 删除健身活动
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void update(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}

	/**
	 * 获取健身活动列表
	 */
	@POST
	@Path("/list")
	public PageInfo<Sports> list(PageParam param) {
		return service.list(param);
	}

	/**
	 * 获取健身活动详情
	 */
	@GET
	@Path("/get/{id}")
	public Sports getById(@PathParam("id") String id) {
		return service.getById(id);
	}

	/**
	 * 暂停或开启健身活动
	 */
	@POST
	@Path("/pause/{id}")
	public void pause(@PathParam("id") String id) {
		service.pause(id);
	}

	/**
	 * 报名
	 */
	@POST
	@Path("/entry")
	public void entry(Sports s, @Context HttpServletRequest request) {
		service.entry(s, (User) SessionUtil.getUser(request));
	}

	/**
	 * 取消报名
	 */
	@POST
	@Path("/cancelEntry")
	public void cacelEntry(Sports s, @Context HttpServletRequest request) {
		service.cacelEntry(s, (User) SessionUtil.getUser(request));
	}

	/**
	 * 签到
	 */
	@POST
	@Path("/sign")
	public void sign(@Context HttpServletRequest request) {
		service.sign((User) SessionUtil.getUser(request));
	}

	/**
	 * 获取健身活动报名列表
	 */
	@POST
	@Path("/entryList/{id}")
	public PageInfo<EntryFormDto> entryList(PageParam param, @PathParam("id") String id) {
		return service.entryList(param, id);
	}

	/**
	 * 导出报名表
	 */
	@POST
	@Path("/export/{id}")
	public String export(PageParam param, @PathParam("id") String id) {
		return service.export(param, id);
	}
}
