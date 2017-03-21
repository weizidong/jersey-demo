
package com.wzd.model.enums;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 运行状态类型
 * 
 * @author WeiZiDong
 *
 */
public enum StateType {
	停用(0), 启用(1);
	private Integer value;

	private StateType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static StateType parse(Integer type) {
		for (StateType item : StateType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + StateType.class + "有效值。");
	}
}
