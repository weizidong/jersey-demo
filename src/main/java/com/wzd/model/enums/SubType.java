package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 关注类型
 * 
 * @author WeiZiDong
 *
 */
public enum SubType {
	未关注(0), 已关注(1);
	private Integer value;

	private SubType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SubType parse(Integer type) {
		for (SubType item : SubType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + SubType.class + "有效值。");
	}
}