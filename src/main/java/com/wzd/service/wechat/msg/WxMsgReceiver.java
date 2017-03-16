package com.wzd.service.wechat.msg;

import com.wzd.service.wechat.base.XmlResp;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 微信推送消息处理
 * 
 * @author WeiZiDong
 *
 */
public class WxMsgReceiver {
	/**
	 * 处理文本消息
	 * 
	 * @param msg
	 */
	public static String text(WechatMsg msg) {
		// TODO 处理文本消息
		return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "asdasdasdasdasd");
	}
}
