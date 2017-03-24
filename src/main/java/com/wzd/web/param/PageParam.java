package com.wzd.web.param;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.wzd.model.entity.Files;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 分页查询参数
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class PageParam implements Serializable {
	private Integer page = 1; // 当前页
	private Integer pageSize = 10;// 每页条数
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
		return "[page=" + page + ", pageSize=" + pageSize + ", sort=" + Arrays.toString(sort) + ", order=" + Arrays.toString(order) + ", filed=" + Arrays.toString(filed)
				+ ", keyWord=" + Arrays.toString(keyWord) + "]";
	}

	/**
	 * 设置参数到Example
	 */
	public static void setCondition(Example e, PageParam param, Class<?> clazz) {
		// 所有的字段名
		List<String> fields = Arrays.stream(clazz.getFields()).map(field -> field.getName()).collect(Collectors.toList());
		// 筛选条件
		if (!ArrayUtils.isEmpty(param.getFiled()) && !ArrayUtils.isEmpty(param.getKeyWord())) {
			Criteria c = e.createCriteria();
			for (int i = 0; i < param.getFiled().length; i++) {
				String filed = param.getFiled()[i];
				if (!fields.contains(filed)) {
					throw new WebException(ResponseCode.资源不存在, "筛选字段名[" + filed + "]错误");
				} else if (("|" + filed + "|").indexOf("|name|nickname|title|place|command|") != -1) {
					c.andLike(filed, param.getKeyWord()[i]);
				} else {
					c.andEqualTo(filed, param.getKeyWord()[i]);
				}
			}
		}
		// 排序条件
		if (!ArrayUtils.isEmpty(param.getOrder()) && !ArrayUtils.isEmpty(param.getSort())) {
			StringBuilder orderStr = new StringBuilder();
			for (int i = 0; i < param.getSort().length; i++) {
				String filed = param.getSort()[i];
				if (!fields.contains(filed)) {
					throw new WebException(ResponseCode.资源不存在, "排序字段名[" + filed + "]错误");
				} else {
					orderStr.append(filed + " " + param.getOrder()[i]);
				}
			}
			e.setOrderByClause(orderStr.toString());
		}
	}
}
