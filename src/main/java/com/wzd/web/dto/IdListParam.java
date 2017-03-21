package com.wzd.web.dto;

import java.util.List;

/**
 * id列表参数
 * 
 * @author WeiZiDong
 *
 */
public class IdListParam<T> {
	private List<T> ids;
	private Integer type;

	public List<T> getIds() {
		return ids;
	}

	public void setIds(List<T> ids) {
		this.ids = ids;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "IdListParam [ids=" + ids + ", type=" + type + "]";
	}

}
