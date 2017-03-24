package com.wzd.web.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.History;
import com.wzd.service.HistoryService;

/**
 * 历史接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoryApi {
	@Autowired
	private HistoryService service;

	/**
	 * 获取历史记录
	 */
	@GET
	@Path("/{userid}")
	public List<History> findAll(@PathParam("userid") String userid) {
		return service.findAll(userid);
	}
}
