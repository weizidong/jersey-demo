package com.wzd.model.enums;

/**
 * 删除状态
 * 
 * @author weizidong
 *
 */
public enum DeleteType {
	未删除(0), 回收站(1), 永久删除(2);
	private Integer value;

	private DeleteType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static DeleteType parse(Integer type) {
		for (DeleteType item : DeleteType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new RuntimeException("值[" + type + "]不是" + DeleteType.class + "有效值。");
	}

}
