package com.wzd.service.wechat.user;

import java.util.List;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 微信同步--->成员列表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxUserList extends BaseResp {
	private List<WxUser> userlist;

	public List<WxUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<WxUser> userlist) {
		this.userlist = userlist;
	}

	@Override
	public String toString() {
		return super.toString() + ", userlist:" + userlist + "}";
	}

}
