package com.wzd.service.wechat.department;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wzd.client.RestClientUtil;
import com.wzd.model.entity.Department;
import com.wzd.service.wechat.base.BaseResp;
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
@Service
public class WxDepService {
	// 获取token
	private String getToken() {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		return token.getAccess_token();
	}

	/**
	 * 创建部门
	 */
	public WxDep create(WxDep dep) {
		String path = MessageFormat.format(QyAPI.CREATE_DEPARTMENT, getToken());
		WxDep resp = RestClientUtil.postJson(path, dep, WxDep.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		dep.setId(resp.getId());
		return dep;
	}

	/**
	 * 更新部门
	 */
	public void update(WxDep dep) {
		String path = MessageFormat.format(QyAPI.UPDATE_DEPARTMENT, getToken());
		BaseResp resp = RestClientUtil.postJson(path, dep, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除部门
	 */
	public void delete(Integer depId) {
		String path = MessageFormat.format(QyAPI.DELETE_DEPARTMENT, getToken(), depId);
		BaseResp resp = RestClientUtil.get(path, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 获取部门列表
	 */
	public List<Department> getDepList(Integer depId) {
		String path = MessageFormat.format(QyAPI.LIST_DEPARTMENT, getToken(), depId);
		WxDepList resp = RestClientUtil.get(path, WxDepList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getDepartment();
	}
}
