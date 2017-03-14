package com.wzd.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 模板信息对象
 * @author charwin
 *
 */
@SuppressWarnings("serial")
public class Data implements Serializable {
	
	/**
	 * 标题
	 */
	private ValueData first;
	
	/**
	 * 备注信息
	 */
	private ValueData remark;


	public ValueData getFirst() {
		return first;
	}


	public void setFirst(ValueData first) {
		this.first = first;
	}


	public ValueData getRemark() {
		return remark;
	}


	public void setRemark(ValueData remark) {
		this.remark = remark;
	}

}

