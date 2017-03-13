package com.wzd.web.filter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.web.dto.exception.RestException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 统一异常处理器
 * 
 * @author weizidong
 *
 */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
	private static final Logger log = LogManager.getLogger(ExceptionMapperSupport.class);

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	/**
	 * 异常处理
	 * 
	 * @param exception
	 * @return 异常处理后的Response对象
	 */
	public Response toResponse(Exception exception) {
		// 处理unchecked exception
		RestException restException = new RestException(ResponseCode.服务器异常);
		if (exception instanceof RestException) {
			restException = (RestException) exception;
		} else if (exception instanceof NotFoundException) {
			restException = new RestException(ResponseCode.资源不存在);
		}
		// checked exception和unchecked exception均被记录在日志里
		log.error(restException.getMsg(), exception);
		return Response.status(200).entity(restException).build();
	}
}