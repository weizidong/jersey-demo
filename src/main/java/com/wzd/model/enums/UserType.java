package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 用户的类型
 * 
 * @author WeiZiDong
 *
 */
public enum UserType {
	全部用户(0), 认证用户(1);
	private Integer value;

	private UserType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static UserType parse(Integer type) {
		for (UserType item : UserType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + UserType.class + "有效值。");
	}
}