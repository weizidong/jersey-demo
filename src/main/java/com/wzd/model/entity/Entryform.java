package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.SignType;
import com.wzd.model.enums.StateType;

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
	@Column(name = "open_id")
	@JsonIgnore
	private String openId; // openId
	@Column(name = "activity_id")
	private String activityId; // 活动ID
	private Date start; // 开始时间
	private Date end; // 结束时间
	private Integer type; // 活动类型
	private Date created; // 报名时间
	private Integer deleted; // 删除标志
	private Integer status; // 状态，0：未到场；1：已到场；
	private Boolean exp; // 有无工作经验

	public Entryform() {
		super();
	}

	public Entryform(String id, String openId, String activityId, Date start, Date end, Integer type, Date created, Integer del, Integer status) {
		this.id = id;
		this.openId = openId;
		this.activityId = activityId;
		this.start = start;
		this.end = end;
		this.type = type;
		this.created = created;
		this.deleted = del;
		this.status = status;
	}

	public Entryform(String openId, ActivityType type, DeleteType del, StateType status) {
		this(null, openId, null, null, null, type.getValue(), null, del.getValue(), status.getValue());
	}

	public Entryform(String activityId, ActivityType type, DeleteType del) {
		this(null, null, activityId, null, null, type.getValue(), null, del.getValue(), null);
	}

	public Entryform(String openId, String activityId, ActivityType type) {
		this(null, openId, activityId, null, null, type.getValue(), null, null, null);
	}

	public Entryform(String openId, String activityId, ActivityType type, Date start, Date end) {
		this(null, openId, activityId, start, end, type.getValue(), null, null, null);
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getExp() {
		return exp;
	}

	public void setExp(Boolean exp) {
		this.exp = exp;
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