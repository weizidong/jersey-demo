package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.AdminMapper;

/**
 * 管理员数据库操作
 * 
 * @author WeiZiDong
 *
 */
public class AdminDao {
	@Autowired
	private AdminMapper mapper;

	/**
	 * 创建
	 */
	public Admin create(Admin admin) {
		admin.setAudit(0);
		admin.setCreated(new Date());
		admin.setDeleted(DeleteType.未删除.getValue());
		mapper.insert(admin);
		return admin;
	}
}