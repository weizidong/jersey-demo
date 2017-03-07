package com.wzd.utils;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis工具类
 * 
 * TODO:数据库连接默认为自动提交autoCommit=true TODO:提供开始事务、提交事务方法
 * 
 * @author juzhi
 *
 */
public class MyBatisUtil {

	/**
	 * 配置路径
	 */
	private static final String CONFIG_RESOURCE = "configs/mybatis-config.xml";

	/**
	 * MyBatis会话工厂
	 */
	private static SqlSessionFactory sqlSessionFactory = null;

	/**
	 * 线程会话，保证同一线程共用同一个SqlSession对象
	 */
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	/**
	 * 初始化SqlSessionFactory
	 */
	static {

		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(CONFIG_RESOURCE);
		} catch (IOException e) {
			throw new RuntimeException("Get resource error:" + CONFIG_RESOURCE, e);
		}

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	/**
	 * 重新创建SqlSessionFactory
	 */
	public static void rebuildSqlSessionFactory() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(CONFIG_RESOURCE);
		} catch (IOException e) {
			throw new RuntimeException("Get resource error:" + CONFIG_RESOURCE, e);
		}

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**
	 * 获取sqlSession
	 */
	public static SqlSession getSession() {
		SqlSession session = threadLocal.get();

		if (session == null) {
			session = sqlSessionFactory.openSession(true);
			threadLocal.set(session);
		}

		return session;
	}

	/**
	 * 关闭sqlSession
	 */
	public static void close() {
		SqlSession session = threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 开始事务,关闭当前线程sqlSession，重新开启一个sqlSession，并设置autoCommit = false
	 */
	public static void begin() {

//		close();

		SqlSession session = getSession();
		
		try {
			session.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("开启事务发生异常。", e);
		}

	}

	/**
	 * 提交sqlSession
	 */
	public static void commit() {
		SqlSession session = threadLocal.get();

		if (session != null) {
			// 强制提交事务
			session.commit(true);
//			close();
		}
	}

	/**
	 * 回滚SqlSession
	 */
	public static void rollback() {
		SqlSession session = threadLocal.get();

		if (session != null) {
			// 强制回滚事务
			session.rollback(true);
//			close();
		}

	}

	/**
	 * 获取Mapper
	 * 
	 * @param mapperClass
	 * @return
	 */
	public static <T> T getMapper(Class<T> mapperClass) {
		return getSession().getMapper(mapperClass);
	}

}