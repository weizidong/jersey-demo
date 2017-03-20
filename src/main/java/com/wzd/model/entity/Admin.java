package com.wzd.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzd.service.wechat.user.WxUser;

/**
 * 管理员
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Admin extends WxUser {
	/**
	 * 成员所属部门id列表
	 */
	private String departments;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 删除标志，0：正常，1：回收站，2：永久删除
	 */
	private Integer deleted;

	/**
	 * 修改时间
	 */
	private Date updated;

	/**
	 * 权限
	 */
	private String auth;

	/**
	 * 微信唯一标志
	 */
	@JsonIgnore
	private String openid;

	/**
	 * 账号
	 */
	@JsonIgnore
	private String uname;

	/**
	 * 密码
	 */
	@JsonIgnore
	private String pwd;

	/**
	 * 登录时间
	 */
	@Column(name = "loginTime")
	private Date logintime;

	/**
	 * 审核状态,0：未审核；1：审核通过；2：审核未通过；
	 */
	private Integer audit;

	/**
	 * 获取成员所属部门id列表
	 *
	 * @return departments - 成员所属部门id列表
	 */
	public String getDepartments() {
		return departments;
	}

	/**
	 * 设置成员所属部门id列表
	 *
	 * @param departments
	 *            成员所属部门id列表
	 */
	public void setDepartments(String departments) {
		this.departments = departments;
	}

	/**
	 * 设置成员所属部门id列表
	 *
	 * @param departments
	 *            成员所属部门id列表
	 */
	public void setDepartments(List<Integer> department) {
		this.departments = department.toString().replaceAll("[\\,\\[\\]]", "|").replaceAll(" ", "");
	}

	/**
	 * 获取创建时间
	 *
	 * @return created - 创建时间
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * 设置创建时间
	 *
	 * @param created
	 *            创建时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * 获取删除标志，0：正常，1：回收站，2：永久删除
	 *
	 * @return deleted - 删除标志，0：正常，1：回收站，2：永久删除
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * 设置删除标志，0：正常，1：回收站，2：永久删除
	 *
	 * @param deleted
	 *            删除标志，0：正常，1：回收站，2：永久删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 * 获取修改时间
	 *
	 * @return updated - 修改时间
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updated
	 *            修改时间
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * 获取权限
	 *
	 * @return auth - 权限
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * 设置权限
	 *
	 * @param auth
	 *            权限
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * 获取微信唯一标志
	 *
	 * @return openid - 微信唯一标志
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 设置微信唯一标志
	 *
	 * @param openid
	 *            微信唯一标志
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 获取账号
	 *
	 * @return uname - 账号
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * 设置账号
	 *
	 * @param uname
	 *            账号
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * 获取密码
	 *
	 * @return pwd - 密码
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置密码
	 *
	 * @param pwd
	 *            密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 获取登录时间
	 *
	 * @return loginTime - 登录时间
	 */
	public Date getLogintime() {
		return logintime;
	}

	/**
	 * 设置登录时间
	 *
	 * @param logintime
	 *            登录时间
	 */
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	/**
	 * 获取审核状态,0：未审核；1：审核通过；2：审核未通过；
	 *
	 * @return audit - 审核状态,0：未审核；1：审核通过；2：审核未通过；
	 */
	public Integer getAudit() {
		return audit;
	}

	/**
	 * 设置审核状态,0：未审核；1：审核通过；2：审核未通过；
	 *
	 * @param audit
	 *            审核状态,0：未审核；1：审核通过；2：审核未通过；
	 */
	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", userid=").append(super.getUserid());
		sb.append(", name=").append(super.getName());
		sb.append(", departments=").append(departments);
		sb.append(", position=").append(super.getPosition());
		sb.append(", mobile=").append(super.getMobile());
		sb.append(", gender=").append(super.getGender());
		sb.append(", email=").append(super.getEmail());
		sb.append(", weixinid=").append(super.getWeixinid());
		sb.append(", avatar=").append(super.getAvatar());
		sb.append(", status=").append(super.getStatus());
		sb.append(", extattr=").append(super.getExtattr());
		sb.append(", created=").append(created);
		sb.append(", deleted=").append(deleted);
		sb.append(", updated=").append(updated);
		sb.append(", auth=").append(auth);
		sb.append(", openid=").append(openid);
		sb.append(", uname=").append(uname);
		sb.append(", pwd=").append(pwd);
		sb.append(", logintime=").append(logintime);
		sb.append(", audit=").append(audit);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Admin other = (Admin) that;
		return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getDepartments() == null ? other.getDepartments() == null : this.getDepartments().equals(other.getDepartments()))
				&& (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
				&& (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
				&& (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
				&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getWeixinid() == null ? other.getWeixinid() == null : this.getWeixinid().equals(other.getWeixinid()))
				&& (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getExtattr() == null ? other.getExtattr() == null : this.getExtattr().equals(other.getExtattr()))
				&& (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
				&& (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
				&& (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
				&& (this.getAuth() == null ? other.getAuth() == null : this.getAuth().equals(other.getAuth()))
				&& (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
				&& (this.getUname() == null ? other.getUname() == null : this.getUname().equals(other.getUname()))
				&& (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
				&& (this.getLogintime() == null ? other.getLogintime() == null : this.getLogintime().equals(other.getLogintime()))
				&& (this.getAudit() == null ? other.getAudit() == null : this.getAudit().equals(other.getAudit()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getDepartments() == null) ? 0 : getDepartments().hashCode());
		result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
		result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
		result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getWeixinid() == null) ? 0 : getWeixinid().hashCode());
		result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getExtattr() == null) ? 0 : getExtattr().hashCode());
		result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
		result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
		result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
		result = prime * result + ((getAuth() == null) ? 0 : getAuth().hashCode());
		result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
		result = prime * result + ((getUname() == null) ? 0 : getUname().hashCode());
		result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
		result = prime * result + ((getLogintime() == null) ? 0 : getLogintime().hashCode());
		result = prime * result + ((getAudit() == null) ? 0 : getAudit().hashCode());
		return result;
	}
}