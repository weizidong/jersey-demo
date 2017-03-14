//package com.wzd.web.filter.authority;
//
//import java.lang.reflect.Method;
//
//import javax.ws.rs.container.DynamicFeature;
//import javax.ws.rs.container.ResourceInfo;
//import javax.ws.rs.core.FeatureContext;
//
///**
// * 动态绑定格式化权限校验
// * 
// * @author WeiZiDong
// *
// */
//public class AuthorityDynamicFeature implements DynamicFeature {
//
//	@Override
//	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
//		// 获取资源方法
//		Method resourceMethod = resourceInfo.getResourceMethod();
//		if (resourceMethod != null) {
//			// 获取Authority注解
//			Authority authority = resourceMethod.getAnnotation(Authority.class);
//			// 未设置权限的接口默认不校验
//			if (authority == null) {
//				return;
//			}
//			// 注册验证器
//			context.register(AuthorityFilter.class);
//		}
//	}
//
//}
