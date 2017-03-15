package com.wzd.service.wechat.admin;

import java.util.List;

import com.wzd.model.entity.Admin;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 微信同步--->成员列表
 * 
 * @author WeiZiDong
 *
 */
public class AdminList extends BaseResp {
	private List<Admin> userlist;

	public List<Admin> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<Admin> userlist) {
		this.userlist = userlist;
	}

	@Override
	public String toString() {
		return "AdminList [userlist=" + userlist + "]";
	}

}
