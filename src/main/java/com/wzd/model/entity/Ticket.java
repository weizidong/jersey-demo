package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
	@Column(name = "foreign_key")
	private String foreignKey; // 外键
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

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
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
		return "[id=" + id + ", ticket=" + ticket + ", foreignKey=" + foreignKey + ", used=" + used + ", created=" + created + "]";
	}

	/**
	 * 生成券码
	 */
	public static String generate(int i, String fk) {
		return fk.substring(fk.length() - 2) + Integer.toHexString(10000 + i);
	}

}
