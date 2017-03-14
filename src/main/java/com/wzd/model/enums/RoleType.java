package com.wzd.model.enums;

/**
 * 角色类型
 * 
 * @author weizidong
 *
 */
public enum RoleType {
	管理员(0), 普通用户(1), 访客(2);
	private Integer value;

	private RoleType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static RoleType parse(Integer type) {
		for (RoleType item : RoleType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new RuntimeException("值[" + type + "]不是" + RoleType.class + "有效值。");
	}
}
