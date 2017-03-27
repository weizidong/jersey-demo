package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 性别类型
 * 
 * @author WeiZiDong
 *
 */
public enum SexType {
	未知(0), 男(1), 女(2);
	private Integer value;

	private SexType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SexType parse(Integer type) {
		for (SexType item : SexType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + SexType.class + "有效值。");
	}
}