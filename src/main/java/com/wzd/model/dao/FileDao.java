package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Files;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.FilesMapper;

/**
 * 文件数据库操作
 * 
 * @author WeiZiDong
 *
 */
public class FileDao {
	@Autowired
	private FilesMapper mapper;

	/**
	 * 创建
	 */
	public Files insert(Files file) {
		file.setCreated(new Date());
		file.setDeleted(DeleteType.未删除.getValue());
		file.setUpdated(new Date());
		mapper.insert(file);
		return file;
	}
}