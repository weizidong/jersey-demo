package com.wzd.model.enums;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * 文章类型
 * 
 * @author WeiZiDong
 *
 */
public enum ArticleType {
	公告(0), 通知(1), 新闻(2), 政策法规(3), 内部文件(4), 工会资讯(5);
	private Integer value;

	private ArticleType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static ArticleType parse(Integer type) {
		for (ArticleType item : ArticleType.values()) {
			if (type != null && type == item.getValue()) {
				return item;
			}
		}
		throw new WebException(ResponseCode.不允许此方法, "值[" + type + "]不是" + ArticleType.class + "有效值。");
	}
}