package com.wzd.service.wechat.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.dao.WxActivityDao;
import com.wzd.model.entity.Wxactivity;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.service.wechat.base.XmlResp;
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
	private WxActivityDao dao;

	/**
	 * 处理文本消息
	 * 
	 * @param msg
	 */
	public String text(WechatMsg msg) {
		Wxactivity activity = dao.findByCommand(msg.getContent());
		if (activity == null || AuditType.审核通过.getValue() != activity.getAudit() || DeleteType.未删除.getValue() != activity.getDeleted()
				|| StateType.启用.getValue() != activity.getStatus()) {
			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "找不到活动,文字消息处理");
		}
		// TODO 验证是否重复
		switch (ActivityType.parse(activity.getType())) {
		case 红包活动:

			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "asdasdasdasdasd");
		case 电影票活动:
		case 优惠券活动:

			return XmlResp.buildText(msg.getFromUserName(), msg.getToUserName(), "asdasdasdasdasd");
		default:
			return XmlResp.SUCCESS;
		}
	}
}
