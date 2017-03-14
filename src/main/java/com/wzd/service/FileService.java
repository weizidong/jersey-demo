package com.wzd.service;

import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletContext;

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
 * @author weizidong
 *
 */
@Service
public class FileService {
	private static final Logger log = LogManager.getLogger(FileService.class);
	@Autowired
	private FilesMapper mapper;

	public Files upload(InputStream file, FormDataContentDisposition disposition, ServletContext context) {
		log.debug("开始上传文件。。。");
		Files f = new Files();
		f.setCreated(new Date());
		f.setDeleted(DeleteType.未删除.getValue());
		String fileFullName = disposition.getFileName();// 文件名
		String storeExt = fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());// 后缀
		log.debug("开始写入文件。。。");
		String url = FileUtil.writeFile(FileUtil.LOCAL_BASE_PATH, file, disposition);// 写入文件
		log.debug("写入文件成功！");
		f.setName(fileFullName);
		f.setSuffix(storeExt);
		f.setUrl(url);
		mapper.insert(f);
		log.debug("上传文件成功:" + f);
		return f;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
	}

}
