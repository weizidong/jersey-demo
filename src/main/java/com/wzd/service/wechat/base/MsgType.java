package com.wzd.service.wechat.base;

/**
 * MsgType类型常量
 * 
 * @author WeiZiDong
 *
 */
public abstract class MsgType {
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
	 * file消息(发送)
	 */
	public static final String FILE = "file";
	/**
	 * 音乐消息(发送)
	 */
	public static final String MUSIC = "music";
	/**
	 * news消息(发送)
	 */
	public static final String NEWS = "news";
	/**
	 * mpNews消息(发送)
	 */
	public static final String MPNEWS = "mpnews";
	/**
	 * 卡券消息(发送)
	 */
	public static final String WXCARD = "wxcard";
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
	public static abstract class Event {
		/**
		 * 订阅
		 */
		public static final String SUBSCRIBE = "SUBSCRIBE";
		/**
		 * 取消订阅
		 */
		public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
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
		/**
		 * 扫码推事件
		 */
		public static final String SCANCODE_PUSH = "SCANCODE_PUSH";
		/**
		 * 扫码推事件且弹出“消息接收中”提示框
		 */
		public static final String SCANCODE_WAITMSG = "SCANCODE_WAITMSG";
		/**
		 * 弹出系统拍照发图
		 */
		public static final String PIC_SYSPHOTO = "PIC_SYSPHOTO";
		/**
		 * 弹出拍照或者相册发图
		 */
		public static final String PIC_PHOTO_OR_ALBUM = "PIC_PHOTO_OR_ALBUM";
		/**
		 * 弹出微信相册发图器
		 */
		public static final String PIC_WEIXIN = "PIC_WEIXIN";
		/**
		 * 弹出地理位置选择器
		 */
		public static final String LOCATION_SELECT = "LOCATION_SELECT";
		/**
		 * 成员进入应用
		 */
		public static final String ENTER_AGENT = "ENTER_AGENT";
		/**
		 * 异步任务完成
		 */
		public static final String BATCH_JOB_RESULT = "BATCH_JOB_RESULT";
	}
}
