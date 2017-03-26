package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	private String name; // 名称
	private Integer score;// 积分
	private Integer time;// 单人次数
	private String provider;// 提供者
	private String website; // 提供者网站
	@Column(name = "start_time")
	private Date startTime;// 开始时间
	@Column(name = "end_time")
	private Date endTime;// 结束时间
	private Integer total;// 总个数
	private Integer current;// 当前领取个数
	private Integer type;// 福利类型
	private Object rule;// 规则
	// 系统属性
	private Integer deleted; // 删除标志
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

	public Object getRule() {
		return rule;
	}

	public void setRule(Object rule) {
		this.rule = rule;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", score=" + score + ", time=" + time + ", provider=" + provider + ", website=" + website + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", total=" + total + ", current=" + current + ", type=" + type + ", rule=" + rule + ", deleted=" + deleted + ", draw=" + draw + "]";
	}

}
