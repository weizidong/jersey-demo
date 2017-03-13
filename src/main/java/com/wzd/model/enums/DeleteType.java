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
		switch (type) {
		case 0:
			return DeleteType.未删除;
		case 1:
			return DeleteType.回收站;
		case 2:
			return DeleteType.永久删除;
		default:
			return DeleteType.回收站;
		}
	}

}
