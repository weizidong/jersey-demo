package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
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
import com.wzd.model.entity.Article;
import com.wzd.model.enums.ArticleType;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.ArticleService;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.PageParam;

/**
 * 文章接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleApi {
	@Autowired
	private ArticleService service;

	/**
	 * 发布文章
	 */
	@POST
	@Path("/create")
	public Article create(Article a, @Context HttpServletRequest request) {
		return service.create(a, (Admin) SessionUtil.getUser(request));
	}

	/**
	 * 搜索文章
	 */
	@POST
	@Path("/find/{del}/{type}")
	public PageInfo<Article> find(PageParam param, @PathParam("type") Integer type, @PathParam("del") Integer del, @Context HttpServletRequest request) {
		return service.find(param, ArticleType.parse(type), DeleteType.parse(del), (Admin) SessionUtil.getUser(request));
	}
}
