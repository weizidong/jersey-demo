package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 发送客服模板消息
 */
@SuppressWarnings("serial")
public class TemplateMessage implements Serializable {

	

	/**
	 * 普通微信用户的openid
	 */
	private String touser;

	/**
	 * 模板id
	 */
	private String template_id;
	
	/**
	 * 点击后跳转链接
	 */
	private String url;

	/**
	 * 顶部颜色
	 */
	private String topcolor;
	
	/**
	 * 模板信息对象
	 */
	private Data data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Data getData() {
		if (null == data){
			data = new Data();
		}
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	

}
