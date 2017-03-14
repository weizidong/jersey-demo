package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Wxactivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 状态
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
     * 开始时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;

    /**
     * 活动口令
     */
    private String command;

    /**
     * 最大参加人数
     */
    private Integer totle;

    /**
     * 当前参加人数
     */
    private Integer current;

    /**
     * 平均金额
     */
    private Double avg;

    /**
     * 祝福语
     */
    private String wishing;

    /**
     * 活动回复语
     */
    private String reply;

    /**
     * 重复提示
     */
    private String repetition;

    /**
     * 领完提示
     */
    private String finish;

    /**
     * 活动结束语
     */
    private String over;

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
     * 审核状态,0：未审核；1：审核通过；2：审核未通过；
     */
    private Integer audit;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
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
     * @param title 活动主题
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
     * @param picUrl 推送配图
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取开始时间
     *
     * @return start - 开始时间
     */
    public Date getStart() {
        return start;
    }

    /**
     * 设置开始时间
     *
     * @param start 开始时间
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     *
     * @return end - 结束时间
     */
    public Date getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     *
     * @param end 结束时间
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * 获取活动口令
     *
     * @return command - 活动口令
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设置活动口令
     *
     * @param command 活动口令
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * 获取最大参加人数
     *
     * @return totle - 最大参加人数
     */
    public Integer getTotle() {
        return totle;
    }

    /**
     * 设置最大参加人数
     *
     * @param totle 最大参加人数
     */
    public void setTotle(Integer totle) {
        this.totle = totle;
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
     * @param current 当前参加人数
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }

    /**
     * 获取平均金额
     *
     * @return avg - 平均金额
     */
    public Double getAvg() {
        return avg;
    }

    /**
     * 设置平均金额
     *
     * @param avg 平均金额
     */
    public void setAvg(Double avg) {
        this.avg = avg;
    }

    /**
     * 获取祝福语
     *
     * @return wishing - 祝福语
     */
    public String getWishing() {
        return wishing;
    }

    /**
     * 设置祝福语
     *
     * @param wishing 祝福语
     */
    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    /**
     * 获取活动回复语
     *
     * @return reply - 活动回复语
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置活动回复语
     *
     * @param reply 活动回复语
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * 获取重复提示
     *
     * @return repetition - 重复提示
     */
    public String getRepetition() {
        return repetition;
    }

    /**
     * 设置重复提示
     *
     * @param repetition 重复提示
     */
    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    /**
     * 获取领完提示
     *
     * @return finish - 领完提示
     */
    public String getFinish() {
        return finish;
    }

    /**
     * 设置领完提示
     *
     * @param finish 领完提示
     */
    public void setFinish(String finish) {
        this.finish = finish;
    }

    /**
     * 获取活动结束语
     *
     * @return over - 活动结束语
     */
    public String getOver() {
        return over;
    }

    /**
     * 设置活动结束语
     *
     * @param over 活动结束语
     */
    public void setOver(String over) {
        this.over = over;
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
     * @param pubUser 发布人
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
     * @param depId 发布机构
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
     * @param auditor 审核人
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
     * @param audTime 审核时间
     */
    public void setAudTime(Date audTime) {
        this.audTime = audTime;
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
     * @param audit 审核状态,0：未审核；1：审核通过；2：审核未通过；
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
        sb.append(", id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", deleted=").append(deleted);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", command=").append(command);
        sb.append(", totle=").append(totle);
        sb.append(", current=").append(current);
        sb.append(", avg=").append(avg);
        sb.append(", wishing=").append(wishing);
        sb.append(", reply=").append(reply);
        sb.append(", repetition=").append(repetition);
        sb.append(", finish=").append(finish);
        sb.append(", over=").append(over);
        sb.append(", pubUser=").append(pubUser);
        sb.append(", depId=").append(depId);
        sb.append(", auditor=").append(auditor);
        sb.append(", audTime=").append(audTime);
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
        Wxactivity other = (Wxactivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getPicUrl() == null ? other.getPicUrl() == null : this.getPicUrl().equals(other.getPicUrl()))
            && (this.getStart() == null ? other.getStart() == null : this.getStart().equals(other.getStart()))
            && (this.getEnd() == null ? other.getEnd() == null : this.getEnd().equals(other.getEnd()))
            && (this.getCommand() == null ? other.getCommand() == null : this.getCommand().equals(other.getCommand()))
            && (this.getTotle() == null ? other.getTotle() == null : this.getTotle().equals(other.getTotle()))
            && (this.getCurrent() == null ? other.getCurrent() == null : this.getCurrent().equals(other.getCurrent()))
            && (this.getAvg() == null ? other.getAvg() == null : this.getAvg().equals(other.getAvg()))
            && (this.getWishing() == null ? other.getWishing() == null : this.getWishing().equals(other.getWishing()))
            && (this.getReply() == null ? other.getReply() == null : this.getReply().equals(other.getReply()))
            && (this.getRepetition() == null ? other.getRepetition() == null : this.getRepetition().equals(other.getRepetition()))
            && (this.getFinish() == null ? other.getFinish() == null : this.getFinish().equals(other.getFinish()))
            && (this.getOver() == null ? other.getOver() == null : this.getOver().equals(other.getOver()))
            && (this.getPubUser() == null ? other.getPubUser() == null : this.getPubUser().equals(other.getPubUser()))
            && (this.getDepId() == null ? other.getDepId() == null : this.getDepId().equals(other.getDepId()))
            && (this.getAuditor() == null ? other.getAuditor() == null : this.getAuditor().equals(other.getAuditor()))
            && (this.getAudTime() == null ? other.getAudTime() == null : this.getAudTime().equals(other.getAudTime()))
            && (this.getAudit() == null ? other.getAudit() == null : this.getAudit().equals(other.getAudit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPicUrl() == null) ? 0 : getPicUrl().hashCode());
        result = prime * result + ((getStart() == null) ? 0 : getStart().hashCode());
        result = prime * result + ((getEnd() == null) ? 0 : getEnd().hashCode());
        result = prime * result + ((getCommand() == null) ? 0 : getCommand().hashCode());
        result = prime * result + ((getTotle() == null) ? 0 : getTotle().hashCode());
        result = prime * result + ((getCurrent() == null) ? 0 : getCurrent().hashCode());
        result = prime * result + ((getAvg() == null) ? 0 : getAvg().hashCode());
        result = prime * result + ((getWishing() == null) ? 0 : getWishing().hashCode());
        result = prime * result + ((getReply() == null) ? 0 : getReply().hashCode());
        result = prime * result + ((getRepetition() == null) ? 0 : getRepetition().hashCode());
        result = prime * result + ((getFinish() == null) ? 0 : getFinish().hashCode());
        result = prime * result + ((getOver() == null) ? 0 : getOver().hashCode());
        result = prime * result + ((getPubUser() == null) ? 0 : getPubUser().hashCode());
        result = prime * result + ((getDepId() == null) ? 0 : getDepId().hashCode());
        result = prime * result + ((getAuditor() == null) ? 0 : getAuditor().hashCode());
        result = prime * result + ((getAudTime() == null) ? 0 : getAudTime().hashCode());
        result = prime * result + ((getAudit() == null) ? 0 : getAudit().hashCode());
        return result;
    }
}