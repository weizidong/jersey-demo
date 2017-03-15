package com.wzd.web.dto;

/**
 * 分页查询返回实体
 * 
 * @author WeiZiDong
 *
 */
public class PageDto {
	private Long total;// 总数量
	private Object list;// 结果

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "{total:" + total + ", list:" + list + "}";
	}

}
