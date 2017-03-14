package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Entryform implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 活动ID
     */
    @Column(name = "activityId")
    private Integer activityid;

    /**
     * 报名时间
     */
    private Date time;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 删除标志，0：未删除；1：回收站；2：永久删除
     */
    private Integer deleted;

    /**
     * 状态，0：未到场；1：已到场；
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取活动ID
     *
     * @return activityId - 活动ID
     */
    public Integer getActivityid() {
        return activityid;
    }

    /**
     * 设置活动ID
     *
     * @param activityid 活动ID
     */
    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    /**
     * 获取报名时间
     *
     * @return time - 报名时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置报名时间
     *
     * @param time 报名时间
     */
    public void setTime(Date time) {
        this.time = time;
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
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
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
     * @param updated 修改时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取删除标志，0：未删除；1：回收站；2：永久删除
     *
     * @return deleted - 删除标志，0：未删除；1：回收站；2：永久删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志，0：未删除；1：回收站；2：永久删除
     *
     * @param deleted 删除标志，0：未删除；1：回收站；2：永久删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取状态，0：未到场；1：已到场；
     *
     * @return status - 状态，0：未到场；1：已到场；
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，0：未到场；1：已到场；
     *
     * @param status 状态，0：未到场；1：已到场；
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", activityid=").append(activityid);
        sb.append(", time=").append(time);
        sb.append(", type=").append(type);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", deleted=").append(deleted);
        sb.append(", status=").append(status);
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
        Entryform other = (Entryform) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getActivityid() == null ? other.getActivityid() == null : this.getActivityid().equals(other.getActivityid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getActivityid() == null) ? 0 : getActivityid().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}