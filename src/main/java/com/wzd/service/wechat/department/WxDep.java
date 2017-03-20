package com.wzd.service.wechat.department;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

import com.wzd.service.wechat.base.BaseResp;

/**
 * 企业号--->部门
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class WxDep extends BaseResp implements Serializable {
	@Id
	private Integer id; // 部门id，整型。指定时必须大于1，不指定时则自动生成
	private String name; // 部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\:*?"<>｜
	private Integer parentid; // 父亲部门id。根部门id为1
	@Column(name = "orders")
	private Integer order; // 在父部门中的次序值。order值小的排序靠前。

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return super.toString() + ", id=" + id + ", name=" + name + ", parentid=" + parentid + ", order=" + order + "}";
	}

}