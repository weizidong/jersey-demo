package com.wzd.web.dto.exception;

import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.response.RestResponse;

/**
 * 异常基类，各个模块的运行期异常均继承与该类
 * 
 * @author weizidong
 *
 */
@SuppressWarnings("serial")
public class RestException extends RuntimeException {

	/**
	 * code
	 */
	private int code;

	/**
	 * msg
	 */
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public RestException() {
	}

	public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(Throwable cause) {
		super(cause);
	}

	public RestException(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public RestException(ResponseCode exception) {
		super();
		this.code = exception.getCode();
		this.msg = exception.getMsg();
	}

	public RestException(ResponseCode exception, String msg) {
		super();
		this.code = exception.getCode();
		this.msg = RestResponse.generateMsg(exception, msg);
	}
}