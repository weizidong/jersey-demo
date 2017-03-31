package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 文件类型
 * 
 * @author WeiZiDong
 *
 */
public enum FileType {
	管理员头像(1), 用户头像(2), 活动配图(3), 福利配图(4);
	private Integer value;

	private FileType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static FileType parse(Integer type) {
		for (FileType item : FileType.values()) {
			if (type != null && type.equals(item.getValue())) {
				return item;
			}
		}
		throw new WebException(ResponseCode.类型错误, "值[" + type + "]不是" + FileType.class + "有效值。");
	}
}
