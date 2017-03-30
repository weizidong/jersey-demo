package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Files;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.FileService;
import com.wzd.web.filter.log.RequestLog;
import com.wzd.web.filter.log.RequestLogType;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

/**
 * 文件接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
public class FileApi {
	@Autowired
	private FileService service;

	/**
	 * 上传文件
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	public Files upload(FormDataMultiPart form, @Context HttpServletRequest request) {
		return service.upload(form, request);
	}

	/**
	 * 删除文件
	 */
	@DELETE
	@Path("/delete/{id}/{del}")
	public void delete(@PathParam("id") String id, @PathParam("del") Integer del) {
		service.delete(id, DeleteType.parse(del));
	}

	/**
	 * 批量删除文件
	 */
	@POST
	@Path("/deleteAll")
	public void deleteAll(IdListParam<String> param) {
		service.delete(param);
	}

	/**
	 * 条件查询文件列表
	 */
	@POST
	@Path("/list/{del}")
	public PageInfo<Files> list(PageParam param, @PathParam("del") Integer del) {
		return service.list(param, DeleteType.parse(del));
	}
}
