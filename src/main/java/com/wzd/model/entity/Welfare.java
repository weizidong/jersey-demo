package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 福利实体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Welfare implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	private String id;
	// 自有属性
	private String name; // 名称
	private Integer score;// 积分
	private Integer time;// 单人次数
	private String provider;// 提供者
	private Date start_time;// 开始时间
	private Date end_time;// 结束时间
	private Integer total;// 总个数
	private Integer current;// 当前领取个数
	private Integer type;// 福利类型
	// 系统属性
	private Integer deleted; // 删除标志

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

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", score=" + score + ", time=" + time + ", provider=" + provider + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", total=" + total + ", current=" + current + ", type=" + type + ", deleted=" + deleted + "]";
	}

}