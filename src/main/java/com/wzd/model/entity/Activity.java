package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 线下活动
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Activity implements Serializable {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
	private String id;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 删除标志，0：未删除，1：回收站，2：永久删除
	 */
	private Integer deleted;

	/**
	 * 修改时间
	 */
	private Date updated;

	/**
	 * 状态，0：未开始，1：进行中，2：已结束
	 */
	private Integer status;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 活动主题
	 */
	private String title;

	/**
	 * 推送配图
	 */
	@Column(name = "pic_url")
	private String picUrl;

	/**
	 * 活动开始时间
	 */
	private Date start;

	/**
	 * 活动结束时间
	 */
	private Date end;

	/**
	 * 活动举办地点
	 */
	private String place;

	/**
	 * 最大参加人数
	 */
	private Integer total;

	/**
	 * 当前参加人数
	 */
	private Integer current;

	/**
	 * 发布人
	 */
	@Column(name = "pub_user")
	private Integer pubUser;

	/**
	 * 发布机构
	 */
	@Column(name = "dep_id")
	private Integer depId;

	/**
	 * 审核人
	 */
	private Integer auditor;

	/**
	 * 审核时间
	 */
	@Column(name = "aud_time")
	private Date audTime;

	/**
	 * 审核状态，0：未审核；1，审核通过；2审核未通过
	 */
	private Integer audit;

	/**
	 * 活动详情
	 */
	private byte[] detail;

	/**
	 * 获取ID
	 *
	 * @return id - ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
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
	 * 获取删除标志，0：未删除，1：回收站，2：永久删除
	 *
	 * @return deleted - 删除标志，0：未删除，1：回收站，2：永久删除
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * 设置删除标志，0：未删除，1：回收站，2：永久删除
	 *
	 * @param deleted
	 *            删除标志，0：未删除，1：回收站，2：永久删除
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
	 * 获取状态，0：未开始，1：进行中，2：已结束
	 *
	 * @return status - 状态，0：未开始，1：进行中，2：已结束
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态，0：未开始，1：进行中，2：已结束
	 *
	 * @param status
	 *            状态，0：未开始，1：进行中，2：已结束
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取类型
	 *
	 * @return type - 类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置类型
	 *
	 * @param type
	 *            类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取活动主题
	 *
	 * @return title - 活动主题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置活动主题
	 *
	 * @param title
	 *            活动主题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取推送配图
	 *
	 * @return pic_url - 推送配图
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置推送配图
	 *
	 * @param picUrl
	 *            推送配图
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 获取活动开始时间
	 *
	 * @return start - 活动开始时间
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * 设置活动开始时间
	 *
	 * @param start
	 *            活动开始时间
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * 获取活动结束时间
	 *
	 * @return end - 活动结束时间
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * 设置活动结束时间
	 *
	 * @param end
	 *            活动结束时间
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * 获取活动举办地点
	 *
	 * @return place - 活动举办地点
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * 设置活动举办地点
	 *
	 * @param place
	 *            活动举办地点
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * 获取最大参加人数
	 *
	 * @return total - 最大参加人数
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 设置最大参加人数
	 *
	 * @param total
	 *            最大参加人数
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 获取当前参加人数
	 *
	 * @return current - 当前参加人数
	 */
	public Integer getCurrent() {
		return current;
	}

	/**
	 * 设置当前参加人数
	 *
	 * @param current
	 *            当前参加人数
	 */
	public void setCurrent(Integer current) {
		this.current = current;
	}

	/**
	 * 获取发布人
	 *
	 * @return pub_user - 发布人
	 */
	public Integer getPubUser() {
		return pubUser;
	}

	/**
	 * 设置发布人
	 *
	 * @param pubUser
	 *            发布人
	 */
	public void setPubUser(Integer pubUser) {
		this.pubUser = pubUser;
	}

	/**
	 * 获取发布机构
	 *
	 * @return dep_id - 发布机构
	 */
	public Integer getDepId() {
		return depId;
	}

	/**
	 * 设置发布机构
	 *
	 * @param depId
	 *            发布机构
	 */
	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	/**
	 * 获取审核人
	 *
	 * @return auditor - 审核人
	 */
	public Integer getAuditor() {
		return auditor;
	}

	/**
	 * 设置审核人
	 *
	 * @param auditor
	 *            审核人
	 */
	public void setAuditor(Integer auditor) {
		this.auditor = auditor;
	}

	/**
	 * 获取审核时间
	 *
	 * @return aud_time - 审核时间
	 */
	public Date getAudTime() {
		return audTime;
	}

	/**
	 * 设置审核时间
	 *
	 * @param audTime
	 *            审核时间
	 */
	public void setAudTime(Date audTime) {
		this.audTime = audTime;
	}

	/**
	 * 获取审核状态，0：未审核；1，审核通过；2审核未通过
	 *
	 * @return audit - 审核状态，0：未审核；1，审核通过；2审核未通过
	 */
	public Integer getAudit() {
		return audit;
	}

	/**
	 * 设置审核状态，0：未审核；1，审核通过；2审核未通过
	 *
	 * @param audit
	 *            审核状态，0：未审核；1，审核通过；2审核未通过
	 */
	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	/**
	 * 获取活动详情
	 *
	 * @return detail - 活动详情
	 */
	public byte[] getDetail() {
		return detail;
	}

	/**
	 * 设置活动详情
	 *
	 * @param detail
	 *            活动详情
	 */
	public void setDetail(byte[] detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", created=").append(created);
		sb.append(", deleted=").append(deleted);
		sb.append(", updated=").append(updated);
		sb.append(", status=").append(status);
		sb.append(", type=").append(type);
		sb.append(", title=").append(title);
		sb.append(", picUrl=").append(picUrl);
		sb.append(", start=").append(start);
		sb.append(", end=").append(end);
		sb.append(", place=").append(place);
		sb.append(", total=").append(total);
		sb.append(", current=").append(current);
		sb.append(", pubUser=").append(pubUser);
		sb.append(", depId=").append(depId);
		sb.append(", auditor=").append(auditor);
		sb.append(", audTime=").append(audTime);
		sb.append(", audit=").append(audit);
		sb.append(", detail=").append(detail);
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
		Activity other = (Activity) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
				&& (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
				&& (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
				&& (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
				&& (this.getPicUrl() == null ? other.getPicUrl() == null : this.getPicUrl().equals(other.getPicUrl()))
				&& (this.getStart() == null ? other.getStart() == null : this.getStart().equals(other.getStart()))
				&& (this.getEnd() == null ? other.getEnd() == null : this.getEnd().equals(other.getEnd()))
				&& (this.getPlace() == null ? other.getPlace() == null : this.getPlace().equals(other.getPlace()))
				&& (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
				&& (this.getCurrent() == null ? other.getCurrent() == null : this.getCurrent().equals(other.getCurrent()))
				&& (this.getPubUser() == null ? other.getPubUser() == null : this.getPubUser().equals(other.getPubUser()))
				&& (this.getDepId() == null ? other.getDepId() == null : this.getDepId().equals(other.getDepId()))
				&& (this.getAuditor() == null ? other.getAuditor() == null : this.getAuditor().equals(other.getAuditor()))
				&& (this.getAudTime() == null ? other.getAudTime() == null : this.getAudTime().equals(other.getAudTime()))
				&& (this.getAudit() == null ? other.getAudit() == null : this.getAudit().equals(other.getAudit()))
				&& (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
		result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
		result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
		result = prime * result + ((getPicUrl() == null) ? 0 : getPicUrl().hashCode());
		result = prime * result + ((getStart() == null) ? 0 : getStart().hashCode());
		result = prime * result + ((getEnd() == null) ? 0 : getEnd().hashCode());
		result = prime * result + ((getPlace() == null) ? 0 : getPlace().hashCode());
		result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
		result = prime * result + ((getCurrent() == null) ? 0 : getCurrent().hashCode());
		result = prime * result + ((getPubUser() == null) ? 0 : getPubUser().hashCode());
		result = prime * result + ((getDepId() == null) ? 0 : getDepId().hashCode());
		result = prime * result + ((getAuditor() == null) ? 0 : getAuditor().hashCode());
		result = prime * result + ((getAudTime() == null) ? 0 : getAudTime().hashCode());
		result = prime * result + ((getAudit() == null) ? 0 : getAudit().hashCode());
		result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
		return result;
	}
}