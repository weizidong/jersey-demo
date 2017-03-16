package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

/**
 * video消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class VIDEO implements Serializable {
	private String media_id; // 视频媒体文件id，可以调用上传临时素材或者永久素材接口获取
	private String thumb_media_id; // 缩略图的媒体ID
	private String title; // 视频消息的标题，不超过128个字节，超过会自动截断
	private String description; // 视频消息的描述，不超过512个字节，超过会自动截断

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

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

	@Override
	public String toString() {
		return "VIDEO [media_id=" + media_id + ", thumb_media_id=" + thumb_media_id + ", title=" + title + ", description=" + description + "]";
	}

}
