package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

/**
 * 音乐消息消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class MUSIC implements Serializable {
	private String title; // 图文消息/视频消息/音乐消息的标题
	private String description; // 图文消息/视频消息/音乐消息的描述
	private String musicurl; // 音乐链接
	private String hqmusicurl; // 高品质音乐链接，wifi环境优先使用该链接播放音乐
	private String thumb_media_id; // 缩略图的媒体ID

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicurl() {
		return musicurl;
	}

	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}

	public String getHqmusicurl() {
		return hqmusicurl;
	}

	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	@Override
	public String toString() {
		return "[title=" + title + ", description=" + description + ", musicurl=" + musicurl + ", hqmusicurl=" + hqmusicurl + ", thumb_media_id=" + thumb_media_id + "]";
	}

}
