package com.wzd.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.entity.Files;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.FilesMapper;
import com.wzd.utils.FileUtil;

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
	private FilesMapper mapper;

	/**
	 * 上传文件
	 */
	public Files upload(InputStream file, FormDataContentDisposition disposition, HttpServletRequest request) {
		log.debug("开始上传文件。。。");
		Files f = new Files();
		f.setCreated(new Date());
		f.setDeleted(DeleteType.未删除.getValue());
		String fileFullName = disposition.getFileName();// 文件名
		String storeExt = fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());// 后缀
		log.debug("开始写入文件。。。");
		String url = FileUtil.writeFile(FileUtil.basePath(), file, disposition);// 写入文件
		log.debug("写入文件成功！");
		f.setName(fileFullName);
		f.setSuffix(storeExt);
		f.setUrl(url);
		// TODO 获取上传者
		f.setUserid(1);
		mapper.insert(f);
		log.debug("上传文件成功:" + f);
		return f;
	}

	/**
	 * 删除文件
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
	}

	/**
	 * 获取文件列表
	 */
	public List<Files> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
