package com.wzd.web.filter.validate;

import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * 动态绑定格式化验证对象
 * 
 * @author WeiZiDong
 *
 */
public class ValidateDynamicFeature implements DynamicFeature {
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		// 获取资源方法
		Method resourceMethod = resourceInfo.getResourceMethod();
		if (resourceMethod != null) {
			// 获取Validate注解
			Validate validate = resourceMethod.getAnnotation(Validate.class);
			// 类型
			ValidateType validateType = null;
			// 注解为空默认为需要验证
			if (validate == null) {
				validateType = ValidateType.VALIDATE;
			} else {
				// 获取注解的值
				validateType = validate.value();
			}
			// 需要验证
			if (validateType != ValidateType.NOVALIDATE) {
				context.register(ValidateFilter.class);
			}
		}
	}
}
