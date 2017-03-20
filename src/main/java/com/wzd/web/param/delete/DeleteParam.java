package com.wzd.web.param.delete;

import java.io.Serializable;
import java.util.List;

/**
 * 批量刪除
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class DeleteParam implements Serializable {

	private List<Integer> ids;
	private Integer type;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
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
		return "DeleteParam [ids=" + ids + ", type=" + type + "]";
	}

}
