package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.FileType;

/**
 * 文件
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Files implements Serializable {
	@Id
	private String id; // ID
	// 自有属性
	private String name; // 文件名
	private String url; // url地址
	@JsonIgnore
	private String fk; // 外键
	private String suffix; // 后缀名
	@Column(name = "user_id")
	private String userId; // 上传者
	private Date created; // 上传时间
	private Integer deleted; // 删除标志
	private Integer status; // 状态
	private Integer type; // 类型

	public Files() {
		super();
	}

	public Files(String fk, FileType type) {
		this.fk = fk;
		this.type = type.getValue();
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setType(FileType type) {
		this.type = type.getValue();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}