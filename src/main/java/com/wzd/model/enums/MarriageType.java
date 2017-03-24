package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 婚姻类型
 * 
 * @author WeiZiDong
 *
 */
public enum MarriageType {
	未婚(0), 已婚(1), 离异(2);
	private Integer value;

	private MarriageType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static MarriageType parse(Integer type) {
		for (MarriageType item : MarriageType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + MarriageType.class + "有效值。");
	}
}