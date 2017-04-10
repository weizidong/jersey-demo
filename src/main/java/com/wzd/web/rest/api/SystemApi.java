package com.wzd.web.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Setting;
import com.wzd.service.SystemService;

/**
 * 系统接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/system")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SystemApi {
	@Autowired
	private SystemService service;

	/**
	 * 初始化系统
	 */
	@POST
	@Path("/init")
	public void init() {
		service.init();
	}

	/**
	 * 清理缓存
	 */
	@POST
	@Path("/clear")
	public void clear() {
		service.clear();
	}

	/**
	 * 系统设置
	 */
	@POST
	@Path("/setting")
	public void setting(Setting s) {
		service.setting(s);
	}

	/**
	 * 获取系统设置
	 */
	@GET
	@Path("/setting")
	public Setting getSetting() {
		return service.getSetting();
	}

}
