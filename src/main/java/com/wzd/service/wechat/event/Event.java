package com.wzd.service.wechat.event;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Setting;
import com.wzd.model.entity.User;
import com.wzd.model.enums.SubType;
import com.wzd.service.SystemService;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.news.News;
import com.wzd.service.wechat.user.FwUserApi;
import com.wzd.utils.Configs;
import com.wzd.utils.StringUtil;
import com.wzd.utils.ThreadPoolUtils;
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
	private SystemService systemService;

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
		// 取消关注服务号
		if (StringUtil.equalsIgnoreCase(msg.getToUserName(), Configs.bId)) {
			ThreadPoolUtils.excuteCachedThreadPool(() -> {
				User user = userDao.getByOpenId(msg.getFromUserName());
				user.setSubscribe(SubType.未关注.getValue());
				userDao.update(user);
			});
		}
		return XmlResp.SUCCESS;
	}

	// 关注事件
	private String subscribe(WechatMsg msg) {
		// 关注服务号
		if (StringUtil.equalsIgnoreCase(msg.getToUserName(), Configs.bId)) {
			ThreadPoolUtils.excuteCachedThreadPool(() -> {
				User user = userDao.getByOpenId(msg.getFromUserName());
				if (user == null) {
					userDao.create(FwUserApi.get(msg.getFromUserName()));
				} else {
					user.setSubscribe(SubType.已关注.getValue());
					userDao.update(user);
				}
			});
			Setting s = systemService.getSetting();
			return XmlResp.buildNews(msg.getFromUserName(), msg.getToUserName(), Arrays.asList(JSON.parseObject(s.getSub(), News.class)));
		}
		return XmlResp.SUCCESS;
	}
}
