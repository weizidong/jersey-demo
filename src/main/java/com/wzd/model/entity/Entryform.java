package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.JSON;
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
	private Date time; // 报名时间
	private Integer type; // 类型
	private Date created; // 创建时间
	private Date updated; // 修改时间
	private Integer deleted; // 删除标志
	private Integer status; // 状态，0：未到场；1：已到场；

	public Entryform() {
		super();
	}

	public Entryform(String userId, String activityId) {
		this.userId = userId;
		this.activityId = activityId;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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