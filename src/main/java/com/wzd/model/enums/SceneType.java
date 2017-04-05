package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 二维码场景
 * 
 * @author WeiZiDong
 *
 */
public enum SceneType {
	服务号健身运动签到("fw_sports_ticket_sign");
	private String value;

	private SceneType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SceneType parse(String type) {
		for (SceneType item : SceneType.values()) {
			if (type != null && type.equals(item.getValue())) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + SceneType.class + "有效值。");
	}
}