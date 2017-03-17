package com.wzd.service.wechat.user;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wzd.client.RestClientUtil;
import com.wzd.model.entity.Admin;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.web.dto.exception.WebException;

/**
 * 微信企业号成员操作类
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WxUserService {
	// 获取token
	private String getToken() {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		return token.getAccess_token();
	}

	/**
	 * 创建成员
	 */
	public void create(WxUser user) {
		String path = MessageFormat.format(QyAPI.CREATE_USER, getToken());
		BaseResp resp = RestClientUtil.postJson(path, user, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 更新成员
	 */
	public void update(WxUser user) {
		String path = MessageFormat.format(QyAPI.UPDATE_USER, getToken());
		BaseResp resp = RestClientUtil.postJson(path, user, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除成员
	 */
	public void delete(String userid) {
		String path = MessageFormat.format(QyAPI.DELETE_USER, getToken(), userid);
		BaseResp resp = RestClientUtil.get(path, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 批量删除成员
	 */
	public void batchDelete(List<String> userids) {
		String path = MessageFormat.format(QyAPI.BATCHDELETE_USER, getToken());
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
	public WxUser get(String userid) {
		String path = MessageFormat.format(QyAPI.GET_USER, getToken(), userid);
		WxUser resp = RestClientUtil.get(path, WxUser.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 获取部门成员
	 */
	public List<Admin> simpleList(Integer depId) {
		String path = MessageFormat.format(QyAPI.USER_SIMPLELIST, getToken(), depId);
		WxUserList resp = RestClientUtil.get(path, WxUserList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getUserlist();
	}

	/**
	 * 获取部门成员(详情)
	 */
	public List<Admin> list(Integer depId) {
		String path = MessageFormat.format(QyAPI.USER_LIST, getToken(), depId);
		WxUserList resp = RestClientUtil.get(path, WxUserList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getUserlist();
	}
}
