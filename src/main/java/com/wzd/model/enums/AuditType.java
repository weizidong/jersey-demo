package com.wzd.model.enums;

/**
 * 审核状态
 * 
 * @author weizidong
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
}