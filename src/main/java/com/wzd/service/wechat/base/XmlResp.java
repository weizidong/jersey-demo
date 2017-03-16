package com.wzd.service.wechat.base;


/**
 * 回复消息Xml协议响应
 */
public class XmlResp {
	/**
	 * 回复文本消息
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
	 * 秒级时间戳
	 */
	private static String time(){
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	

}
