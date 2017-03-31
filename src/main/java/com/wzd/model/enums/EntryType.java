package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 报名权限
 * 
 * @author WeiZiDong
 *
 */
public enum EntryType {
	所有用户可报名(0), 认证用户可报名(1);
	private Integer value;

	private EntryType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static EntryType parse(Integer type) {
		for (EntryType item : EntryType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + EntryType.class + "有效值。");
	}
}