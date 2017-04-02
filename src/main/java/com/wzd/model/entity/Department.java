package com.wzd.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 部门
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class Department extends BaseResp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 部门id，整型。指定时必须大于1，不指定时则自动生成
	// 微信属性
	private String name; // 部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\:*?"<>｜
	private Integer parentid; // 父亲部门id。根部门id为1
	@Column(name = "orders")
	private Integer order; // 在父部门中的次序值。order值小的排序靠前。
	// 自有属性

	// 系统属性
	private String admin; // 创建者
	private Integer deleted; // 删除标志
	// 关联属性
	@Transient
	private List<Department> child; // 子部门
	@Transient
	private List<Admin> admins;// 部门成员

	public Department() {
		super();
	}

	public Department(Integer id, String name, Integer parentid, Integer order, String admin, Integer deleted, List<Department> child) {
		super();
		this.id = id;
		this.name = name;
		this.parentid = parentid;
		this.order = order;
		this.admin = admin;
		this.deleted = deleted;
		this.child = child;
	}

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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setDeleted(DeleteType del) {
		this.deleted = del.getValue();
	}

	public List<Department> getChild() {
		return child;
	}

	public void setChild(List<Department> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}