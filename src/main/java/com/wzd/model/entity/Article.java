package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;

/**
 * 文章
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Article implements Serializable {
	@Id
	private String id; // ID
	// 自有属性
	private String title; // 标题
	private String content;// 内容
	@Column(name = "pic_url")
	private String imgUrl;// 配图
	private Integer type; // 文章类型
	@Column(name = "admin_id")
	private String adminId; // 发布人
	@Column(name = "dep_id")
	private Integer depId; // 发布机构
	// 系统属性
	private Date created; // 创建时间
	private Date updated; // 修改时间
	private Integer deleted;// 删除标志
	private String auditor; // 审核人
	private Integer audit; // 审核状态
	@Column(name = "aud_time")
	private Date audTime; // 审核时间
	// 关联属性
	@Transient
	private List<Files> files;// 附件
	@Transient
	private List<Admin> admins;// 通知成员

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
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

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Date getAudTime() {
		return audTime;
	}

	public void setAudTime(Date audTime) {
		this.audTime = audTime;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
