package com.wzd.web.filter.log;

/**
 * 事务类型
 * 
 * @author wzd
 *
 */
public enum RequestLogType {

	/**
	 * 需要事务
	 */
	REQUIRED,
	/**
	 * 不需要事务
	 */
	NOTSUPPORTED
}
