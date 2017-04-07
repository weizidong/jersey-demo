package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;

/**
 * 用户历史记录
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class History implements Serializable {
	@Id
	private String id;
	// 自有属性
	@Column(name = "user_id")
	private String userId;// 用户Id
	private String title;// 标题
	private String content;// 内容
	private Integer score;// 积分
	private String ticket;// 票券
	private Date recording;// 记录时间
	private Integer type;// 类型
	@Column(name = "welfare_id")
	private String welfareId;// 兑换的福利
	// 系统属性
	private Integer deleled;// 删除标志
	private Date used;// 使用时间

	public History() {
	}
	public History(String userId, String title, String content, Integer score, String ticket, HistoryType type, String welfareId) {
		this(userId, title, content, score, ticket, type.getValue(), welfareId);
	}

	public History(String userId, String title, String content, Integer score, String ticket, Integer type, String welfareId) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.score = score;
		this.ticket = ticket;
		this.type = type;
		this.welfareId = welfareId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getRecording() {
		return recording;
	}

	public void setRecording(Date recording) {
		this.recording = recording;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setType(HistoryType type) {
		this.type = type.getValue();
	}

	public Integer getDeleled() {
		return deleled;
	}

	public void setDeleled(Integer deleled) {
		this.deleled = deleled;
	}

	public void setDeleled(DeleteType del) {
		this.deleled = del.getValue();
	}

	public String getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}

	public Date getUsed() {
		return used;
	}

	public void setUsed(Date used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
