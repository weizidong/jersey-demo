package com.wzd.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.security.Credential.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.RoleType;
import com.wzd.model.mapper.UserMapper;

@Service
public class UserService {
	private static final Logger log = LogManager.getLogger(UserService.class);
	@Autowired
	private UserMapper mapper;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public void create(User user) {
		log.debug("创建用户。。。");
		user.setId(null);
		user.setUpdated(new Date());
		user.setCreated(new Date());
		user.setDeleted(DeleteType.未删除.getValue());
		user.setStatus(AuditType.未审核.getValue());
		user.setType(RoleType.普通用户.getValue());
		user.setPwd(MD5.digest(user.getPwd()));
		mapper.insert(user);
		log.debug("创建成功：", user);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param type
	 */
	public void delete(Integer id, DeleteType type) {
		log.debug("删除用户。。。");
		User user = mapper.selectByPrimaryKey(id);
		user.setDeleted(type.getValue());
		user.setUpdated(new Date());
		mapper.updateByPrimaryKey(user);
		log.debug("删除成功：", user);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public void update(User user) {
		log.debug("修改用户。。。");
		if (user.getId() == null) {
			// TODO 定义异常
			return;
		}
		user.setUpdated(new Date());
		user.setPwd(MD5.digest(user.getPwd()));
		mapper.updateByPrimaryKeySelective(user);
		log.debug("修改成功：", user);
	}

	/**
	 * 条件查询所有
	 * 
	 * @return
	 */
	public List<User> find(User user) {
		user.setId(null);
		return mapper.select(user);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public User findById(Integer id, DeleteType type) {
		User user = new User();
		user.setId(id);
		user.setDeleted(type.getValue());
		return mapper.selectOne(user);
	}
}
