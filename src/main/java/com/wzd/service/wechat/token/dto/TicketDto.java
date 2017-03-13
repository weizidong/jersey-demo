package com.lifesense.healthcenter.service.wechat.token.dto;

import java.io.Serializable;

/**
 * 微信公众号jsapi_ticket信息
 * 
 * @author juzhi
 *
 */
@SuppressWarnings("serial")
public class TicketDto implements Serializable {

	// jsapi_ticket
	private String jsapiTicket;

	// 令牌有效时间，单位：秒
	private Long expiresIn;

	// 获取令牌时间
	private Long timestamp;

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
