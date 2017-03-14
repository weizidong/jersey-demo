package com.wzd.service.wechat.constants;


/**
 * Xml协议响应
 */
public class XmlResp {

	/**
	 * 文本消息响应格式
	 */
	public static final String TEXT = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<Content><![CDATA[%s]]></Content>")
			.append("</xml>").toString();
	
	/**
	 * 构造文本消息响应
	 */
	public static final String buildText(String toUser, String fromUser, String content) {
		return String.format(TEXT, toUser, fromUser, time(), MsgType.TEXT, content);
	}
	
	/**
	 * 设备消息响应格式
	 */
	public static final String DEVICE_TEXT = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<DeviceType><![CDATA[%s]]></DeviceType>")
			.append("<DeviceID><![CDATA[%s]]></DeviceID>")
			.append("<SessionID>%s</SessionID>")
			.append("<Content><![CDATA[%s]]></Content>")
			.append("</xml>").toString();
	
	/**
	 * 用户订阅/退订设备状态，微信把消息推送给第三方，接收第三方的设备状态信息回包
	 * 
	 * 设备状态订阅的响应内容
	 */
	public static final String DEVICE_STATUS = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")//消息类型：device_status
			.append("<DeviceType><![CDATA[%s]]></DeviceType>")//设备类型（同请求参数）
			.append("<DeviceID><![CDATA[%s]]></DeviceID>")
			.append("<DeviceStatus>%s</DeviceStatus>")//设备状态：0--未连接；1--已连接
			.append("</xml>").toString();
	
	
	/**
	 * 构造设备消息响应
	 */
	public static final String buildDeviceText(String fromUser, String toUser,
			String deviceType, String deviceID, String content, String sessionID) {
		return String.format(DEVICE_TEXT, fromUser, toUser, time(),
				MsgType.DEVICE_TEXT, deviceType, deviceID, sessionID, content);
	}
	/**
	 * 构造设备事件响应
	 * @param fromUser
	 * @param toUser
	 * @param deviceType
	 * @param deviceID
	 * @param content
	 * @param sessionID
	 * @return
	 */
	public static final String buildDeviceEvent(String fromUser, String toUser,
			String deviceType, String deviceID, Integer device_status) {
		return String.format(DEVICE_STATUS, 
				fromUser, 
				toUser, 
				time(),
				MsgType.DEVICE_STATUS, 
				deviceType, deviceID, device_status);
	}

	/**
	 * 秒级时间戳
	 */
	private static String time(){
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	public static void main(String[] args) {
//		System.out.println(buildText("","",""));
//		System.out.println(buildDeviceText("", "","","","",""));
		System.out.println(buildDeviceEvent("fromUser","toUser",MsgType.DEVICE_STATUS,"deviceID",1));
	}


}
