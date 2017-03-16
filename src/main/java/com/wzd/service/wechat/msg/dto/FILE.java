package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

/**
 * file消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class FILE implements Serializable {
	private String media_id; // 媒体文件id，可以调用上传临时素材或者永久素材接口获取

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	@Override
	public String toString() {
		return "FILE [media_id=" + media_id + "]";
	}

}
