package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * Socket指令类型
 * 
 * @author WeiZiDong
 *
 */
public enum SocketType {
	心跳("noop"), 数据("data"), 通知("notice"), 开启("open"), 关闭("close"), 异常("error");
	private String value;

	private SocketType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SocketType parse(String type) {
		for (SocketType item : SocketType.values()) {
			if (type != null && type.equals(item.getValue())) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + SocketType.class + "有效值。");
	}
}