package com.wzd.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.wzd.model.entity.User;
import com.wzd.model.enums.AuditType;
import com.wzd.model.mapper.UserMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 用户数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
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
	 * 更新用户(所有不为空的字段)
	 */
	public void update(User user) {
		mapper.updateByPrimaryKeySelective(user);
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
		user.setId(UUIDUtil.get());
		user.setAudit(AuditType.未审核.getValue());
		user.setUpdated(new Date());
		mapper.insertSelective(user);
	}

	/**
	 * 创建或更新user
	 */
	public void save(User user) {
		User db = getByOpenId(user.getOpenid());
		if (db == null) {
			create(user);
		} else {
			user.setId(db.getId());
			update(user);
		}
	}

	/**
	 * 根据ID获取
	 */
	public User getById(String id) {
		User u = new User();
		u.setId(id);
		return mapper.selectOne(u);
	}

	/**
	 * 查询用户列表
	 */
	public List<User> find(PageParam param) {
		Example e = new Example(User.class);
		PageParam.setCondition(e, "created", param, User.class);
		e.setOrderByClause("created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return mapper.selectByExample(e);
	}
}
