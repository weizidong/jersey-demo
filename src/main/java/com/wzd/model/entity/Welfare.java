package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;

/**
 * 福利实体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Welfare implements Serializable {
	@Id
	private String id;
	// 自有属性
	@Column(name = "pic_url")
	private String picUrl; // 配图
	private String name; // 名称
	private Integer total;// 总个数
	@Column(name = "start_time")
	private Date startTime;// 开始时间
	@Column(name = "end_time")
	private Date endTime;// 结束时间
	private Integer score;// 积分
	private Integer time;// 单人次数
	private String provider;// 提供者
	private String website; // 提供者网站
	private Integer current;// 当前领取个数
	private Integer type;// 福利类型
	private Object rule;// 规则
	private String wishing; // 红包祝福语
	private String news; // 图文回复
	private String text; // 文本回复
	@Column(name = "admin_id")
	private String adminId;// 发布者
	private Date created;// 发布时间
	// 系统属性
	private Integer deleted; // 删除标志
	private Integer status; // 状态标识
	// 关联属性
	@Transient
	private Integer draw;// 该用户可领取剩余次数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setType(HistoryType type) {
		this.type = type.getValue();
	}

	public Object getRule() {
		return rule;
	}

	public void setRule(Object rule) {
		this.rule = rule;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
