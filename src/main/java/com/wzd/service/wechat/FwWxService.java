package com.wzd.service.wechat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.event.Event;
import com.wzd.service.wechat.msg.WxMsgReceiver;
import com.wzd.service.wechat.utils.AesException;
import com.wzd.service.wechat.utils.WXBizMsgCrypt;
import com.wzd.utils.Configs;
import com.wzd.utils.HttpUtils;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 服务号超卓服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class FwWxService {
	private static final Logger log = LogManager.getLogger(FwWxService.class);
	private static WXBizMsgCrypt wxcpt = null;

	// 获取加密协议
	public static WXBizMsgCrypt wxcpt() {
		try {
			if (wxcpt == null) {
				wxcpt = new WXBizMsgCrypt(Configs.bToken, Configs.bEncodingAESKey, Configs.bAppid);
			}
		} catch (AesException e) {
			throw new WebException(ResponseCode.未授权, "AES签名错误");
		}
		return wxcpt;
	}

	/**
	 * 处理服务号回调过来的内容
	 */
	public String push(WechatMsg msg) {
		switch (msg.getMsgType().toLowerCase()) {
		case MsgType.TEXT: // 文本消息处理
			return WxMsgReceiver.text(msg);
		case MsgType.IMAGE: // 图片消息处理
			// TODO 图片消息处理
			return XmlResp.SUCCESS;
		case MsgType.VOICE: // 语音消息处理
			// TODO 语音消息处理
			return XmlResp.SUCCESS;
		case MsgType.VIDEO: // 视频消息处理
			// TODO 视频消息处理
			return XmlResp.SUCCESS;
		case MsgType.SHORTVIDEO: // 小视频消息处理
			// TODO 小视频消息处理
			return XmlResp.SUCCESS;
		case MsgType.LOCATION: // 地理位置消息处理
			// TODO 地理位置消息处理
			return XmlResp.SUCCESS;
		case MsgType.LINK: // 链接消息处理
			// TODO 链接消息处理
			return XmlResp.SUCCESS;
		case MsgType.EVENT: // 事件处理
			return Event.push(msg);
		default:
			return XmlResp.SUCCESS;
		}
	}

	/**
	 * 验证回调URL
	 */
	public String VerifyURL(String msg_signature, String timestamp, String nonce, String echostr) {
		String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
		String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
		String sVerifyNonce = HttpUtils.ParseUrl("nonce");
		String sVerifyEchoStr = HttpUtils.ParseUrl("echostr");
		log.debug("验证回调URL...");
		String sEchoStr = null;
		try {
			sEchoStr = wxcpt().VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
		} catch (AesException e) {
			e.printStackTrace();
		}
		log.debug("验证回调URL结果：" + sEchoStr);
		return sEchoStr;
	}

}
