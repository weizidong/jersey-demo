package com.wzd.service;

import java.io.InputStream;
import java.util.Date;
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
import com.wzd.utils.DateUtils;
import com.wzd.utils.UUIDUtil;

/**
 * 文件上传
 * 
 * @author weizidong
 *
 */
@Service
public class FileService {
	private static final Logger log = LogManager.getLogger(FileService.class);
	public static final String SERVER_BASE_PATH = System.getProperty("jetty.home") + "/webapps";
	public static final String LOCAL_BASE_PATH = System.getProperty("user.dird") + "/src/main/webapp";
	private static final String RESOURCE_URL = "/userfiles/";
	@Autowired
	private FileMapper mapper;

	public File upload(InputStream fileInputStream, FormDataContentDisposition disposition) {
		String fileFullName = disposition.getFileName();
		String storeFileName = UUIDUtil.get();// 文件名uuid生成
		String storeExt = fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());// 后缀
		String storeFolder = RESOURCE_URL + File.separator + DateUtils.dateToString(new Date(), DateUtils.PDATE2);
		String storePath = LOCAL_BASE_PATH + storeFolder + File.separator + storeFileName + storeExt;
		
		java.io.File file = new File(ARTICLE_IMAGES_PATH + imageName);
		try {
			// 使用common io的文件写入操作
			FileUtils.copyInputStreamToFile(fileInputStream, file);
		} catch (IOException ex) {

		}

		return null;
	}

}
