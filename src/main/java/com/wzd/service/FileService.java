package com.wzd.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.FileDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Files;
import com.wzd.model.entity.User;
import com.wzd.model.enums.APPType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.FileType;
import com.wzd.utils.FileUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.SessionUtil;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

/**
 * 文件上传
 * 
 * @author WeiZiDong
 *
 */
@Service
public class FileService {
	private static final Logger log = LogManager.getLogger(FileService.class);
	@Autowired
	private FileDao dao;

	/**
	 * 上传文件
	 */
	public Files upload(FormDataMultiPart form, HttpServletRequest request) {
		log.debug("开始上传文件。。。");
		FormDataBodyPart filePart = form.getField("file");
		Integer type = form.getField("type").getValueAs(Integer.class);
		FileType.parse(type);
		InputStream file = filePart.getValueAs(InputStream.class);
		FormDataContentDisposition disposition = filePart.getFormDataContentDisposition();
		Files f = FileUtil.writeFile(file, disposition);
		f.setType(type);
		if (APPType.企业号.getValue().equals(request.getParameter("appType")) || APPType.管理平台.getValue().equals(request.getParameter("appType"))) {
			Admin user = (Admin) SessionUtil.getUser(request);
			f.setUserId(user.getUserid());
		}
		if (APPType.服务号.getValue().equals(request.getParameter("appType"))) {
			User user = (User) SessionUtil.getUser(request);
			f.setUserId(user.getId().toString());
		}
		dao.create(f);
		log.debug("上传文件成功:" + f);
		return f;
	}

	/**
	 * 删除文件
	 */
	public void delete(String id, DeleteType type) {
		Files f = dao.getById(id);
		if (f == null) {
			throw new WebException(ResponseCode.资源不存在, "文件不存在");
		}
		if (DeleteType.永久删除 != type) {
			f.setDeleted(type.getValue());
			dao.update(f);
		} else {
			dao.delete(id);
			FileUtil.delete(f.getUrl());
		}
	}

	/**
	 * 获取文件列表
	 */
	public PageInfo<Files> list(PageParam param, DeleteType del) {
		return dao.list(param, del);
	}

	/**
	 * 批量删除文件
	 */
	public void delete(IdListParam<String> param) {
		DeleteType type = DeleteType.parse(param.getType());
		param.getIds().forEach(id -> {
			delete(id, type);
		});
	}
}
