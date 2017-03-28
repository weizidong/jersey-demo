package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 活动表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Activity implements Serializable {
	@Id
	private String id; // ID
	// 自有属性
	private String title; // 活动主题
	private Integer type; // 类型
	@Column(name = "pic_url")
	private String picUrl; // 推送配图
	private Date start; // 活动开始时间
	private Date end; // 活动结束时间
	private String place; // 活动举办地点
	private Integer total; // 最大参加人数
	private Integer current; // 当前参加人数
	@Column(name = "pub_user")
	private Integer pubUser; // 发布人
	@Column(name = "dep_id")
	private Integer depId; // 发布机构
	private Object detail; // 活动详情
	// 系统属性
	private Date created; // 创建时间
	private Integer deleted; // 删除标志，0：未删除，1：回收站，2：永久删除
	private Date updated; // 修改时间
	private Integer status; // 状态，0：未开始，1：进行中，2：已结束
	private Integer auditor; // 审核人
	private Integer audit; // 审核状态
	@Column(name = "aud_time")
	private Date audTime; // 审核时间
	// 关联属性
	@Transient
	private List<Files> files;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPubUser() {
		return pubUser;
	}

	public void setPubUser(Integer pubUser) {
		this.pubUser = pubUser;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuditor() {
		return auditor;
	}

	public void setAuditor(Integer auditor) {
		this.auditor = auditor;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Date getAudTime() {
		return audTime;
	}

	public void setAudTime(Date audTime) {
		this.audTime = audTime;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", title=" + title + ", type=" + type + ", picUrl=" + picUrl + ", start=" + start + ", end=" + end + ", place=" + place + ", total=" + total
				+ ", current=" + current + ", pubUser=" + pubUser + ", depId=" + depId + ", detail=" + detail + ", created=" + created + ", deleted=" + deleted + ", updated="
				+ updated + ", status=" + status + ", auditor=" + auditor + ", audit=" + audit + ", audTime=" + audTime + ", files=" + files + "]";
	}

}