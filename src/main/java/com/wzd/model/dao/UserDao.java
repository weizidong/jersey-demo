package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.UserMapper;

/**
 * 用户数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Service
public class UserDao {
	@Autowired
	private UserMapper mapper;

	/**
	 * 根据OpneId获取用户
	 */
	public User getByOpenId(String openid) {
		User user = new User();
		user.setOpenid(openid);
		return mapper.selectOne(user);
	}

	/**
	 * 更新用户
	 */
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	/**
	 * 用户登录
	 */
	public void login(User user) {
		user.setLogin(new Date());
		mapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 创建用户
	 */
	public void create(User user) {
		user.setCreated(new Date());
		user.setDeleted(DeleteType.未删除.getValue());
		user.setStatus(AuditType.未审核.getValue());
		user.setUpdated(new Date());
		mapper.insert(user);
	}
}
