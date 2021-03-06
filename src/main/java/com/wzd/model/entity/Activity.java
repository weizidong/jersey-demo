package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.EntryType;
import com.wzd.model.enums.StateType;

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
	@Column(name = "pic_url")
	private String picUrl; // 封面
	private String name; // 活动名称
	private Date start; // 活动开始时间
	private Date end; // 活动结束时间
	@Column(name = "entry_start")
	private Date entryStart; // 活动报名开始时间
	@Column(name = "entry_end")
	private Date entryEnd; // 活动报名结束时间
	private Integer entry; // 活动报名权限
	private Integer score; // 报名所需积分
	private String sponsor; // 主办方
	@Column(name = "co_sponsor")
	private String coSponsor; // 协办方
	private String organizer; // 承办方
	private String place; // 活动地点
	private String website; // 提供方链接
	private String ticket; // 签到二维码
	private String detail; // 活动详情
	private String rule; // 活动规则
	@Column(name = "admin_id")
	private String adminId;// 发布者
	private Date created;// 发布时间
	private Integer total; // 活动总人数
	private Integer current; // 当前报名人数
	// 系统属性
	private Integer type; // 类型
	private Integer deleted; // 删除标志
	private Integer status; // 状态

	@Transient
	private Admin admin; // 发布者
	@Transient
	private List<Files> files; // 活动配图

	public Activity() {
		super();
	}

	public Activity(String picUrl, String name, Date start, Date end, Date entryStart, Date entryEnd, EntryType entry, Integer score, String sponsor, String coSponsor,
			String organizer, String place, String website, String detail, String rule, Integer total, ActivityType type) {
		this.picUrl = picUrl;
		this.name = name;
		this.start = start;
		this.end = end;
		this.entryStart = entryStart;
		this.entryEnd = entryEnd;
		this.entry = entry.getValue();
		this.score = score;
		this.sponsor = sponsor;
		this.coSponsor = coSponsor;
		this.organizer = organizer;
		this.place = place;
		this.website = website;
		this.detail = detail;
		this.rule = rule;
		this.total = total;
		this.type = type.getValue();
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
		if (files != null && files.size() > 0) {
			this.picUrl = files.get(0).getUrl();
		}
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public Date getEntryStart() {
		return entryStart;
	}

	public void setEntryStart(Date entryStart) {
		this.entryStart = entryStart;
	}

	public Date getEntryEnd() {
		return entryEnd;
	}

	public void setEntryEnd(Date entryEnd) {
		this.entryEnd = entryEnd;
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

	public Integer getEntry() {
		return entry;
	}

	public void setEntry(Integer entry) {
		this.entry = entry;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getCoSponsor() {
		return coSponsor;
	}

	public void setCoSponsor(String coSponsor) {
		this.coSponsor = coSponsor;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}