package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 签到类型
 * 
 * @author WeiZiDong
 *
 */
public enum SignType {
	未签到(0), 已签到(1);
	private Integer value;

	private SignType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SignType parse(Integer type) {
		for (SignType item : SignType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + SignType.class + "有效值。");
	}
}