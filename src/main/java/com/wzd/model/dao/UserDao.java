package com.wzd.model.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.wzd.model.entity.User;
import com.wzd.model.mapper.UserMapper;
import com.wzd.utils.MyBatisUtil;

public class UserDao {

	public void insert() {
		SqlSession sqlSession = MyBatisUtil.getSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setName("laowei");
		user.setBirthday(new Date());
		user.setSex(1);
		mapper.insert(user);
		sqlSession.close();
	}
}
