package com.wzd.web.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.service.InitService;

/**
 * 系统初始化接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/init")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Init {
	@Autowired
	private InitService service;

	/**
	 * 获取历史记录
	 */
	@POST
	@Path("/all")
	public void findAll() {
		service.initAll();
	}
}
