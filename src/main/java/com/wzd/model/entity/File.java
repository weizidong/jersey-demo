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
     * �ļ���
     */
    private String name;

    /**
     * url��ַ
     */
    private String url;

    /**
     * ���
     */
    private Integer fk;

    /**
     * ��׺��
     */
    private String suffix;

    /**
     * �ϴ���
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * �ϴ�ʱ��
     */
    private Date created;

    /**
     * �޸�ʱ��
     */
    private Date updated;

    /**
     * ɾ����־��0��δɾ����1������վ��2������ɾ��
     */
    private Integer deleted;

    /**
     * ״̬
     */
    private Integer status;

    /**
     * ���ͣ�0��ͷ��1��������2����Ҫ�ļ�
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    /**
     * ��ȡID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ�ļ���
     *
     * @return name - �ļ���
     */
    public String getName() {
        return name;
    }

    /**
     * �����ļ���
     *
     * @param name �ļ���
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡurl��ַ
     *
     * @return url - url��ַ
     */
    public String getUrl() {
        return url;
    }

    /**
     * ����url��ַ
     *
     * @param url url��ַ
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * ��ȡ���
     *
     * @return fk - ���
     */
    public Integer getFk() {
        return fk;
    }

    /**
     * �������
     *
     * @param fk ���
     */
    public void setFk(Integer fk) {
        this.fk = fk;
    }

    /**
     * ��ȡ��׺��
     *
     * @return suffix - ��׺��
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * ���ú�׺��
     *
     * @param suffix ��׺��
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * ��ȡ�ϴ���
     *
     * @return userId - �ϴ���
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * �����ϴ���
     *
     * @param userid �ϴ���
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * ��ȡ�ϴ�ʱ��
     *
     * @return created - �ϴ�ʱ��
     */
    public Date getCreated() {
        return created;
    }

    /**
     * �����ϴ�ʱ��
     *
     * @param created �ϴ�ʱ��
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * ��ȡ�޸�ʱ��
     *
     * @return updated - �޸�ʱ��
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * �����޸�ʱ��
     *
     * @param updated �޸�ʱ��
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * ��ȡɾ����־��0��δɾ����1������վ��2������ɾ��
     *
     * @return deleted - ɾ����־��0��δɾ����1������վ��2������ɾ��
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * ����ɾ����־��0��δɾ����1������վ��2������ɾ��
     *
     * @param deleted ɾ����־��0��δɾ����1������վ��2������ɾ��
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * ��ȡ״̬
     *
     * @return status - ״̬
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * ����״̬
     *
     * @param status ״̬
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * ��ȡ���ͣ�0��ͷ��1��������2����Ҫ�ļ�
     *
     * @return type - ���ͣ�0��ͷ��1��������2����Ҫ�ļ�
     */
    public Integer getType() {
        return type;
    }

    /**
     * �������ͣ�0��ͷ��1��������2����Ҫ�ļ�
     *
     * @param type ���ͣ�0��ͷ��1��������2����Ҫ�ļ�
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