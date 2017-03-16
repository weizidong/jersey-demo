package com.wzd.service.wechat.msg;

import java.io.Serializable;

import com.wzd.service.wechat.msg.dto.FILE;
import com.wzd.service.wechat.msg.dto.IMAGE;
import com.wzd.service.wechat.msg.dto.MPNEWS;
import com.wzd.service.wechat.msg.dto.MUSIC;
import com.wzd.service.wechat.msg.dto.NEWS;
import com.wzd.service.wechat.msg.dto.TEXT;
import com.wzd.service.wechat.msg.dto.VIDEO;
import com.wzd.service.wechat.msg.dto.VOICE;
import com.wzd.service.wechat.msg.dto.WXCARD;

/**
 * 推送消息的基类
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxMsg implements Serializable {
	private String touser; // 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	private String toparty; // 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	private String totag; // 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	private String msgtype; // 消息类型，此时固定为：text （支持消息型应用跟主页型应用）
	private Integer agentid; // 企业应用的id，整型。可在应用的设置页面查看
	private Integer safe; // 表示是否是保密消息，0表示否，1表示是，默认0

	private TEXT text; // text消息
	private IMAGE image; // image消息
	private VOICE voice; // voice消息
	private VIDEO video; // video消息
	private MUSIC music; // music消息
	private WXCARD wxcard; // 卡券消息
	private FILE file; // file消息
	private NEWS news; // news消息
	private MPNEWS mpnews; // mpnews消息

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	public TEXT getText() {
		return text;
	}

	public void setText(TEXT text) {
		this.text = text;
	}

	public IMAGE getImage() {
		return image;
	}

	public void setImage(IMAGE image) {
		this.image = image;
	}

	public VOICE getVoice() {
		return voice;
	}

	public void setVoice(VOICE voice) {
		this.voice = voice;
	}

	public VIDEO getVideo() {
		return video;
	}

	public void setVideo(VIDEO video) {
		this.video = video;
	}

	public MUSIC getMusic() {
		return music;
	}

	public void setMusic(MUSIC music) {
		this.music = music;
	}

	public WXCARD getWxcard() {
		return wxcard;
	}

	public void setWxcard(WXCARD wxcard) {
		this.wxcard = wxcard;
	}

	public FILE getFile() {
		return file;
	}

	public void setFile(FILE file) {
		this.file = file;
	}

	public NEWS getNews() {
		return news;
	}

	public void setNews(NEWS news) {
		this.news = news;
	}

	public MPNEWS getMpnews() {
		return mpnews;
	}

	public void setMpnews(MPNEWS mpnews) {
		this.mpnews = mpnews;
	}

	@Override
	public String toString() {
		return "BaseMsg [touser=" + touser + ", toparty=" + toparty + ", totag=" + totag + ", msgtype=" + msgtype + ", agentid=" + agentid + ", safe=" + safe + ", text=" + text
				+ ", image=" + image + ", voice=" + voice + ", video=" + video + ", music=" + music + ", wxcard=" + wxcard + ", file=" + file + ", news=" + news + ", mpnews="
				+ mpnews + "]";
	}

}
