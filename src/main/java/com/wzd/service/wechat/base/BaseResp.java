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
	private String invaliduser;
	private String invalidparty;
	private String invalidtag;

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

	public String getInvaliduser() {
		return invaliduser;
	}

	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}

	public String getInvalidparty() {
		return invalidparty;
	}

	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}

	public String getInvalidtag() {
		return invalidtag;
	}

	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}

	@Override
	public String toString() {
		return "{errcode=" + errcode + ", errmsg=" + errmsg + ", invaliduser=" + invaliduser + ", invalidparty=" + invalidparty + ", invalidtag=" + invalidtag;
	}

}
