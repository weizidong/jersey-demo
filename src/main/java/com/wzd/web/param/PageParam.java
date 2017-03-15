package com.wzd.web.param;

import java.util.Arrays;

/**
 * 分页查询参数
 * 
 * @author WeiZiDong
 *
 */
public class PageParam {
	private Integer page; // 当前页
	private Integer pageSize;// 每页条数
	private String[] sort;// 排序字段
	private String[] order; // 排序顺序
	private String[] filed;// 筛选字段
	private String[] keyWord;// 筛选关键词

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String[] getSort() {
		return sort;
	}

	public void setSort(String[] sort) {
		this.sort = sort;
	}

	public String[] getOrder() {
		return order;
	}

	public void setOrder(String[] order) {
		this.order = order;
	}

	public String[] getFiled() {
		return filed;
	}

	public void setFiled(String[] filed) {
		this.filed = filed;
	}

	public String[] getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String[] keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "PageParam [page=" + page + ", pageSize=" + pageSize + ", sort=" + Arrays.toString(sort) + ", order="
				+ Arrays.toString(order) + ", filed=" + Arrays.toString(filed) + ", keyWord=" + Arrays.toString(keyWord)
				+ "]";
	}

}
