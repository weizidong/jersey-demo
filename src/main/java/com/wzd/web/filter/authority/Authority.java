package com.wzd.web.filter.authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wzd.model.enums.AuthType;

/**
 * 接口验证
 * 
 * @author WeiZiDong
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
	/**
	 * 默认具有所有权限
	 * 
	 * @return
	 */
	AuthType[] value();
}
