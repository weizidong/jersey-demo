package com.wzd.service.wechat;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.client.RestClientUtil;
import com.wzd.model.dao.AdminDao;
import com.wzd.model.dao.DepartmentDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Department;
import com.wzd.model.enums.APPType;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.QyAPI;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.department.WxDepService;
import com.wzd.service.wechat.event.Event;
import com.wzd.service.wechat.msg.WxMsgReceiver;
import com.wzd.service.wechat.token.Token;
import com.wzd.service.wechat.user.QyUser;
import com.wzd.service.wechat.user.WxUserService;
import com.wzd.service.wechat.utils.AesException;
import com.wzd.service.wechat.utils.WXBizMsgCrypt;
import com.wzd.utils.Configs;
import com.wzd.utils.HttpUtils;
import com.wzd.utils.SignatureUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;
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
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private DepartmentDao depDao;
	@Autowired
	private WxUserService userservice;
	@Autowired
	private WxDepService depService;
	@Autowired
	private WxMsgReceiver receiver;

	// 获取加密协议
	public static WXBizMsgCrypt wxcpt() {
		try {
			if (wxcpt == null) {
				wxcpt = new WXBizMsgCrypt(Configs.sToken, Configs.sEncodingAESKey, Configs.sCorpID);
			}
		} catch (AesException e) {
			throw new WebException(ResponseCode.未授权, "AES签名错误");
		}
		return wxcpt;
	}

	// 获取token
	private static String getToken() {
		Token token = Token.get(QyAPI.GETTOKEN, Configs.sCorpID, Configs.sSecret);
		return token.getAccess_token();
	}

	/**
	 * 处理企业号回调过来的内容
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

	/**
	 * 根据code获取成员信息
	 */
	public Session getUserInfo(String code) {
		QyUser user = RestClientUtil.get(MessageFormat.format(QyAPI.GETUSERINFO, getToken(), code), QyUser.class);
		if (user.getErrcode() != null) {
			throw new WebException(user.getErrcode(), user.getErrmsg());
		}
		// 非企业成员
		if (user.getOpenId() != null) {
			throw new WebException(ResponseCode.未授权, "不是企业成员");
		}
		// 企业成员
		Session session = new Session();
		if (user.getUserId() != null) {
			session.setAccessToken(SignatureUtil.generateToke());
			session.setSessionId(user.getUserId());
			session.setAppType(APPType.企业号.getValue());
			Admin admin = adminDao.getByUserId(user.getUserId());
			session.setUser(admin);
		}
		return session;
	}

	/**
	 * 同步部门和部门成员
	 */
	public String sync() {
		List<Department> deps = depService.getDepList(null);
		deps.forEach(dep -> {
			depDao.save(dep);
			List<Admin> admins = userservice.list(dep.getId());
			admins.forEach(admin -> {
				admin.setDepartments(admin.getDepartment());
				adminDao.save(admin);
			});
		});
		return XmlResp.SUCCESS;
	}

}
