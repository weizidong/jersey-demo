package com.wzd.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.wzd.model.entity.Files;

/**
 * 文件工具类
 * 
 * @author WeiZiDong
 *
 */
public class FileUtil {
	private static final String RESOURCE_URL = "/userfiles";
	private static String BASE_PATH = "";

	static {
		String basePath = System.getProperty("jetty.home");
		if (basePath == null) {
			BASE_PATH = System.getProperty("user.dir") + "/src/main/webapp";
		} else {
			BASE_PATH = basePath + "/webapps";
		}
	}

	/**
	 * 写文件到硬盘，返回相对路径
	 */
	public static Files writeFile(InputStream file, FormDataContentDisposition disposition) {
		try {
			// 文件全称
			String fullName = new String(disposition.getFileName().getBytes("ISO8859-1"), "UTF-8");
			// 文件名UUID生成
			String fileName = UUIDUtil.get();
			// 后缀
			String ext = fullName.substring(fullName.lastIndexOf("."), fullName.length());
			// 相对路径
			String folder = RESOURCE_URL + File.separator + DateUtil.dateToString(new Date(), DateUtil.PDATE2);
			// 绝对路径
			String path = BASE_PATH + folder + File.separator + fileName + ext;
			// 生成目录
			File store = new File(BASE_PATH + folder);
			if (!store.exists()) {
				store.mkdirs();
			}
			store = new File(path);
			FileUtils.copyInputStreamToFile(file, store);
			// 返回值
			Files f = new Files();
			f.setName(fullName);
			f.setSuffix(ext);
			f.setUrl(folder + File.separator + fileName + ext);
			return f;
		} catch (IOException e) {
			throw new RuntimeException("写文件失败", e);
		}
	}

	/**
	 * 删除文件
	 */
	public static void delete(String url) {
		File file = new File(BASE_PATH + url);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
}
