package com.wzd.service.wechat.user;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzd.client.RestClientUtil;
import com.wzd.model.entity.Admin;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.user.dto.WxUserList;
import com.wzd.web.dto.exception.WebException;

/**
 * 微信企业号成员操作类
 * 
 * @author WeiZiDong
 *
 */
public class QyUserApi {

	/**
	 * 创建成员
	 */
	public static void create(Admin user) {
		String path = MessageFormat.format(QyAPI.CREATE_USER, QyWxService.getToken());
		BaseResp resp = RestClientUtil.postJson(path, user, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 更新成员
	 */
	public static void update(Admin user) {
		String path = MessageFormat.format(QyAPI.UPDATE_USER, QyWxService.getToken());
		BaseResp resp = RestClientUtil.postJson(path, user, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除成员
	 */
	public static void delete(String userid) {
		String path = MessageFormat.format(QyAPI.DELETE_USER, QyWxService.getToken(), userid);
		BaseResp resp = RestClientUtil.get(path, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 批量删除成员
	 */
	public static void batchDelete(List<String> userids) {
		String path = MessageFormat.format(QyAPI.BATCHDELETE_USER, QyWxService.getToken());
		Map<String, Object> param = new HashMap<>();
		param.put("useridlist", userids);
		BaseResp resp = RestClientUtil.postJson(path, param, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 获取成员
	 */
	public static Admin get(String userid) {
		String path = MessageFormat.format(QyAPI.GET_USER, QyWxService.getToken(), userid);
		Admin resp = RestClientUtil.get(path, Admin.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 获取部门成员
	 */
	public static List<Admin> simpleList(Integer depId) {
		String path = MessageFormat.format(QyAPI.USER_SIMPLELIST, QyWxService.getToken(), depId);
		WxUserList resp = RestClientUtil.get(path, WxUserList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getUserlist();
	}

	/**
	 * 获取部门成员(详情)
	 */
	public static List<Admin> list(Integer depId) {
		String path = MessageFormat.format(QyAPI.USER_LIST, QyWxService.getToken(), depId);
		WxUserList resp = RestClientUtil.get(path, WxUserList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getUserlist();
	}
}
