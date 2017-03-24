package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.AdminMapper;
import com.wzd.utils.UUIDUtil;

/**
 * 管理员数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class AdminDao {
	@Autowired
	private AdminMapper mapper;

	/**
	 * 创建
	 */
	public Admin create(Admin admin) {
		admin.setId(UUIDUtil.get());
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
		Admin admin = new Admin();
		admin.setUserid(userId);
		return mapper.selectOne(admin);
	}

	/**
	 * 修改(更新不为null的值)
	 */
	public void update(Admin admin) {
		mapper.updateByPrimaryKeySelective(admin);
	}

	/**
	 * 修改(更新全部字段)
	 */
	public void updateAll(Admin admin) {
		mapper.updateByPrimaryKey(admin);
	}

	/**
	 * 修改或创建
	 */
	public void save(Admin admin) {
		if (getByUserId(admin.getUserid()) == null) {
			create(admin);
		} else {
			update(admin);
		}
	}

	/**
	 * 根据电话号码获取用户信息
	 */
	public Admin getByMobile(String mobile, DeleteType type) {
		Admin admin = new Admin();
		admin.setMobile(mobile);
		admin.setDeleted(type.getValue());
		return admin;
	}

	/**
	 * 刪除
	 */
	public void delete(String userid, DeleteType type) {
		Admin admin = new Admin();
		admin.setUserid(userid);
		if (type == DeleteType.永久删除) {
			mapper.delete(admin);
		} else {
			admin.setDeleted(type.getValue());
			update(admin);
		}
	}

	/**
	 * 根据UserId或者email查找
	 */
	public Admin find(String userid, String email) {
		Admin admin = new Admin();
		if (userid != null) {
			admin.setUserid(userid);
		}
		if (email != null) {
			admin.setEmail(email);
		}
		return mapper.selectOne(admin);
	}
}
