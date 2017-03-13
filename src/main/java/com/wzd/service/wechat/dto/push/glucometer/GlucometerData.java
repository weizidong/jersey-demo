package com.lifesense.healthcenter.service.wechat.dto.push.glucometer;

import java.io.Serializable;

import com.lifesense.healthcenter.service.wechat.dto.push.Data;
import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;

/**
 * 模板信息 对象 血糖仪
 * @author linjinyu
 *
 */
@SuppressWarnings("serial")
public class GlucometerData extends Data implements Serializable {

	/**
	 * 血糖值
	 */
	private ValueData keyword1;
	
	/**
	 * 进餐
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

	
	
	
//	{{first.DATA}}
//	血糖值：{{keyword1.DATA}}
//	血糖水平：{{keyword2.DATA}}
//	{{remark.DATA}}
	
	
	
}