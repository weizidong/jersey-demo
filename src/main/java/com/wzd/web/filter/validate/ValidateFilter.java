package com.wzd.web.filter.validate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.utils.SessionUtil;
import com.wzd.web.filter.UrlFilter;

/**
 * rest响应过滤器，用于将返回值转为RestResponse
 * 
 * @author LinHaobin
 *
 */
public class ValidateFilter implements ContainerRequestFilter {
	public String version = UrlFilter.getVersion();
	private static final Logger log = LogManager.getLogger(ValidateFilter.class);
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		log.debug("请求：" + request.getRequestURI());
		if (!SessionUtil.isLogin(request, response)) {
			response.sendRedirect("/index.html?v=" + version);
			return;
		}
	}
}
