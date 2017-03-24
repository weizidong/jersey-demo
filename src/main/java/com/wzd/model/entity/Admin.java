package com.wzd.model.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 管理员
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Admin extends BaseResp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
	private String id; // ID
	private String userid; // 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
	private String name; // 成员名称。长度为1~64个字节
	@Transient
	private List<Integer> department;// 成员所属部门id列表,不超过20个
	private String position;// 职位信息。长度为0~64个字节
	private String mobile;// 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	private String gender;// 性别。1表示男性，2表示女性
	private String email;// 邮箱。长度为0~64个字节。企业内必须唯一
	private String weixinid;// 微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）
	@Transient
	@JsonIgnore
	private String avatar_mediaid;// 成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
	private String avatar;// 头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
	private Integer status;// 关注状态: 1=已关注，2=已禁用，4=未关注
	private String extattr;// 扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值
	private String departments; // 成员所属部门id列表
	private Date created; // 创建时间
	private Integer deleted; // 删除标志，0：正常，1：回收站，2：永久删除
	private Date updated; // 修改时间
	private String auth; // 权限
	@JsonIgnore
	private String openid; // 微信唯一标志
	@JsonIgnore
	private String pwd; // 密码
	private Date login; // 登录时间
	private Integer audit; // 审核状态,0：未审核；1：审核通过；2：审核未通过；

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		this.departments = department.toString().replaceAll("\\,\\[\\]", "|").replaceAll(" ", "");
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
		this.department = Arrays.stream(departments.split("|")).map(d -> Integer.parseInt(d)).collect(Collectors.toList());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", userid=" + userid + ", name=" + name + ", department=" + department + ", position=" + position + ", mobile=" + mobile + ", gender=" + gender
				+ ", email=" + email + ", weixinid=" + weixinid + ", avatar_mediaid=" + avatar_mediaid + ", avatar=" + avatar + ", status=" + status + ", extattr=" + extattr
				+ ", departments=" + departments + ", created=" + created + ", deleted=" + deleted + ", updated=" + updated + ", auth=" + auth + ", openid=" + openid + ", pwd="
				+ pwd + ", login=" + login + ", audit=" + audit + "}";
	}
}