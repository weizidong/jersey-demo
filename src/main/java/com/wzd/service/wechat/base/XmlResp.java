package com.wzd.service.wechat.base;

import java.util.List;

import com.wzd.service.wechat.news.News;

/**
 * 回复消息Xml协议响应
 */
public class XmlResp {
	/**
	 * 回复success
	 */
	public static final String SUCCESS = "success";
	/**
	 * 回复文本消息
	 */
	private static final String TEXT = new StringBuilder()
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
	 * 回复图文消息
	 */
	private static final String NEWS = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<ArticleCount>%s</ArticleCount>")
			.append("<Articles>%s</Articles>")
			.append("</xml>").toString();
	/**
	 * 图文消息内容
	 */
	private static final String NEWSITEM = new StringBuilder()
			.append("<item>")
			.append("<Title><![CDATA[%s]]></Title>")
			.append("<Description><![CDATA[%s]]></Description>")
			.append("<PicUrl><![CDATA[%s]]></PicUrl>")
			.append("<Url><![CDATA[%s]]></Url>")
			.append("</item>").toString();
	/**
	 * 构造图文消息内容响应,多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
	 */
	public static final String buildNews(String toUser, String fromUser, List<News> news) {
		StringBuilder sb = new StringBuilder();
		for (News n : news) {
			sb.append(String.format(NEWSITEM, n.getTitle(), n.getDescription(), n.getPicUrl(), n.getUrl()));
		}
		return String.format(NEWS, toUser, fromUser, time(), MsgType.NEWS, news.size(), sb.toString());
	}
	/**
	 * 回复图片消息
	 */
	private static final String IMAGE = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<Image>")
			.append("<MediaId><![CDATA[%s]]></MediaId>")
			.append("</Image>")
			.append("</xml>").toString();
	
	/**
	 * 构造图片消息响应
	 */
	public static final String buildImge(String toUser, String fromUser, String mediaId) {
		return String.format(IMAGE, toUser, fromUser, time(), MsgType.IMAGE, mediaId);
	}
	/**
	 * 回复语音消息
	 */
	private static final String VOICE = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<Voice>")
			.append("<MediaId><![CDATA[%s]]></MediaId>")
			.append("</Voice>")
			.append("</xml>").toString();
	/**
	 * 构造语音消息响应
	 */
	public static final String buildVoice(String toUser, String fromUser, String mediaId) {
		return String.format(VOICE, toUser, fromUser, time(), MsgType.VOICE, mediaId);
	}
	/**
	 * 回复视频消息
	 */
	private static final String VIDEO = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<Video>")
			.append("<MediaId><![CDATA[%s]]></MediaId>")
			.append("<Title><![CDATA[%s]]></Title>")
			.append("<Description><![CDATA[%s]]></Description>")
			.append("</Video>")
			.append("</xml>").toString();
	/**
	 * 构造视频消息响应
	 */
	public static final String buildVideo(String toUser, String fromUser, String mediaId, String title, String description) {
		return String.format(VIDEO, toUser, fromUser, time(), MsgType.VIDEO, mediaId, title, description);
	}
	/**
	 * 回复音乐消息
	 */
	private static final String MUSIC = new StringBuilder()
			.append("<xml>")
			.append("<ToUserName><![CDATA[%s]]></ToUserName>")
			.append("<FromUserName><![CDATA[%s]]></FromUserName>")
			.append("<CreateTime>%s</CreateTime>")
			.append("<MsgType><![CDATA[%s]]></MsgType>")
			.append("<Music>")
			.append("<Title><![CDATA[%s]]></Title>")
			.append("<Description><![CDATA[%s]]></Description>")
			.append("<MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>")
			.append("<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>")
			.append("<ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>")
			.append("</Music>")
			.append("</xml>").toString();
	/**
	 * 构造音乐消息响应
	 */
	public static final String buildMusic(String toUser, String fromUser, String title, String description, String musicUrl, String HQMusicUrl, String ThumbMediaId) {
		return String.format(MUSIC, toUser, fromUser, time(), MsgType.MUSIC, title, description, musicUrl, HQMusicUrl, ThumbMediaId);
	}
	/**
	 * 秒级时间戳
	 */
	private static String time(){
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

}
