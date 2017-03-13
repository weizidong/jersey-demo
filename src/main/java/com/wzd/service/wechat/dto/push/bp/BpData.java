package com.lifesense.healthcenter.service.wechat.dto.push.bp;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.Data;
import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;


/**
 * 模板信息 对象 血压计
 */
@SuppressWarnings("serial")
public class BpData extends Data implements Serializable {
	
	/**
	 * 高压
	 */
	private ValueData highPressure;
	
	/**
	 * 低压
	 */
	private ValueData lowPressure;
	
	/**
	 * 心率
	 */
	private ValueData heart;
	
	/**
	 * 血压等级
	 */
	private ValueData bloodPressureLevel;
	


	public ValueData getLowPressure() {
		return lowPressure;
	}

	public void setLowPressure(ValueData lowPressure) {
		this.lowPressure = lowPressure;
	}

	public ValueData getHeart() {
		return heart;
	}

	public void setHeart(ValueData heart) {
		this.heart = heart;
	}

	public ValueData getBloodPressureLevel() {
		return bloodPressureLevel;
	}

	public void setBloodPressureLevel(ValueData bloodPressureLevel) {
		this.bloodPressureLevel = bloodPressureLevel;
	}

	public ValueData getHighPressure() {
		return highPressure;
	}

	public void setHighPressure(ValueData highPressure) {
		this.highPressure = highPressure;
	}


	
}


