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
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.ActivityService;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

/**
 * 活动接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivityApi {
	@Autowired
	private ActivityService service;

	/**
	 * 创建活动
	 */
	@POST
	@Path("/create")
	public Activity create(Activity activity, @Context HttpServletRequest request) {
		return service.create(activity, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 修改活动
	 */
	@POST
	@Path("/update")
	public void update(Activity activity) {
		service.update(activity);
	}

	/**
	 * 删除活动
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void delete(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}

	/**
	 * 获取活动详情
	 */
	@GET
	@Path("/get/{id}")
	public Activity getById(@PathParam("id") String id) {
		return service.findById(id);
	}

	/**
	 * 获取活动列表
	 */
	@POST
	@Path("/find")
	public PageInfo<Activity> find(PageParam param) {
		return service.find(param);
	}

}
