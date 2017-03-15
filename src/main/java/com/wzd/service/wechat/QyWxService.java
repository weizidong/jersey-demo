package com.wzd.service.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wzd.utils.Configs;
import com.wzd.utils.HttpUtils;
import com.wzd.utils.JaxbUtil;
import com.wzd.utils.wechat.AesException;
import com.wzd.utils.wechat.WXBizMsgCrypt;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 企业号操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class QyWxService {
	private static final Logger log = LogManager.getLogger(QyWxService.class);
	private static WXBizMsgCrypt wxcpt = null;

	// 获取加密协议
	private static WXBizMsgCrypt wxcpt() {
		try {
			if (wxcpt == null) {
				wxcpt = new WXBizMsgCrypt(Configs.sToken, Configs.sEncodingAESKey, Configs.sCorpID);
			}
		} catch (AesException e) {
			throw new WebException(ResponseCode.未授权, "AES签名错误");
		}
		return wxcpt;
	}

	/**
	 * 处理企业号回调过来的内容
	 * 
	 * @param msg_signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param xml
	 * @param request
	 */
	public void push(String msg_signature, String timestamp, String nonce, String sReqData,
			HttpServletRequest request) {
		log.debug("接收到企业号退烧红的消息。。。");
		// URL解码
		String sReqMsgSig = HttpUtils.ParseUrl(msg_signature);
		String sReqTimeStamp = HttpUtils.ParseUrl(timestamp);
		String sReqNonce = HttpUtils.ParseUrl(nonce);
		log.debug("sReqMsgSig=" + sReqMsgSig);
		log.debug("sReqTimeStamp=" + sReqTimeStamp);
		log.debug("sReqNonce=" + sReqNonce);
		log.debug("sReqData=" + sReqData);
		// 对用户回复的消息解密
		String sMsg;
		try {
			sMsg = wxcpt().DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData);
		} catch (AesException e) {
			throw new WebException(ResponseCode.不允许此方法, "解密回复信息密文失败！");
		}
		log.debug("解密结果：" + sMsg);
		// 处理解密结果
		WechatMsg msg = JaxbUtil.converyToJavaBean(sMsg, WechatMsg.class);
		log.debug("解析结果：" + msg);
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
