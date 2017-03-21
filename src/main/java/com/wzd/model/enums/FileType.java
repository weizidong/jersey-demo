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
	头像(1), 配图(2), 附件(3);
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
