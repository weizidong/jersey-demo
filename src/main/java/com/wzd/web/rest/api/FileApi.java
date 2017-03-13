package com.wzd.web.rest.api;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifesense.communityhospital.web.filter.validate.Validate;
import com.lifesense.communityhospital.web.filter.validate.ValidateType;
import com.lifesense.framework.rest.base.filter.log.annotate.RequestLog;
import com.lifesense.framework.rest.base.filter.log.annotate.RequestLogType;
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

	/**
	 * 
	 * @param file
	 * @param fileDisposition
	 * @param fileName
	 * @param application
	 * @return
	 */
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	@Validate(ValidateType.NOVALIDATE)
	public String uploadFile(@FormDataParam("file") InputStream file, @FormDataParam("file") FormDataContentDisposition fileDisposition,
			@FormDataParam("fileName") String fileName, @FormDataParam("data") String patientId, @Context ServletContext application) {
		String dirPath = patientId;
		return fileManager.writeFile(dirPath, file, fileDisposition);
	}

	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteImage(Map<String, String> map) {
		String fileName = map.get("fileName");
		return fileManager.delete(fileName);
	}

}
