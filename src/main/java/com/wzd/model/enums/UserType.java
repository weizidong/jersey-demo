package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 登录用户的类型
 * 
 * @author WeiZiDong
 *
 */
public enum UserType {
	企业号创建者(1), 企业号内部系统管理员(2), 企业号外部系统管理员(3), 企业号分级管理员(4), 企业号成员(5);
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