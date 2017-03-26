package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 历史记录类型
 * 
 * @author WeiZiDong
 *
 */
public enum HistoryType {
	系统消息(0), 积分签到(1), 红包福利(2), 券票福利(3), 活动(4);
	private Integer value;

	private HistoryType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static HistoryType parse(Integer type) {
		for (HistoryType item : HistoryType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + HistoryType.class + "有效值。");
	}
}