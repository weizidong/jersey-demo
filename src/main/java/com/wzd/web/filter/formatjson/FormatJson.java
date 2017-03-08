package com.wzd.web.filter.formatjson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatJson {

	/**
	 * 默认需要事务
	 * 
	 * @return
	 */
	FormatJsonType value();
}
