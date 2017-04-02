package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.SignType;

/**
 * 报名表
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Entryform implements Serializable {
	@Id
	private String id; // ID
	// 自有属性
	@Column(name = "user_id")
	private String userId; // 用户ID
	@Column(name = "activity_id")
	private String activityId; // 活动ID
	private Date start; // 开始时间
	private Date end; // 结束时间
	private Integer type; // 活动类型
	private Date created; // 报名时间
	private Integer deleted; // 删除标志
	private Integer status; // 状态，0：未到场；1：已到场；

	public Entryform() {
		super();
	}

	public Entryform(String userId, String activityId, ActivityType type) {
		this.userId = userId;
		this.activityId = activityId;
		this.type = type.getValue();
	}

	public Entryform(String userId, String activityId, ActivityType type, Date start, Date end) {
		this.userId = userId;
		this.activityId = activityId;
		this.type = type.getValue();
		this.start = start;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public void setStatus(SignType status) {
		this.status = status.getValue();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}