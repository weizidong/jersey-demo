package com.wzd.service.wechat.event;

import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 微信事件消息处理
 * 
 * @author WeiZiDong
 *
 */
public class Event {
	/**
	 * 处理事件
	 * 
	 * @param msg
	 */
	public static String push(WechatMsg msg) {
		switch (msg.getEvent().toUpperCase()) {
		case MsgType.Event.SUBSCRIBE: // 关注事件
			return subscribe(msg);
		case MsgType.Event.UNSUBSCRIBE: // 取消关注事件
			return unsubscribe(msg);
		case MsgType.Event.ENTER_AGENT: // 成员进入应用
			return enter_agent(msg);
		default:
			return XmlResp.SUCCESS;
		}
	}

	// 处理成员进入应用
	private static String enter_agent(WechatMsg msg) {
		// TODO Auto-generated method stub
		return XmlResp.SUCCESS;

	}

	// 处理取消关注事件
	private static String unsubscribe(WechatMsg msg) {
		// TODO Auto-generated method stub
		return XmlResp.SUCCESS;

	}

	// 处理关注事件
	private static String subscribe(WechatMsg msg) {
		// TODO Auto-generated method stub
		return XmlResp.SUCCESS;

	}
}