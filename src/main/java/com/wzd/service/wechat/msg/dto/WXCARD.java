package com.wzd.service.wechat.msg.dto;

import java.io.Serializable;

/**
 * 卡券消息体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WXCARD implements Serializable {
	private String card_id;
	private String card_ext;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getCard_ext() {
		return card_ext;
	}

	public void setCard_ext(String card_ext) {
		this.card_ext = card_ext;
	}

	@Override
	public String toString() {
		return "WXCARD [card_id=" + card_id + ", card_ext=" + card_ext + "]";
	}

}
