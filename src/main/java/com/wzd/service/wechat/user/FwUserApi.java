package com.wzd.service.wechat.user;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.wzd.client.RestClientUtil;
import com.wzd.model.entity.User;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.user.dto.FwUserList;
import com.wzd.web.dto.exception.WebException;

/**
 * 微信服务号用户API
 * 
 * @author WeiZiDong
 *
 */
public class FwUserApi {
	/**
	 * 获取用户详情
	 */
	public static User get(String openid) {
		String path = MessageFormat.format(FwAPI.USER_INFO, FwWxService.getToken(), openid);
		User resp = RestClientUtil.get(path, User.class);
		if (resp.getErrcode()  != null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 获取用户(根据临时授权码拉取用户信息)
	 */
	public static User get(String access_token, String openid) {
		String path = MessageFormat.format(FwAPI.USERINFO, access_token, openid);
		User resp = RestClientUtil.get(path, User.class);
		if (resp.getErrcode() != null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 获取用户列表(仅openId)
	 */
	public static FwUserList getList(String next_openid) {
		String path = MessageFormat.format(FwAPI.GET_USER, FwWxService.getToken(), next_openid);
		FwUserList resp = RestClientUtil.get(path, FwUserList.class);
		if (resp.getErrcode() != null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 批量获取用户信息
	 */
	public static List<User> batchGet(List<String> openids) {
		String path = MessageFormat.format(FwAPI.BATCHGET_INFO, FwWxService.getToken());
		Map<String, List<Map<String, String>>> param = new HashMap<>();
		List<Map<String, String>> userList = openids.stream().map(openid -> {
			Map<String, String> user = new HashMap<>();
			user.put("openid", openid);
			user.put("lang", "zh-CN");
			return user;
		}).collect(Collectors.toList());
		param.put("user_list", userList);
		FwUserList resp = RestClientUtil.postJson(path, param, FwUserList.class);
		if (resp.getErrcode() != null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getUser_info_list();
	}

	/**
	 * 设置用户备注名
	 */
	public static void updateRemark(String openid, String remark) {
		String path = MessageFormat.format(FwAPI.UPDATEREMARK, FwWxService.getToken());
		Map<String, String> param = new HashMap<>();
		param.put("openid", openid);
		param.put("remark", remark);
		BaseResp resp = RestClientUtil.postJson(path, param, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}
}
