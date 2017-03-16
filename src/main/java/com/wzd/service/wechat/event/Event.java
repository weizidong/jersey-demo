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

	public static void push(WechatMsg msg) {
		String event = msg.getEvent();
		if (event.equals(MsgType.Event.CLICK)) {

		}
		if (event.equals(MsgType.Event.LOCATION)) {

		}
		if (event.equals(MsgType.Event.SCAN)) {

		}
		if (event.equals(MsgType.Event.SUBSCRIBE)) {

		}
		if (event.equals(MsgType.Event.UNSUBSCRIBE)) {

		}
		if (event.equals(MsgType.Event.VIEW)) {

		}

	}
}
