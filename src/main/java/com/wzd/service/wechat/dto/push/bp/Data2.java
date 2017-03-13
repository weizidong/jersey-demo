package com.lifesense.healthcenter.service.wechat.dto.push.bp;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;


/**
 * 模板信息 对象 血压计
 */
@SuppressWarnings("serial")
public class Data2 extends com.lifesense.healthcenter.service.wechat.dto.push.Data implements Serializable {
	
	/**
	 * 高压
	 */
	private ValueData keyword1;
	
	/**
	 * 低压
	 */
	private ValueData keyword2;
	
	/**
	 * 心率
	 */
	private ValueData keyword3;
	
	/**
	 * 血压等级
	 */
	private ValueData keyword4;

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

	public ValueData getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(ValueData keyword3) {
		this.keyword3 = keyword3;
	}

	public ValueData getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(ValueData keyword4) {
		this.keyword4 = keyword4;
	}
	
}


