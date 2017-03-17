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
	未审核(0), 审核通过(1), 审核未通过(2);
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
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + DeleteType.class + "有效值。");
	}
}