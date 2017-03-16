package com.wzd.service.wechat.department;

import java.io.Serializable;
import java.util.List;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 企业号--->部门列表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxDepList extends BaseResp implements Serializable {
	private List<WxDep> department;

	public List<WxDep> getDepartment() {
		return department;
	}

	public void setDepartment(List<WxDep> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return super.toString() + ", department=" + department + "}";
	}

}
