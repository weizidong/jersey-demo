package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户历史记录
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class History implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public History() {
		super();
	}

	public History(String userId, String title, String content, Integer score, String ticket, Integer type,
			String welfareId) {
		super();
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

	public Integer getDeleled() {
		return deleled;
	}

	public void setDeleled(Integer deleled) {
		this.deleled = deleled;
	}

	public String getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content + ", score=" + score
				+ ", ticket=" + ticket + ", recording=" + recording + ", type=" + type + ", welfareId=" + welfareId
				+ ", deleled=" + deleled + "]";
	}

}
