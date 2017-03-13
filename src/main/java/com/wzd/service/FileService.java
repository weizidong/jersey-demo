package com.wzd.service;

import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wzd.model.entity.File;
import com.wzd.model.mapper.FileMapper;
import com.wzd.utils.DateUtil;

/**
 * 文件上传
 * 
 * @author weizidong
 *
 */
@Service
public class FileService {
	private static final Logger log = LogManager.getLogger(FileService.class);
	private static final String BASEPATH = LogManager.getLogger(FileService.class);
	@Autowired
	private FileMapper mapper;

	public File upload(InputStream fileInputStream, FormDataContentDisposition disposition) {
		String fileName = disposition.getFileName();
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);

		String imageName = DateUtil.getCurrentDate("yyyy-MM-dd") + java.io.File.separatorChar + UUID.randomUUID() + "."
				+ prefix;

		java.io.File file = new File(ARTICLE_IMAGES_PATH + imageName);
		try {
			// 使用common io的文件写入操作
			FileUtils.copyInputStreamToFile(fileInputStream, file);
		} catch (IOException ex) {

		}

		return null;
	}

}
