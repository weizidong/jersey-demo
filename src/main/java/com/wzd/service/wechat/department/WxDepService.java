package com.wzd.service.wechat.department;

import java.text.MessageFormat;

import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.web.dto.exception.WebException;

/**
 * 微信企业号部门操作类
 * 
 * @author WeiZiDong
 *
 */
public class WxDepService {
	/**
	 * 创建部门
	 */
	public WxDep createDep(WxDep dep) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		String path = MessageFormat.format(QyAPI.CREATE_DEPARTMENT, token.getAccess_token());
		WxDep resp = RestClientUtil.postJson(path, dep, WxDep.class);
		if (resp.getErrcode() != 0 && resp.getId() == null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		dep.setId(resp.getId());
		return dep;
	}

	/**
	 * 更新部门
	 */
	public void updateDep(WxDep dep) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		String path = MessageFormat.format(QyAPI.UPDATE_DEPARTMENT, token.getAccess_token());
		WxDep resp = RestClientUtil.postJson(path, dep, WxDep.class);
		if (resp.getErrcode() != 0 && resp.getId() == null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除部门
	 */
	public void deleteDep(Integer id) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		String path = MessageFormat.format(QyAPI.DELETE_DEPARTMENT, token.getAccess_token(), id);
		WxDep resp = RestClientUtil.get(path, WxDep.class);
		if (resp.getErrcode() != 0 && resp.getId() == null) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 获取部门列表
	 */
	public WxDepList getDepList(Integer depId) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		String path = MessageFormat.format(QyAPI.LIST_DEPARTMENT, token.getAccess_token(), depId);
		WxDepList resp = RestClientUtil.get(path, WxDepList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}
}
