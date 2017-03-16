package com.wzd.service.wechat.base;

/**
 * MsgType类型常量
 * 
 * @author WeiZiDong
 *
 */
public class MsgType {
	/**
	 * 文本消息
	 */
	public static final String TEXT = "text";
	/**
	 * 图片消息
	 */
	public static final String IMAGE = "image";
	/**
	 * 语音消息
	 */
	public static final String VOICE = "voice";
	/**
	 * 视频消息
	 */
	public static final String VIDEO = "video";
	/**
	 * 小视频消息
	 */
	public static final String SHORTVIDEO = "shortvideo";
	/**
	 * 地理位置消息
	 */
	public static final String LOCATION = "location";
	/**
	 * 链接消息
	 */
	public static final String LINK = "link";
	/**
	 * 事件推送
	 */
	public static final String EVENT = "event";

	/**
	 * 事件具体类型
	 * 
	 * @author WeiZiDong
	 *
	 */
	public static class Event {
		/**
		 * 订阅
		 */
		public static final String SUBSCRIBE = "subscribe";
		/**
		 * 取消订阅
		 */
		public static final String UNSUBSCRIBE = "unsubscribe";
		/**
		 * 扫码
		 */
		public static final String SCAN = "SCAN";
		/**
		 * 上报地理位置
		 */
		public static final String LOCATION = "LOCATION";
		/**
		 * 点击菜单拉取消息
		 */
		public static final String CLICK = "CLICK";
		/**
		 * 点击菜单跳转链接
		 */
		public static final String VIEW = "VIEW";
	}
}
