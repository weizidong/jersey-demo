package com.wzd.service.wechat.base;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * 微信返回结果基类
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class BaseResp implements Serializable {
	@Transient
	private Integer errcode;
	@Transient
	private String errmsg;
	@Transient
	private String invaliduser;
	@Transient
	private String invalidparty;
	@Transient
	private String invalidtag;
	@Transient
	private String invalidlist;

	public String getInvalidlist() {
		return invalidlist;
	}

	public void setInvalidlist(String invalidlist) {
		this.invalidlist = invalidlist;
	}

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
		return "{errcode=" + errcode + ", errmsg=" + errmsg + ", invaliduser=" + invaliduser + ", invalidparty=" + invalidparty + ", invalidtag=" + invalidtag + ", invalidlist="
				+ invalidlist;
	}

}
