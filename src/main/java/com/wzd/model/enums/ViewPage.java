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
	public static String loginSuccess = "view/zhxt/loginSuccess";
	/**
	 * 二维码登录地址
	 */
	public static String login2 = "view/login2/{0}?appType={1}";
	/**
	 * 登录页面
	 */
	public static String login = "view/zhxt/login";
	/**
	 * 登陆失败
	 */
	public static String loginError = "view/zhxt/loginError";

	public static String genarate(String page, Object... arguments) {
		return Configs.hostname + MessageFormat.format(page, arguments);
	}
}
