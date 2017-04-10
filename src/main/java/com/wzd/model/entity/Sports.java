package com.wzd.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.utils.DateUtil;

/**
 * 运动表
 */
@SuppressWarnings("serial")
public class Sports implements Serializable {
	@Id
	private String id;
	@Column(name = "pic_url")
	private String picUrl; // 项目图标
	private String name; // 项目活动
	private Integer type; // 项目类型
	private Integer total; // 人数上限
	private Integer score; // 报名积分
	private String date; // 开启日期
	private String start; // 开始时间
	private String end; // 结束时间
	private Integer status; // 状态
	private Integer deleted; // 删除状态
	@Column(name = "admin_id")
	private String adminId;// 创建者
	private Date created;// 创建时间

	// 关联属性
	@Transient
	private List<Date> dates = new ArrayList<>();// 开启日期
	@Transient
	private List<Date> starts = new ArrayList<>();// 开始时间
	@Transient
	private List<Date> ends = new ArrayList<>();// 结束时间

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setDeleted(DeleteType del) {
		this.deleted = del.getValue();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatus(StateType state) {
		this.status = state.getValue();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		if (StringUtils.isNotBlank(date)) {
			this.dates = Arrays.stream(date.split("\\|")).map(d -> DateUtil.parseToDate(d, DateUtil.P_DATE)).collect(Collectors.toList());
		}
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
		if (StringUtils.isNotBlank(start)) {
			this.starts = Arrays.stream(start.split("\\|")).map(t -> DateUtil.parseToDate(t, DateUtil.P_TIME)).collect(Collectors.toList());
		}
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
		if (StringUtils.isNotBlank(end)) {
			this.ends = Arrays.stream(end.split("\\|")).map(t -> DateUtil.parseToDate(t, DateUtil.P_TIME)).collect(Collectors.toList());
		}
	}

	public List<Date> getStarts() {
		return starts;
	}

	public void setStarts(List<Date> starts) {
		this.starts = starts;
		this.start = starts.stream().map(t -> DateUtil.formatDate(t, DateUtil.P_TIME)).collect(Collectors.joining("|"));
	}

	public List<Date> getEnds() {
		return ends;
	}

	public void setEnds(List<Date> ends) {
		this.ends = ends;
		this.end = ends.stream().map(t -> DateUtil.formatDate(t, DateUtil.P_TIME)).collect(Collectors.joining("|"));
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
		this.date = dates.stream().map(d -> DateUtil.formatDate(d, DateUtil.P_DATE)).collect(Collectors.joining("|"));
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
