package com.wzd.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 图片对象
 */
@SuppressWarnings("serial")
public class Image implements Serializable {

	/**
	 * 媒体文件ID
	 */
	private String mediaId;

	private String media_id; // 发送客服消息用到

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
