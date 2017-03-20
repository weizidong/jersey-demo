package com.wzd.web.rest.api;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Files;
import com.wzd.service.FileService;
import com.wzd.web.filter.log.RequestLog;
import com.wzd.web.filter.log.RequestLogType;

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
	public Files upload(@FormDataParam("file") InputStream file, @FormDataParam("file") FormDataContentDisposition disposition, @Context HttpServletRequest request) {
		return service.upload(file, disposition, request);
	}

	/**
	 * 删除文件
	 */
	@POST
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Integer id) {
		service.delete(id);
	}

	/**
	 * 文件列表
	 */
	@POST
	@Path("/list")
	public List<Files> list() {
		return service.list();
	}
}
