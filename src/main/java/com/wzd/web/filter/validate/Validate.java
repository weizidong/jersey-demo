package com.wzd.web.filter.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口校验注解
 * 
 * @author weizidong
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
	/**
	 * 默认需要验证
	 * 
	 * @return
	 */
	ValidateType value();
}
