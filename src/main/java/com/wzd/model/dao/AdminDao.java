package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.AdminMapper;

/**
 * 管理员数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Service
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
		admin.setUpdated(new Date());
		mapper.insert(admin);
		return admin;
	}

	/**
	 * 根据UserId获取用户信息
	 */
	public Admin getByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
