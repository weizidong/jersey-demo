package com.wzd.web.dto.history;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class SignDto implements Serializable {
	private String name;
	private String nickname;
	private String dep_name;
	private Integer audit;
	private Integer score;
	private Date recording;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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
