package com.wzd.web.filter.authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wzd.model.enums.RoleType;

/**
 * 允许权限
 * 
 * @author WeiZiDong
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
	/**
	 * 默认普通权限
	 * 
	 * @return
	 */
	RoleType[] value() default { RoleType.普通医生 };
}
