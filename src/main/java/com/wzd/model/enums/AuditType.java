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

	public static AuditType parse(Integer type) {
		for (AuditType item : AuditType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new RuntimeException("值[" + type + "]不是" + AuditType.class + "有效值。");
	}
}