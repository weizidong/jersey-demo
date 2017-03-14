package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class File implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件名
     */
    private String name;

    /**
     * url地址
     */
    private String url;

    /**
     * 外键
     */
    private Integer fk;

    /**
     * 后缀名
     */
    private String suffix;

    /**
     * 上传者
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 上传时间
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
     * 状态
     */
    private Integer status;

    /**
     * 类型，0：头像；1：附件；2：重要文件
     */
    private Integer type;

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
     * 获取文件名
     *
     * @return name - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取url地址
     *
     * @return url - url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url地址
     *
     * @param url url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取外键
     *
     * @return fk - 外键
     */
    public Integer getFk() {
        return fk;
    }

    /**
     * 设置外键
     *
     * @param fk 外键
     */
    public void setFk(Integer fk) {
        this.fk = fk;
    }

    /**
     * 获取后缀名
     *
     * @return suffix - 后缀名
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置后缀名
     *
     * @param suffix 后缀名
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取上传者
     *
     * @return userId - 上传者
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置上传者
     *
     * @param userid 上传者
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取上传时间
     *
     * @return created - 上传时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置上传时间
     *
     * @param created 上传时间
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
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取类型，0：头像；1：附件；2：重要文件
     *
     * @return type - 类型，0：头像；1：附件；2：重要文件
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，0：头像；1：附件；2：重要文件
     *
     * @param type 类型，0：头像；1：附件；2：重要文件
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", fk=").append(fk);
        sb.append(", suffix=").append(suffix);
        sb.append(", userid=").append(userid);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", deleted=").append(deleted);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
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
        File other = (File) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getFk() == null ? other.getFk() == null : this.getFk().equals(other.getFk()))
            && (this.getSuffix() == null ? other.getSuffix() == null : this.getSuffix().equals(other.getSuffix()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getFk() == null) ? 0 : getFk().hashCode());
        result = prime * result + ((getSuffix() == null) ? 0 : getSuffix().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
}