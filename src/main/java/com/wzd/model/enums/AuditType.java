package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 审核状态
 * 
 * @author WeiZiDong
 *
 */
public enum AuditType {
	未审核(0), 审核中(1), 审核成功(2), 审核失败(3);
	private Integer value;

	private AuditType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static AuditType parse(Integer type) {
		for (AuditType item : AuditType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + AuditType.class + "有效值。");
	}
}