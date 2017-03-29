package com.wzd.web.rest.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.History;
import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.HistoryService;
import com.wzd.web.dto.history.HistoryDto;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

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
	 * 获取积分历史记录
	 */
	@POST
	@Path("/score/{userId}/{del}")
	public HistoryDto score(PageParam param, @PathParam("userId") String userId, @PathParam("del") Integer del) {
		return service.findScore(param, userId, DeleteType.parse(del));
	}

	/**
	 * 获取消息历史记录
	 */
	@GET
	@Path("/msg")
	public List<History> msg(PageParam param, @Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.findMsg(param, user.getId());
	}

	/**
	 * 获取签到历史
	 */
	@GET
	@Path("/sign")
	public List<History> sign(PageParam param, @Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.getSign(param, user.getId());
	}

	/**
	 * 获取签到历史(当前自然周)
	 */
	@GET
	@Path("/weekSign")
	public List<History> getWeekSign(@Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.getSign(new PageParam(), user.getId());
	}
}
