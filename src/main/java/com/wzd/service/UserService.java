package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.User;
import com.wzd.model.enums.DeleteType;
import com.wzd.web.dto.PageDto;
import com.wzd.web.param.PageParam;

/**
 * 用户业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDao dao;

	/**
	 * 创建
	 */
	public void create(User user) {
		dao.create(user);

	}

	/**
	 * 删除
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO 删除

	}

	/**
	 * 修改
	 */
	public void update(User user) {
		// TODO 修改

	}

	/**
	 * 查询指定id用户
	 */
	public User findById(Integer id, DeleteType type) {
		// TODO 查询指定id用户
		return null;
	}

	/**
	 * 条件查询列表
	 */
	public PageDto find(PageParam param) {
		// TODO 条件查询列表
		return null;
	}
}
