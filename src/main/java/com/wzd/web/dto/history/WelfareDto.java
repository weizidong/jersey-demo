package com.wzd.web.dto.history;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.JSON;

/**
 * 福利Dto
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WelfareDto implements Serializable {
	private String id;
	// 自有属性
	@Column(name = "pic_url")
	private String picUrl; // 配图
	private String name; // 名称
	@Column(name = "end_time")
	private Date endTime;// 结束时间
	private String title;// 标题
	private String content;// 内容
	private Integer score;// 积分
	private String ticket;// 票券
	private Date recording;// 记录时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
