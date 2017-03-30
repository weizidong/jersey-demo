package com.wzd.model.entity;

import java.io.Serializable;

import javax.persistence.Id;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class Setting implements Serializable {
	@Id
	private String id;
	private String logo; // logo
	private String name; // name
	private String sub; // 关注信息
	private Integer score; // 关注积分
	private Integer sign; // 签到积分基数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Setting() {
		super();
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Setting(String logo, String name, String sub, Integer score, Integer sign) {
		this.logo = logo;
		this.name = name;
		this.sub = sub;
		this.score = score;
		this.sign = sign;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
