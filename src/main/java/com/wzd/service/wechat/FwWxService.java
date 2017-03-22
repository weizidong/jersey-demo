package com.wzd.service.wechat;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.client.RestClientUtil;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.User;
import com.wzd.model.enums.APPType;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.event.Event;
import com.wzd.service.wechat.msg.WxMsgReceiver;
import com.wzd.service.wechat.token.Token;
import com.wzd.service.wechat.utils.AesException;
import com.wzd.service.wechat.utils.WXBizMsgCrypt;
import com.wzd.utils.Configs;
import com.wzd.utils.SignatureUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 服务号操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class FwWxService {
	private static final Logger log = LogManager.getLogger(FwWxService.class);
	private static WXBizMsgCrypt wxcpt = null;
	@Autowired
	private UserDao dao;
	@Autowired
	private WxMsgReceiver receiver;

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

	// 获取token
	public String getToken() {
		Token token = Token.get(FwAPI.TOKEN_URL, Configs.bAppid, Configs.bSecret);
		return token.getAccess_token();
	}

	/**
	 * 处理服务号回调过来的内容
	 */
	public String push(WechatMsg msg) {
		switch (msg.getMsgType().toLowerCase()) {
		case MsgType.TEXT: // 文本消息处理
			return receiver.text(msg);
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
	public String VerifyURL(String signature, String timestamp, String nonce, String echostr) {
		log.debug("验证回调URL...");
		Boolean check = SignatureUtil.checkSignature(Configs.bToken, signature, timestamp, nonce);
		log.debug("验证回调URL结果：" + check);
		if (check) {
			return echostr;
		}
		return null;
	}

	/**
	 * 拉取用户信息
	 */
	public Session getUserInfo(String code) {
		Token token = RestClientUtil.get(MessageFormat.format(FwAPI.GET_ACCESS_TOKEN_URL, Configs.bAppid, Configs.bSecret, code), Token.class);
		if (token.getErrcode() != null) {
			throw new WebException(token.getErrcode(), token.getErrmsg());
		}
		Session session = new Session();
		session.setSessionId(token.getOpenid());
		session.setAccessToken(token.getAccess_token());
		session.setAppType(APPType.服务号.getValue());
		User user = dao.getByOpenId(token.getOpenid());
		// 无用户，创建
		if (user == null) {
			// 拉取用户信息
			user = RestClientUtil.get(MessageFormat.format(FwAPI.GET_USERINFO_URL, token.getAccess_token(), token.getOpenid()), User.class);
			dao.create(user);
		} else {
			dao.login(user);
		}
		session.setUser(user);
		return session;
	}

}
