package com.wzd.web.rest.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.File;
import com.wzd.service.FileService;

/**
 * 文件上传接口
 * 
 * @author weizidong
 *
 */
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
public class FileApi {
	@Autowired
	private FileService service;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public File upload(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition disposition) {
		File file = service.upload(fileInputStream, disposition);
		return file;
	}

}
