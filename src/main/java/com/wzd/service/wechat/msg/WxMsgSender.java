package com.wzd.service.wechat.msg;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.msg.dto.MPNEWS;
import com.wzd.service.wechat.msg.dto.NEWS;
import com.wzd.service.wechat.msg.dto.TEXT;
import com.wzd.service.wechat.token.Token;
import com.wzd.utils.Configs;
import com.wzd.utils.ThreadPoolUtils;
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
	 */
	private static void send(String path, String token, WxMsg msg) {
		String uri = MessageFormat.format(path, token);
		BaseResp resp = RestClientUtil.postJson(uri, msg, BaseResp.class);
		if (resp != null && resp.getErrcode() != 0) {
			log.error(resp);
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 推送消息到企业号
	 */
	public static void sendToQy(WxMsg msg) {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		send(QyAPI.SEND_MSG, token.getAccess_token(), msg);
	}

	/**
	 * 推送消息到服务号
	 */
	public static void sendToFw(WxMsg msg) {
		Token token = Token.get(FwAPI.TOKEN, Configs.bAppid, Configs.bSecret);
		send(FwAPI.SEND_CUSTOM, token.getAccess_token(), msg);
	}

	/**
	 * 批量推送到服务号
	 */
	public static void batchSendToFw(List<String> openids, TEXT text) {
		if (openids == null || openids.size() <= 0) {
			return;
		}
		openids.forEach((openid) -> {
			ThreadPoolUtils.execute(() -> {
				sendToFw(new WxMsg(openid, text));
			});
		});
	}

	/**
	 * 批量推送到服务号
	 */
	public static void batchSendToFw(List<String> openids, NEWS text) {
		if (openids == null || openids.size() <= 0) {
			return;
		}
		openids.forEach((openid) -> {
			ThreadPoolUtils.execute(() -> {
				sendToFw(new WxMsg(openid, text));
			});
		});
	}

	/**
	 * 批量推送到服务号
	 */
	public static void batchSendToFw(List<String> openids, MPNEWS text) {
		if (openids == null || openids.size() <= 0) {
			return;
		}
		openids.forEach((openid) -> {
			ThreadPoolUtils.execute(() -> {
				sendToFw(new WxMsg(openid, text));
			});
		});
	}

}
