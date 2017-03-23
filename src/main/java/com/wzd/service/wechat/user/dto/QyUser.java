package com.wzd.service.wechat.user.dto;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 根据code获取的成员信息
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class QyUser extends BaseResp {
	private String UserId; // 非企业成员的标识，对当前企业号唯一
	private String DeviceId; // 手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响)
	private String OpenId; // 非企业成员的标识，对当前企业号唯一

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	@Override
	public String toString() {
		return super.toString() + ", UserId=" + UserId + ", DeviceId=" + DeviceId + ", OpenId=" + OpenId + "}";
	}

}
