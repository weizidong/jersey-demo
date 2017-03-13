package com.wzd.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public class FileManagerUtil {
	public static final String BASE_PATH = System.getProperty("jetty.home") + "/webapps";
	
//	public static final String BASE_PATH = System.getProperty("user.dir") + "/src/main/webapp";// 测试用
	private static final String RESOURCE_URL = "/userfiles/followup/";

	/**
	 * 写文件到硬盘，返回相对路径
	 * 
	 * @param file
	 * @param fileDisposition
	 * @return
	 */
	public String writeFile(String dirPath, InputStream file, FormDataContentDisposition fileDisposition) {
		String fileFullName = fileDisposition.getFileName();
		String storeFileName = UUIDUtil.get();// 文件名uuid生成
		String storeExt = fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());// 后缀
		String storeFolder = RESOURCE_URL+ dirPath + File.separator + DateUtils.dateToString(new Date(), DateUtils.PDATE2) + File.separator + "images";
		String storePath = BASE_PATH + storeFolder + File.separator + storeFileName + storeExt;
		
		try {

			File storeDirPath = new File(BASE_PATH +  storeFolder);

			if (!storeDirPath.exists()) {
				storeDirPath.mkdirs();
			}
			storeDirPath = null;
			storeDirPath = new File(storePath);

			OutputStream outputStream = new FileOutputStream(storeDirPath);

			int length = 0;
			byte[] buff = new byte[256];
			while (-1 != (length = file.read(buff))) {
				outputStream.write(buff, 0, length);
			}
			file.close();
			outputStream.close();
			return storeFolder+ File.separator + storeFileName + storeExt;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String writeFile(InputStream file, FormDataContentDisposition fileDisposition) {
		return writeFile("image", file, fileDisposition);
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public Boolean delete(String fileName) {
		File file = new File(BASE_PATH + fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

}
