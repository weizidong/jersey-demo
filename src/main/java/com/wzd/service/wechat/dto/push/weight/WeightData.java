package com.lifesense.healthcenter.service.wechat.dto.push.weight;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.Data;
import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;



/**
 * 模板信息 对象 体重称
 */
@SuppressWarnings("serial")
public class WeightData extends Data implements Serializable {

	/**
	 * 体重
	 */
	private ValueData weight;
	
	/**
	 * bmi
	 */
	private ValueData bmi;

	public ValueData getWeight() {
		return weight;
	}

	public void setWeight(ValueData weight) {
		this.weight = weight;
	}

	public ValueData getBmi() {
		return bmi;
	}

	public void setBmi(ValueData bmi) {
		this.bmi = bmi;
	}
	
	
}


