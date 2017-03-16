package com.wzd.service.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.event.Event;
import com.wzd.service.wechat.msg.WxMsgReceiver;
import com.wzd.service.wechat.utils.AesException;
import com.wzd.service.wechat.utils.WXBizMsgCrypt;
import com.wzd.service.wechat.utils.WeChatXmlUtil;
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
	private static WXBizMsgCrypt wxcpt() {
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
	 * 
	 * @param msg_signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param xml
	 * @param request
	 */
	public void push(String msg_signature, String timestamp, String nonce, String sReqData, HttpServletRequest request) {
		log.debug("接收到服务号推送的消息。。。");
		// URL解码
		String sReqMsgSig = HttpUtils.ParseUrl(msg_signature);
		String sReqTimeStamp = HttpUtils.ParseUrl(timestamp);
		String sReqNonce = HttpUtils.ParseUrl(nonce);
		// 对用户回复的消息解密
		String sMsg;
		try {
			sMsg = wxcpt().DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData);
		} catch (AesException e) {
			throw new WebException(ResponseCode.不允许此方法, "解密回复信息密文失败！");
		}
		log.debug("解密结果：" + sMsg);
		// 解析xml
		WechatMsg msg = WeChatXmlUtil.xmlToBean(sMsg, WechatMsg.class);
		switch (msg.getMsgType().toLowerCase()) {
		case MsgType.TEXT: // 文本消息处理
			WxMsgReceiver.text(msg);
			break;
		case MsgType.IMAGE: // 图片消息处理
			// TODO 图片消息处理
			break;
		case MsgType.VOICE: // 语音消息处理
			// TODO 语音消息处理
			break;
		case MsgType.VIDEO: // 视频消息处理
			// TODO 视频消息处理
			break;
		case MsgType.SHORTVIDEO: // 小视频消息处理
			// TODO 小视频消息处理
			break;
		case MsgType.LOCATION: // 地理位置消息处理
			// TODO 地理位置消息处理
			break;
		case MsgType.LINK: // 链接消息处理
			// TODO 链接消息处理
			break;
		case MsgType.EVENT: // 事件处理
			Event.push(msg);
			break;
		}
	}

	/**
	 * 验证回调URL
	 * 
	 * @param msg_signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
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
