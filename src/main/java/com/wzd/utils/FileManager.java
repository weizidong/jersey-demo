package com.wzd.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.lifesense.framework.common.util.UUIDUtil;

/**
 * 文件管理
 * @author linjinyu
 *
 */
public class FileManager {
	
	public static final String TYPE_IMG = "/image";
	
	public static final String SERVER_BASE_PATH = System.getProperty("jetty.home") + "/webapps";
	public static final String LOCAL_BASE_PATH 	= System.getProperty("user.dird") + "/src/main/webapp";//测试用
	
	public static final String RES_CHAT_IMG_URL 	= "/userfiles/chat-img";
	public static final String RES_FOLLOW_UP_URL 	= "/userfiles/followup";
	
	
	private String fileFullName		= "";//文件全名 xxx.jpg
	
	private String storeRootPath 	= "";//根目录
	private String storeResPath		= "";//资源目录
	private String storeFolder		= "";//文件夹
	private String storeFileName 	= "";//文件名
	private String storeExt			= "";//扩展名
	private String storeDir			= "";//存储目录
	private String storePath 		= "";//存储路径
	
	private String relativePath		= "";//相对路径
	private String absolutePath		= "";//绝对路径
	
//	private String storeNameSpace	= "";
//	private String storeType		= "";
	
	public FileManager(	String fileFullName,
						String storeRootPath,
						String storeResPath,
						String storeNameSpace,
						String storeType){
		
		this.fileFullName 	= fileFullName;
		this.storeRootPath	= storeRootPath;
		this.storeResPath	= storeResPath;
//		this.storeNameSpace = storeNameSpace;
//		this.storeType		= storeType;
		
		this.storeFileName 	= UUIDUtil.get();
		this.storeFolder	= DateUtils.dateToString(new Date() , DateUtils.PDATE2);
		this.storeExt 		= fileFullName.substring(fileFullName.indexOf("."),fileFullName.length());
		
		this.storeDir		= storeRootPath + storeResPath + File.separator + storeNameSpace + File.separator + storeFolder + storeType;
		this.storePath		= storeDir + File.separator + storeFileName + storeExt;
		this.relativePath	= storeResPath + File.separator + storeNameSpace + File.separator + storeFolder +  storeType + File.separator + storeFileName + storeExt;
		
	}
	
	/**
	 * 写入
	 * @param file
	 * @return
	 */
	public Boolean write(InputStream file){
		
		try {
			
			File storeDirPath = new File(this.storeDir);
			
			boolean checkFolder = storeDirPath.exists();
			if (checkFolder == false) {
			    checkFolder = storeDirPath.mkdirs();
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
			
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 删除
	 * @return
	 */
	public Boolean delete() {
		File storeDirPath = new File(storePath);
		if (storeDirPath.isFile() && storeDirPath.exists()) {
			storeDirPath.delete();
			return true;
		}
		return false;
	}


	public String getFileFullName() {
		return fileFullName;
	}


	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}


	public String getStoreRootPath() {
		return storeRootPath;
	}


	public void setStoreRootPath(String storeRootPath) {
		this.storeRootPath = storeRootPath;
	}


	public String getStoreResPath() {
		return storeResPath;
	}


	public void setStoreResPath(String storeResPath) {
		this.storeResPath = storeResPath;
	}


	public String getStoreFolder() {
		return storeFolder;
	}


	public void setStoreFolder(String storeFolder) {
		this.storeFolder = storeFolder;
	}


	public String getStoreFileName() {
		return storeFileName;
	}


	public void setStoreFileName(String storeFileName) {
		this.storeFileName = storeFileName;
	}


	public String getStoreExt() {
		return storeExt;
	}


	public void setStoreExt(String storeExt) {
		this.storeExt = storeExt;
	}


	public String getStoreDir() {
		return storeDir;
	}


	public void setStoreDir(String storeDir) {
		this.storeDir = storeDir;
	}


	public String getStorePath() {
		return storePath;
	}


	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}


	public String getRelativePath() {
		return relativePath;
	}


	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}


	public String getAbsolutePath() {
		return absolutePath;
	}


	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	
	
	
	

}
