package com.wzd.service.wechat.event;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.Setting;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.SceneType;
import com.wzd.model.enums.SubType;
import com.wzd.service.SportsService;
import com.wzd.service.SystemService;
import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.msg.dto.ARTICLE;
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
	@Autowired
	private SportsService sportsService;
	@Autowired
	private EntryformDao entryformDao;

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
		case MsgType.Event.SCANCODE_PUSH:
			return scanPush(msg);
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
	 * 企业号扫码事件
	 */
	private String scanPush(WechatMsg msg) {
		// TODO 企业号扫码事件
		return XmlResp.SUCCESS;
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
		if (SceneType.服务号健身运动签到.getValue().equals(msg.getEventKey())) {
			sportsService.sign(userDao.getByOpenId(msg.getFromUserName()));
		} else {
			Entryform ef = new Entryform(msg.getFromUserName(), msg.getEventKey(), ActivityType.工会活动);
			if (!entryformDao.isEntry(ef)) {
				return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "您还未报名该活动！");
			}
			entryformDao.sign(ef);
			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "签到成功！");
		}
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
			ThreadPoolUtils.execute(() -> {
				User user = userDao.getByOpenId(msg.getFromUserName());
				user.setSubscribe(SubType.未关注);
				userDao.update(user);
			});
		}
		return XmlResp.SUCCESS;
	}

	// 关注事件
	private String subscribe(WechatMsg msg) {
		// 关注服务号
		if (StringUtil.equalsIgnoreCase(msg.getToUserName(), Configs.bId)) {
			Setting s = systemService.getSetting();
			ThreadPoolUtils.execute(() -> {
				User user = userDao.getByOpenId(msg.getFromUserName());
				if (user == null) {
					user = FwUserApi.get(msg.getFromUserName());
					user.setScore(s.getScore());
					userDao.create(user);
				} else {
					user.setSubscribe(SubType.已关注);
					userDao.update(user);
				}
			});
			return XmlResp.buildNews(msg.getFromUserName(), msg.getToUserName(), Arrays.asList(JSON.parseObject(s.getSub(), ARTICLE.class)));
		}
		return XmlResp.SUCCESS;
	}
}
