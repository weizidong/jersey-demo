package com.wzd.service.wechat.dto.push.device;

import java.io.Serializable;

import com.wzd.service.wechat.dto.push.Data;
import com.wzd.service.wechat.dto.push.ValueData;



/**
 * 模板信息 解绑设备
 */
@SuppressWarnings("serial")
public class UnbindDeviceData extends Data implements Serializable {

	/**
	 * 设备SN
	 */
	private ValueData keyword1;
	
	/**
	 * 解绑时间
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


