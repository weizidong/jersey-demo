package com.wzd.service.wechat.user;

import java.text.MessageFormat;

import com.wzd.client.RestClientUtil;
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
public class WxUserService {
	/**
	 * 创建成员
	 */
	public WxUser createDep(WxUser user) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		String path = MessageFormat.format(QyAPI.CREATE_USER, token.getAccess_token());
		WxUser resp = RestClientUtil.postJson(path, user, WxUser.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
		return user;
	}
}
