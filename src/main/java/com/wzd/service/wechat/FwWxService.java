package com.wzd.service.wechat;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.client.RestClientUtil;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.User;
import com.wzd.model.enums.APPType;
import com.wzd.service.wechat.base.BaseResp;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.event.Event;
import com.wzd.service.wechat.msg.WxMsgReceiver;
import com.wzd.service.wechat.token.Token;
import com.wzd.service.wechat.user.FwUserApi;
import com.wzd.service.wechat.user.dto.FwUserList;
import com.wzd.utils.Configs;
import com.wzd.utils.SignatureUtil;
import com.wzd.web.dto.exception.WebException;
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
	@Autowired
	private UserDao dao;
	@Autowired
	private WxMsgReceiver receiver;
	@Autowired
	private Event event;

	// 获取加密协议
	// private static WXBizMsgCrypt wxcpt = null;
	// public static WXBizMsgCrypt wxcpt() {
	// try {
	// if (wxcpt == null) {
	// wxcpt = new WXBizMsgCrypt(Configs.bToken, Configs.bEncodingAESKey,
	// Configs.bAppid);
	// }
	// } catch (AesException e) {
	// throw new WebException(ResponseCode.未授权, "AES签名错误");
	// }
	// return wxcpt;
	// }

	/**
	 * 获取token
	 */
	public static String getToken() {
		Token token = Token.get(FwAPI.TOKEN, Configs.bAppid, Configs.bSecret);
		return token.getAccess_token();
	}

	/**
	 * 处理服务号回调过来的内容
	 */
	public String push(WechatMsg msg) {
		switch (msg.getMsgType().toLowerCase()) {
		case MsgType.TEXT:
			return receiver.text(msg);
		case MsgType.IMAGE:
			return receiver.img(msg);
		case MsgType.VOICE:
			return receiver.voice(msg);
		case MsgType.VIDEO:
			return receiver.video(msg);
		case MsgType.SHORTVIDEO:
			return receiver.shortvideo(msg);
		case MsgType.LOCATION:
			return receiver.location(msg);
		case MsgType.LINK:
			return receiver.link(msg);
		case MsgType.EVENT:
			return event.push(msg);
		default:
			return XmlResp.SUCCESS;
		}
	}

	/**
	 * 验证回调URL
	 */
	public String VerifyURL(String signature, String timestamp, String nonce, String echostr) {
		Boolean check = SignatureUtil.checkSignature(Configs.bToken, signature, timestamp, nonce);
		log.debug("验证回调URL：" + check);
		if (check) {
			return echostr;
		}
		return null;
	}

	/**
	 * 根据Code拉取用户信息
	 */
	public Session getUserInfo(String code) {
		Token token = RestClientUtil.get(MessageFormat.format(FwAPI.ACCESS_TOKEN, Configs.bAppid, Configs.bSecret, code), Token.class);
		if (token.getErrcode() != null) {
			throw new WebException(token.getErrcode(), token.getErrmsg());
		}
		Session session = new Session();
		session.setSessionId(token.getOpenid());
		session.setAccessToken(token.getAccess_token());
		session.setAppType(APPType.服务号);
		User user = dao.getByOpenId(token.getOpenid());
		// 无用户，创建
		if (user == null) {
			// 拉取用户信息
			user = FwUserApi.get(token.getAccess_token(), token.getOpenid());
			user.setLogin(new Date());
			dao.create(user);
		} else {
			dao.login(user);
		}
		session.setUser(user);
		return session;
	}

	/**
	 * 获取菜单
	 */
	public String getFwMenu() {
		return RestClientUtil.get(MessageFormat.format(FwAPI.GET_MENU, getToken()), String.class);
	}

	/**
	 * 创建菜单
	 */
	public void createFwMenu(String menu) {
		BaseResp resp = RestClientUtil.postJson(MessageFormat.format(FwAPI.CREATE_MENU, getToken()), menu, BaseResp.class);
		if (resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 删除菜单
	 */
	public void deleteFwMenu() {
		BaseResp resp = RestClientUtil.get(MessageFormat.format(FwAPI.DELETE_MENU, getToken()), BaseResp.class);
		if (resp == null || resp.getErrcode() != 0) {
			throw new WebException(resp.getErrcode(), resp.getErrmsg());
		}
	}

	/**
	 * 同步服务号获取用户列表
	 */
	public void syncUser(String next_openid) {
		if (next_openid != null && next_openid.equals("OVER")) {
			return;
		}
		FwUserList resp = FwUserApi.getList(next_openid);
		List<String> openids = resp.getOpenids();
		if (openids == null || openids.size() == 0) {
			return;
		}
		openids.forEach(openid -> dao.save(FwUserApi.get(openid)));
		syncUser(resp.getNext_openid() == null ? "OVER" : resp.getNext_openid());
	}
}
