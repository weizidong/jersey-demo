package com.wzd.service.wechat.msg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.dao.WelfareDao;
import com.wzd.model.enums.ActivityType;
import com.wzd.service.wechat.base.XmlResp;
import com.wzd.service.wechat.news.News;
import com.wzd.utils.Configs;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 微信推送消息处理
 * 
 * @author WeiZiDong
 *
 */
@Component
public class WxMsgReceiver {
	@Autowired
	private WelfareDao dao;

	/**
	 * 处理文本消息
	 * 
	 * @param msg
	 */
	public String text(WechatMsg msg) {
		/*
		 * 以下是测试代码
		 */
		if (msg.getContent().equals("图文")) {
			List<News> news = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				News n = new News();
				if (i == 0) {
					n.setPicUrl(Configs.hostname + "userfiles/2017/03/22/360+200.png");
				} else {
					n.setPicUrl(Configs.hostname + "userfiles/2017/03/22/200+200.png");
				}
				n.setDescription("文字描述" + i);
				n.setTitle("题目" + i);
				n.setUrl(Configs.hostname);
				news.add(n);
			}
			return XmlResp.buildNews(msg.getFromUserName(), msg.getToUserName(), news);
		}
		// =============================结束

//		Wxactivity activity = dao.findByCommand(msg.getContent());
//		if (activity == null || AuditType.审核成功.getValue() != activity.getAudit() || DeleteType.未删除.getValue() != activity.getDeleted()
//				|| StateType.启用.getValue() != activity.getStatus()) {
//			WxMsgSender.sendTextToFw(WxMsg.generateText(msg.getFromUserName(), "你才" + msg.getContent()));
//			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "找不到活动,文字消息处理");
//		}
		// TODO 验证是否重复
		switch (ActivityType.parse(0)) {
		case 红包活动:

			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "asdasdasdasdasd");
		case 电影票活动:
		case 优惠券活动:

			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "asdasdasdasdasd");
		default:
			return XmlResp.SUCCESS;
		}
	}

	/**
	 * 处理图片消息
	 */
	public String img(WechatMsg msg) {
		// TODO 图片消息处理
		return XmlResp.SUCCESS;
	}

	/**
	 * 语音消息处理
	 */
	public String voice(WechatMsg msg) {
		// TODO 语音消息处理
		return XmlResp.SUCCESS;
	}

	/**
	 * 视频消息处理
	 */
	public String video(WechatMsg msg) {
		// TODO 视频消息处理
		return XmlResp.SUCCESS;
	}

	/**
	 * 小视频消息处理
	 */
	public String shortvideo(WechatMsg msg) {
		// TODO 小视频消息处理
		return XmlResp.SUCCESS;
	}

	/**
	 * 地理位置消息处理
	 */
	public String location(WechatMsg msg) {
		// TODO 地理位置消息处理
		return XmlResp.SUCCESS;
	}

	/**
	 * 链接消息处理
	 */
	public String link(WechatMsg msg) {
		// TODO 链接消息处理
		return XmlResp.SUCCESS;
	}
}
