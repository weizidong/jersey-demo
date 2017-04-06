package com.wzd.web.rest.api;

import java.util.List;

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
import com.wzd.model.entity.History;
import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.HistoryService;
import com.wzd.web.dto.history.HistoryDto;
import com.wzd.web.dto.history.SignDto;
import com.wzd.web.dto.history.WelfareDto;
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
	 * 获取我的福利
	 */
	@POST
	@Path("/welfare/{userId}/{del}")
	public PageInfo<WelfareDto> welfare(PageParam param, @PathParam("userId") String userId, @PathParam("del") Integer del) {
		return service.findWelfare(param, userId, DeleteType.parse(del));
	}

	/**
	 * 获取消息历史记录
	 */
	@POST
	@Path("/msg")
	public PageInfo<History> msg(PageParam param, @Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.findMsg(param, user.getId());
	}

	/**
	 * 获取签到历史
	 */
	@POST
	@Path("/sign")
	public PageInfo<SignDto> sign(PageParam param) {
		return service.getSignList(param);
	}

	/**
	 * 获取签到历史(当前自然周)
	 */
	@GET
	@Path("/weekSign")
	public List<History> getWeekSign(@Context HttpServletRequest request) {
		User user = (User) SessionUtil.getUser(request);
		return service.getSign(user.getId());
	}

	/**
	 * 删除历史记录
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void delete(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}
}
