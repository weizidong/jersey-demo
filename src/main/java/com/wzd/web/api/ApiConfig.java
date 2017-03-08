package com.wzd.web.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;
/**
 * 接口注册
 * @author wzd
 *
 */
@ApplicationPath("/")
public class ApiConfig extends ResourceConfig {

	public ApiConfig() {
		this.packages(this.getClass().getPackage().getName());
		  
        //用 Jackson JSON 的提供者来解释 JSON  
        register(JacksonFeature.class);  
        
        //Spring filter 提供了 JAX-RS 和 Spring 请求属性之间的桥梁  
        register(RequestContextFilter.class);  
//		// 注册异常映射
//		register(HibernateExceptionMapper.class);
//
//		// 注册关闭hibernate会话过滤器
//		register(HibernateCloseSessionFilter.class);
//
//		// 注册JSON
//		register(HibernateObjectMapperResolver.class);
//		
//		register(JacksonFeature.class);
//
//		// 注册多媒体功能
//		register(MultiPartFeature.class);
//
//		// 注册请求日志过滤器
//		register(RequestLogDynamicFeature.class);
//
//		// 将返回值转换为JsonResponse
//		register(FormatJsonDynamicFeature.class);
//		register(CloseJedisFilter.class);
	}

}
