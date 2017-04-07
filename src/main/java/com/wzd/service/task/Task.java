package com.wzd.service.task;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wzd.utils.DateUtil;
import com.wzd.utils.FileUtil;

/**
 * 定时任务
 * 
 * @author WeiZiDong
 *
 */
@Component
public class Task {
	private static final Logger log = LogManager.getLogger(Task.class);

	// 每天定时清理下载文件夹
	public void deleteFile() {
		log.debug("开始清理文件。。。\t时间：" + DateUtil.dateFormat(new Date()));
		try {
			FileUtils.deleteDirectory(new File(FileUtil.BASE_PATH + FileUtil.DOWNLOAD_URL));
		} catch (IOException e) {
			log.error("清理文件失败!\t时间：" + DateUtil.dateFormat(new Date()));
			throw new RuntimeException("清理文件失败!", e);
		}
	}
}
