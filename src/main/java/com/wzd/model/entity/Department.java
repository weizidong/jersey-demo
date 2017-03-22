package com.wzd.model.entity;

import java.util.List;

import javax.persistence.Transient;

import com.wzd.service.wechat.department.WxDep;

/**
 * 部门
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Department extends WxDep {
	private String admin;
	private Integer deleted;
	@Transient
	private List<Department> child;

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public List<Department> getChild() {
		return child;
	}

	public void setChild(List<Department> child) {
		this.child = child;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n {");
		sb.append("id:").append(super.getId());
		sb.append(", name:").append(super.getName());
		sb.append(", deleted:").append(deleted);
		sb.append(", parentid:").append(super.getParentid());
		sb.append(", order:").append(super.getOrder());
		sb.append(", admin:").append(admin);
		sb.append(", child:").append(child);
		sb.append("}");
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
		Department other = (Department) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
				&& (this.getParentid() == null ? other.getParentid() == null : this.getParentid().equals(other.getParentid()))
				&& (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
				&& (this.getAdmin() == null ? other.getAdmin() == null : this.getAdmin().equals(other.getAdmin()))
				&& (this.getChild() == null ? other.getChild() == null : this.getChild().equals(other.getChild()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
		result = prime * result + ((getParentid() == null) ? 0 : getParentid().hashCode());
		result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
		result = prime * result + ((getAdmin() == null) ? 0 : getAdmin().hashCode());
		result = prime * result + ((getChild() == null) ? 0 : getChild().hashCode());
		return result;
	}

}