package com.wzd.service.wechat.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.dao.AdminDao;
import com.wzd.model.dao.UserDao;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.utils.Configs;
import com.wzd.utils.StringUtil;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 微信事件消息处理
 * 
 * @author WeiZiDong
 *
 */
@Component
public class Event {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdminDao adminDao;

	/**
	 * 处理事件
	 * 
	 * @param msg
	 */
	public String push(WechatMsg msg) {
		switch (msg.getEvent().toUpperCase()) {
		case MsgType.Event.SUBSCRIBE:
			return subscribe(msg);
		case MsgType.Event.UNSUBSCRIBE:
			return unsubscribe(msg);
		case MsgType.Event.SCAN:
			return scan(msg);
		case MsgType.Event.LOCATION:
			return location(msg);
		case MsgType.Event.CLICK:
			return click(msg);
		case MsgType.Event.VIEW:
			return view(msg);
		default:
			return XmlResp.SUCCESS;
		}
	}

	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	private String view(WechatMsg msg) {
		// TODO 点击菜单跳转链接时的事件推送
		return XmlResp.SUCCESS;
	}

	/**
	 * 上报地理位置事件
	 */
	private String location(WechatMsg msg) {
		// TODO 上报地理位置事件
		return XmlResp.SUCCESS;
	}

	/**
	 * 扫描带参数二维码事件
	 */
	private String scan(WechatMsg msg) {
		// TODO 扫描带参数二维码事件
		return XmlResp.SUCCESS;
	}

	// 自定义菜单事件
	private String click(WechatMsg msg) {
		// TODO 自定义菜单事件
		return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "点击菜单:" + msg.getEventKey());
	}

	// 取消关注事件
	private String unsubscribe(WechatMsg msg) {
		// TODO 取消关注事件
		return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "取消关注事件");

	}

	// 关注事件
	private String subscribe(WechatMsg msg) {
		// TODO 关注事件
		// 关注服务号
		if (StringUtil.equalsIgnoreCase(msg.getToUserName(), Configs.bId)) {
		}
		return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "关注事件");

	}
}
