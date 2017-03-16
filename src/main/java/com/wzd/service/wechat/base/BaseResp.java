package com.wzd.service.wechat.base;

import java.io.Serializable;

/**
 * 微信返回结果基类
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class BaseResp implements Serializable {
	private Integer errcode;
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return "BaseReq [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

}
