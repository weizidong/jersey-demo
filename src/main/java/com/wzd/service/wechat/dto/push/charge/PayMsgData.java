package com.lifesense.healthcenter.service.wechat.dto.push.charge;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.Data;
import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;

/**
 * 充值成功模板消息
 * 
 * @author lyb
 * 
 */
@SuppressWarnings("serial")
public class PayMsgData extends Data implements Serializable {

	/**
	 * 消息内容
	 */
	private ValueData first;

	/**
	 * 设备
	 */
	private ValueData money;

	/**
	 * 解绑时间
	 */
	private ValueData product;

	/**
	 * 备注
	 */
	private ValueData remark;

	public ValueData getFirst() {
		return first;
	}

	public void setFirst(ValueData first) {
		this.first = first;
	}


	public ValueData getMoney() {
		return money;
	}

	public void setMoney(ValueData money) {
		this.money = money;
	}

	public ValueData getProduct() {
		return product;
	}

	public void setProduct(ValueData product) {
		this.product = product;
	}

	public ValueData getRemark() {
		return remark;
	}

	public void setRemark(ValueData remark) {
		this.remark = remark;
	}

}
