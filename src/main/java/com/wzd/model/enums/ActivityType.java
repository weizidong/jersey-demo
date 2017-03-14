package com.wzd.model.enums;

/**
 * 活动类型
 * 
 * @author weizidong
 *
 */
public enum ActivityType {
	普通活动(0), 健身活动(1), 工会活动(2), 相亲活动(3);
	private Integer value;

	private ActivityType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static ActivityType parse(Integer type) {
		for (ActivityType item : ActivityType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new RuntimeException("值[" + type + "]不是" + ActivityType.class + "有效值。");
	}
}