package com.wzd.web.param;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSON;
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
	private Date start; // 开始时间
	private Date end; // 结束时间

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 设置参数到Example
	 */
	public static Criteria setCondition(Example e, String timeFiled, PageParam param, Class<?> clazz) {
		// 所有的字段名
		List<String> fields = Arrays.stream(clazz.getFields()).map(field -> field.getName()).collect(Collectors.toList());
		// 筛选条件
		Criteria c = e.createCriteria();
		if (!ArrayUtils.isEmpty(param.getFiled()) && !ArrayUtils.isEmpty(param.getKeyWord())) {
			for (int i = 0; i < param.getFiled().length; i++) {
				String filed = param.getFiled()[i];
				if (!fields.contains(filed)) {
					throw new WebException(ResponseCode.资源不存在, "筛选字段名[" + filed + "]错误");
				} else if (("|" + filed + "|").indexOf("|name|nickname|title|place|command|") != -1) {
					c.andLike(filed, param.getKeyWord()[i]);
				} else if (("|" + filed + "|").indexOf("|birthday|score|") != -1) {
					c.andGreaterThanOrEqualTo(filed, param.getKeyWord()[i]);
				} else {
					c.andEqualTo(filed, param.getKeyWord()[i]);
				}
			}
		}
		if (param.getStart() != null && param.getEnd() != null) {
			c.andBetween(timeFiled, param.getStart(), param.getEnd());
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
		return c;
	}
}
