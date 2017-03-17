package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 权限类型
 * 
 * @author WeiZiDong
 *
 */
public enum AuthType {
	文章(11);
	private Integer value;

	private AuthType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static AuthType parse(Integer type) {
		for (AuthType item : AuthType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + AuthType.class + "有效值。");
	}
}
