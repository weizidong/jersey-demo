package com.wzd.service.wechat.tag;

import java.util.List;

import com.wzd.model.entity.User;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 企业号--->标签成员列表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxTagUserList extends BaseResp {
	private List<User> userlist; // 企业成员ID列表，注意：userlist、partylist不能同时为空
	private List<Integer> partylist; // 企业部门ID列表，注意：userlist、partylist不能同时为空

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<Integer> getPartylist() {
		return partylist;
	}

	public void setPartylist(List<Integer> partylist) {
		this.partylist = partylist;
	}

	@Override
	public String toString() {
		return super.toString() + ", userlist=" + userlist + ", partylist=" + partylist + "}";
	}

}
