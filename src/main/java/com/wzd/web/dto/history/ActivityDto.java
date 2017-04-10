package com.wzd.web.dto.history;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class ActivityDto implements Serializable {
	@Id
	private String id; // ID
	@Column(name = "pic_url")
	private String picUrl; // 封面
	private String name; // 活动名称
	private Date start; // 活动开始时间
	private Date end; // 活动结束时间
	private Integer total; // 活动总人数
	private Integer entry; // 活动报名权限
	private Integer current; // 当前报名人数
	private Integer status; // 状态，0：未到场；1：已到场；

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getEntry() {
		return entry;
	}

	public void setEntry(Integer entry) {
		this.entry = entry;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
