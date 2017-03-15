package com.wzd.model.entity;

import java.io.Serializable;
import java.util.List;

public class Admin implements Serializable {
	private Integer id; // ID
	private String userid; // 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
	private String name; // 成员名称。长度为1~64个字节
	private List<Integer> department; // 成员所属部门id列表,不超过20个
	private String position; // 职位信息。长度为0~64个字节
	private String mobile; // 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	private String gender; // 性别。1表示男性，2表示女性
	private String email; // 邮箱。长度为0~64个字节。企业内必须唯一
	private String weixinid; // 微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）
	private String avatar_mediaid; // 成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
	private String extattr; // 扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userid=" + userid + ", name=" + name + ", department=" + department + ", position=" + position + ", mobile=" + mobile + ", gender=" + gender
				+ ", email=" + email + ", weixinid=" + weixinid + ", avatar_mediaid=" + avatar_mediaid + ", extattr=" + extattr + "]";
	}

}