package com.wzd.service.wechat.event;

import com.wzd.service.wechat.base.MsgType;
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
	public static void push(WechatMsg msg) {
		switch (msg.getEvent().toUpperCase()) {
		case MsgType.Event.SUBSCRIBE: // 关注事件
			subscribe(msg);
			break;
		case MsgType.Event.UNSUBSCRIBE: // 取消关注事件
			unsubscribe(msg);
			break;
		case MsgType.Event.ENTER_AGENT: // 成员进入应用
			enter_agent(msg);
			break;
		default:
			break;
		}
	}

	// 处理成员进入应用
	private static void enter_agent(WechatMsg msg) {
		// TODO Auto-generated method stub

	}

	// 处理取消关注事件
	private static void unsubscribe(WechatMsg msg) {
		// TODO Auto-generated method stub

	}

	// 处理关注事件
	private static void subscribe(WechatMsg msg) {
		// TODO Auto-generated method stub

	}
}
