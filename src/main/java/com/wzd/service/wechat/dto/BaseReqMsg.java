package com.lifesense.healthcenter.service.wechat.dto;

/**
 * 微信信息
 * 
 * @author linjinyu
 *
 */
public class BaseReqMsg extends BaseReq {

	protected String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
