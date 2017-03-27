package com.wzd.web.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	 * 初始化所有数据
	 */
	@POST
	@Path("/all")
	public void findAll() {
		service.initAll();
	}

	/**
	 * 创建测试数据
	 */
	@POST
	@Path("/test")
	public void test() {
		service.test();
	}

	/**
	 * 清理测试数据
	 */
	@DELETE
	@Path("/test")
	public void clear() {
		service.clear();
	}

}
