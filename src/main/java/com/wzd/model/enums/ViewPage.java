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
	 * 登陆成功
	 */
	public static String loginSuccess = "view/zhxt/loginSuccess?appType={0}";
	/**
	 * 登陆失败
	 */
	public static String loginError = "view/zhxt/loginError";

	public static String genarate(String page, Object... arguments) {
		return Configs.hostname + MessageFormat.format(page, arguments);
	}
}
