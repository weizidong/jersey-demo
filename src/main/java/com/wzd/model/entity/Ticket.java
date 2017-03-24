package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 票券
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Ticket implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 自有属性
	private String ticket; // 票券码
	private Integer foreign_key; // 外键
	private Date used;// 使用时间
	private Date created; // 生成时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getForeign_key() {
		return foreign_key;
	}

	public void setForeign_key(Integer foreign_key) {
		this.foreign_key = foreign_key;
	}

	public Date getUsed() {
		return used;
	}

	public void setUsed(Date used) {
		this.used = used;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", ticket=" + ticket + ", foreign_key=" + foreign_key + ", used=" + used + ", created=" + created + "]";
	}

}
