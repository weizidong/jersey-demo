package com.wzd.model.entity;

import java.io.Serializable;
import java.util.Date;

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
	private Integer id;
	// 自有属性
	private Integer user_id;// 用户Id
	private String title;// 标题
	private String content;// 内容
	private Integer score;// 积分
	private String ticket;// 票券
	private Date recording;// 记录时间
	private Integer type;// 类型

	// 系统属性
	private Integer deleled;// 删除标志

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	@Override
	public String toString() {
		return "[id=" + id + ", user_id=" + user_id + ", title=" + title + ", content=" + content + ", score=" + score + ", ticket=" + ticket + ", recording=" + recording
				+ ", type=" + type + ", deleled=" + deleled + "]";
	}

}
