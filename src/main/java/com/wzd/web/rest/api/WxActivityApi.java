package com.wzd.web.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
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
 * 微信活动接口
 * 
 * @author weizidong
 *
 */
@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WxActivityApi {
	@Autowired
	private ActivityService service;

	@Path("/create")
	@POST
	public void create(Activity activity) {
		service.create(activity);
	}

	@Path("/delete/{id}/{type}")
	@POST
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		service.delete(id, DeleteType.parse(type));
	}

	@Path("/update")
	@POST
	public void update(Activity activity) {
		service.update(activity);
	}

	@Path("/get/{id}/{type}")
	@POST
	public Activity getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	@Path("/find")
	@POST
	public List<Activity> getById(Activity activity) {
		return service.find(activity);
	}
}
