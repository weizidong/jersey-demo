package com.wzd.web.rest.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Activity;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.ActivityService;
import com.wzd.web.dto.PageDto;
import com.wzd.web.param.PageParam;

/**
 * 活动接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityApi {
	@Autowired
	private ActivityService service;

	/**
	 * 发起活动
	 */
	@Path("/create")
	@POST
	public void create(Activity activity) {
		service.create(activity);
	}

	/**
	 * 删除活动
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
	 * 修改活动
	 */
	@Path("/update")
	@POST
	public void update(Activity activity) {
		service.update(activity);
	}

	/**
	 * 获取活动详情
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/get/{id}/{type}")
	@POST
	public Activity getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	/**
	 * 获取活动列表
	 */
	@Path("/find")
	@POST
	public PageDto find(PageParam param) {
		return service.find(param);
	}

}
