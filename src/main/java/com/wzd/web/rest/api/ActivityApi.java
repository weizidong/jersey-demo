package com.wzd.web.rest.api;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Activity;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.ActivityService;

/**
 * 活动接口
 * 
 * @author weizidong
 *
 */
@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityApi {
	@Autowired
	private ActivityService service;
	/**
	 * 发起活动
	 * @param activity
	 */
	@Path("/create")
	@POST
	public void create(Activity activity) {
		service.create(activity);
	}
	/**
	 * 删除活动
	 * @param id
	 * @param type
	 */
	@Path("/delete/{id}/{type}")
	@POST
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		service.delete(id, DeleteType.parse(type));
	}
	/**
	 * 修改活动
	 * @param activity
	 */
	@Path("/update")
	@POST
	public void update(Activity activity) {
		service.update(activity);
	}
	/**
	 * 获取活动详情
	 * @param id
	 * @param type
	 * @return
	 */
	@Path("/get/{id}/{type}")
	@POST
	public Activity getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	@Path("/find")
	@POST
	public List<Activity> getById(Activity activity) {
		return service.find(activity);
	}
	
}
