package com.lifesense.healthcenter.service.wechat.dto.push.step;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.Data;
import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;



/**
 * 模板信息 对象 体重称
 */
@SuppressWarnings("serial")
public class StepData extends Data implements Serializable {
	
	/**
	 * 步数
	 */
	private ValueData keyword1;


	/**
	 * 卡路里
	 */
	private ValueData keyword2;


	public ValueData getKeyword1() {
		return keyword1;
	}


	public void setKeyword1(ValueData keyword1) {
		this.keyword1 = keyword1;
	}


	public ValueData getKeyword2() {
		return keyword2;
	}


	public void setKeyword2(ValueData keyword2) {
		this.keyword2 = keyword2;
	}


	
}


