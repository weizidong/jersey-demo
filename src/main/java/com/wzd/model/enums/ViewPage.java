package com.wzd.model.enums;

import java.text.MessageFormat;

import com.wzd.utils.Configs;

/**
 * 前端页面
 * 
 * @author WeiZiDong
 *
 */
public abstract class ViewPage {
	/**
	 * 活动页面
	 */
	public static final String activity = "view/activity/{0}";
	/**
	 * 二维码登录
	 */
	public static String login2 = "view/login2/{0}?appType=zhxt";

	public static String genarate(String page, Object... arguments) {
		return Configs.hostname + MessageFormat.format(page, arguments);
	}
}
