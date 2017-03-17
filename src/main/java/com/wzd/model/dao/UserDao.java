package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.mapper.UserMapper;

/**
 * 用户数据库操作
 * 
 * @author WeiZiDong
 *
 */
public class UserDao {
	@Autowired
	private UserMapper mapper;
}
