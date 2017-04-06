package com.wzd.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;

/**
 * 招聘信息
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Recruit implements Serializable {
	@Id
	private String id; // ID
	private String name; // 招聘标题
	private Integer num; // 招聘人数
	private Integer current; // 当前应聘人数
	private Integer pay; // 薪资待遇
	private String company; // 招聘单位
	private String address; // 单位地址
	private Date start; // 开始时间
	private Date end; // 结束时间
	private String linkman = ""; // 联系人
	private String phone = ""; // 联系电话
	private String claim; // 招聘要求
	private String treatment; // 福利待遇
	@Column(name = "admin_id")
	private String adminId; // 发布者
	private Date created; // 发布时间
	private Integer deleted; // 删除标志
	private Integer status; // 状态
	// 管理属性
	@Transient
	private List<String> linkmans = new ArrayList<>();
	@Transient
	private List<String> phones = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
		this.linkmans = Arrays.stream(linkman.split("\\|")).collect(Collectors.toList());
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		this.phones = Arrays.stream(phone.split("\\|")).collect(Collectors.toList());
	}

	public String getClaim() {
		return claim;
	}

	public void setClaim(String claim) {
		this.claim = claim;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<String> linkmans) {
		this.linkmans = linkmans;
		this.linkman = linkmans.stream().collect(Collectors.joining("|"));
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
		this.phone = phones.stream().collect(Collectors.joining("|"));
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
