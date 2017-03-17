package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 请求来源类型
 * 
 * @author WeiZiDong
 *
 */
public enum APPType {
	企业号("QYH"), 服务号("FWH"), 管理平台("GLPT"), 网站主页("WZZY");
	private String value;

	private APPType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static APPType parse(String type) {
		for (APPType item : APPType.values()) {
			if (type != null && type.equals(item.getValue())) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + APPType.class + "有效值。");
	}
}