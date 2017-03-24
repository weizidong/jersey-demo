package com.wzd.web.rest.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.History;
import com.wzd.model.entity.User;
import com.wzd.service.HistoryService;
import com.wzd.web.dto.session.SessionUtil;

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

	/**
	 * 获取签到历史(当前自然周)
	 */
	@GET
	@Path("/sign")
	public List<History> getSign(@Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.getSign(user.getId());
	}
}
