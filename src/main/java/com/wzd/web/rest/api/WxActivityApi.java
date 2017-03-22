package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Wxactivity;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.service.WxActivityService;
import com.wzd.utils.PoiExcelUtils;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

/**
 * 微信活动接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/wxActivity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WxActivityApi {
	@Autowired
	private WxActivityService service;

	/**
	 * 发起
	 */
	@Path("/create")
	@POST
	public void create(Wxactivity activity, @Context HttpServletRequest request) {
		service.create(activity, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 删除
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站；2：永久
	 */
	@Path("/delete/{id}/{type}")
	@POST
	public void delete(@PathParam("id") Integer id, @PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.delete(id, DeleteType.parse(type), (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 修改
	 */
	@Path("/update")
	@POST
	public void update(Wxactivity activity, @Context HttpServletRequest request) {
		service.update(activity, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 查询详情
	 * 
	 * @param type
	 *            删除类型，0：不刪；1：回收站
	 */
	@Path("/get/{id}/{type}")
	@POST
	public Wxactivity getById(@PathParam("id") Integer id, @PathParam("type") Integer type) {
		return service.findById(id, DeleteType.parse(type));
	}

	/**
	 * 条件查询列表
	 */
	@Path("/find")
	@POST
	public PageInfo<Wxactivity> find(PageParam param) {
		return service.find(param);
	}

	/**
	 * 审核
	 * 
	 * @param type
	 *            审核类型,1：审核通过；2：审核未通过
	 */
	@Path("/audit/{type}")
	@POST
	public void auditing(@PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.auditing(AuditType.parse(type), (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 暂停/启用
	 * 
	 * @param type
	 *            停用(0), 启用(1);
	 */
	@Path("/state/{type}")
	@POST
	public void changeState(@PathParam("type") Integer type, @Context HttpServletRequest request) {
		service.changeState(StateType.parse(type), (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 批量导出活动详情
	 */
	@Path("/export")
	@POST
	public void export(IdListParam<Integer> param, @Context HttpServletResponse response) {
		PoiExcelUtils.writeWorkbook(response, service.export(param.getIds(), response));
	}

	/**
	 * 导出参加活动的人员
	 */
	@Path("/exportUser/{id}")
	@POST
	public void exportUser(@PathParam("id") Integer id, @Context HttpServletResponse response) {
		service.exportUser(id, response);
	}
}
