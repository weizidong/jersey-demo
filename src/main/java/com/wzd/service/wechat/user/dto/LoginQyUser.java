package com.wzd.service.wechat.user.dto;

import com.wzd.model.entity.Admin;

/**
 * 企业登录成员信息
 * 
 * @author WeiZiDong
 *
 */
public class LoginQyUser {
	private Integer usertype; // 登录用户的类型：1. 企业号创建者 2. 企业号内部系统管理员 3. 企业号外部系统管理员
								// 4. 企业号分级管理员 5. 企业号成员
	private Admin user_info;// 登录管理员的信息

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Admin getUser_info() {
		return user_info;
	}

	public void setUser_info(Admin user_info) {
		this.user_info = user_info;
	}

	@Override
	public String toString() {
		return "LoginQyUser [usertype=" + usertype + ", user_info=" + user_info + "]";
	}

}
