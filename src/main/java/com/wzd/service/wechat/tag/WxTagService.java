package com.wzd.service.wechat.tag;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.web.dto.exception.WebException;

/**
 * 微信企业号标签操作类
 * 
 * @author WeiZiDong
 *
 */
public class WxTagService {
	// 获取token
	private String getToken() {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		return token.getAccess_token();
	}

	/**
	 * 创建标签
	 */
	public WxTag create(WxTag tag) {
		String path = MessageFormat.format(QyAPI.CREATE_TAG, getToken());
		WxTag resp = RestClientUtil.postJson(path, tag, WxTag.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		tag.setTagid(resp.getTagid());
		return tag;
	}

	/**
	 * 更新标签名字
	 */
	public void update(WxTag tag) {
		String path = MessageFormat.format(QyAPI.UPDATE_TAG, getToken());
		BaseResp resp = RestClientUtil.postJson(path, tag, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除标签
	 */
	public void delete(String tagid) {
		String path = MessageFormat.format(QyAPI.DELETE_TAG, getToken(), tagid);
		BaseResp resp = RestClientUtil.get(path, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 获取标签成员
	 */
	public WxTagUserList get(String tagid) {
		String path = MessageFormat.format(QyAPI.GET_TAG, getToken(), tagid);
		WxTagUserList resp = RestClientUtil.get(path, WxTagUserList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp;
	}

	/**
	 * 增加标签成员
	 */
	public void addTagUsers(String tagid, List<String> userlist, List<Integer> partylist) {
		String path = MessageFormat.format(QyAPI.ADDTAGUSERS, getToken());
		Map<String, Object> param = new HashMap<>();
		param.put("tagid", tagid);
		param.put("userlist", userlist);
		param.put("partylist", partylist);
		BaseResp resp = RestClientUtil.postJson(path, param, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		if (resp.getInvalidparty() != null || resp.getInvalidlist() != null) {
			throw new WebException(resp.getErrcode(), resp.toString());
		}
	}

	/**
	 * 删除标签成员
	 */
	public void delTagUsers(String tagid, List<String> userlist, List<Integer> partylist) {
		String path = MessageFormat.format(QyAPI.DELTAGUSERS, getToken(), tagid);
		Map<String, Object> param = new HashMap<>();
		param.put("tagid", tagid);
		param.put("userlist", userlist);
		param.put("partylist", partylist);
		BaseResp resp = RestClientUtil.postJson(path, param, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		if (resp.getInvalidparty() != null || resp.getInvalidlist() != null) {
			throw new WebException(resp.getErrcode(), resp.toString());
		}
	}

	/**
	 * 获取标签列表
	 */
	public List<WxTag> list() {
		String path = MessageFormat.format(QyAPI.TAG_LIST, getToken());
		WxTagList resp = RestClientUtil.get(path, WxTagList.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return resp.getTaglist();
	}
}
