package com.wzd.service.wechat.msg;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.web.dto.exception.WebException;

/**
 * 发送消息到微信
 * 
 * @author WeiZiDong
 *
 */
public class WxMsgSender {
	private static final Logger log = LogManager.getLogger(WxMsgSender.class);

	/**
	 * 发送消息
	 * 
	 * @param path
	 * @param token
	 * @param msg
	 */
	private static void sendText(String path, String token, WxMsg msg) {
		String uri = MessageFormat.format(path, token);
		BaseResp resp = RestClientUtil.postJson(uri, msg, BaseResp.class);
		if (resp == null || resp.getErrcode() != 0) {
			log.error(resp);
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 推送消息到企业号
	 * 
	 * @param msg
	 */
	public static void sendTextToQy(WxMsg msg) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		sendText(QyAPI.SEND_MSG, token.getAccess_token(), msg);
	}

	/**
	 * 推送消息到服务号
	 * 
	 * @param msg
	 */
	public static void sendTextToFw(WxMsg msg) {
		Token token = Token.get(FwAPI.TOKEN, Configs.bAppid, Configs.bSecret);
		sendText(FwAPI.SEND_CUSTOM, token.getAccess_token(), msg);
	}

}
