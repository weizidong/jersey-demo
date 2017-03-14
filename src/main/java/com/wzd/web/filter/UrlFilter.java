package com.wzd.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.utils.PropertiesUtil;
import com.wzd.utils.SessionUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 页面请求过滤分发
 * 
 * @author WeiZiDong
 *
 */
public class UrlFilter implements Filter {
	private static final Logger log = LogManager.getLogger(UrlFilter.class);
	public static String version = "1.0.0";
	public static String page = "index.html";
	public static String PROPERTIES = "configs/jdbc.properties";

	static {
		try {
			version = PropertiesUtil.readValue(PROPERTIES, "version");
			page = PropertiesUtil.readValue(PROPERTIES, "page");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("启动成功！版本：" + version);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (!SessionUtil.isLogin(httpRequest, httpResponse)) {
			throw new WebException(ResponseCode.未授权, "未登录");
		}
		request.getRequestDispatcher("/" + page + "?" + version).forward(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String ver) {
		version = ver;
	}

}
