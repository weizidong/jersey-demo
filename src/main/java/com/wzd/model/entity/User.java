package com.wzd.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wzd.service.wechat.user.FwUser;

public class User extends FwUser {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 注册时间
	 */
	private Date created;

	/**
	 * 删除标志，0：正常，1：回收站，2：永久删除
	 */
	private Integer deleted;

	/**
	 * 修改时间
	 */
	private Date updated;

	/**
	 * 状态，0：未审核，1：审核通过，2：审核未通过
	 */
	private Integer status;

	/**
	 * 权限
	 */
	private String auth;

	/**
	 * 身份证编号
	 */
	@Column(name = "id_card")
	private String idCard;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 联系方式
	 */
	private String phone;

	/**
	 * 部门名称
	 */
	@Column(name = "dep_name")
	private String depName;

	/**
	 * 单位地址
	 */
	@Column(name = "dep_address")
	private String depAddress;

	/**
	 * 单位电话
	 */
	@Column(name = "dep_tel")
	private String depTel;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 岗位
	 */
	private String position;

	/**
	 * 登录时间
	 */
	private Date login;

	private static final long serialVersionUID = 1L;

	/**
	 * 获取ID
	 *
	 * @return id - ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *            ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取注册时间
	 *
	 * @return created - 注册时间
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * 设置注册时间
	 *
	 * @param created
	 *            注册时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * 获取删除标志，0：正常，1：回收站，2：永久删除
	 *
	 * @return deleted - 删除标志，0：正常，1：回收站，2：永久删除
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * 设置删除标志，0：正常，1：回收站，2：永久删除
	 *
	 * @param deleted
	 *            删除标志，0：正常，1：回收站，2：永久删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 * 获取修改时间
	 *
	 * @return updated - 修改时间
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updated
	 *            修改时间
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * 获取状态，0：未审核，1：审核通过，2：审核未通过
	 *
	 * @return status - 状态，0：未审核，1：审核通过，2：审核未通过
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态，0：未审核，1：审核通过，2：审核未通过
	 *
	 * @param status
	 *            状态，0：未审核，1：审核通过，2：审核未通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取权限
	 *
	 * @return auth - 权限
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * 设置权限
	 *
	 * @param auth
	 *            权限
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * 获取身份证编号
	 *
	 * @return id_card - 身份证编号
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * 设置身份证编号
	 *
	 * @param idCard
	 *            身份证编号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 获取出生日期
	 *
	 * @return birthday - 出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生日期
	 *
	 * @param birthday
	 *            出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取联系方式
	 *
	 * @return phone - 联系方式
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系方式
	 *
	 * @param phone
	 *            联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取部门名称
	 *
	 * @return dep_name - 部门名称
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * 设置部门名称
	 *
	 * @param depName
	 *            部门名称
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * 获取单位地址
	 *
	 * @return dep_address - 单位地址
	 */
	public String getDepAddress() {
		return depAddress;
	}

	/**
	 * 设置单位地址
	 *
	 * @param depAddress
	 *            单位地址
	 */
	public void setDepAddress(String depAddress) {
		this.depAddress = depAddress;
	}

	/**
	 * 获取单位电话
	 *
	 * @return dep_tel - 单位电话
	 */
	public String getDepTel() {
		return depTel;
	}

	/**
	 * 设置单位电话
	 *
	 * @param depTel
	 *            单位电话
	 */
	public void setDepTel(String depTel) {
		this.depTel = depTel;
	}

	/**
	 * 获取电子邮箱
	 *
	 * @return email - 电子邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置电子邮箱
	 *
	 * @param email
	 *            电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取住址
	 *
	 * @return address - 住址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置住址
	 *
	 * @param address
	 *            住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取岗位
	 *
	 * @return position - 岗位
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 设置岗位
	 *
	 * @param position
	 *            岗位
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 获取登录时间
	 *
	 * @return login - 登录时间
	 */
	public Date getLogin() {
		return login;
	}

	/**
	 * 设置登录时间
	 *
	 * @param login
	 *            登录时间
	 */
	public void setLogin(Date login) {
		this.login = login;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", created=").append(created);
		sb.append(", deleted=").append(deleted);
		sb.append(", updated=").append(updated);
		sb.append(", status=").append(status);
		sb.append(", nickname=").append(super.getNickname());
		sb.append(", openid=").append(super.getOpenid());
		sb.append(", auth=").append(auth);
		sb.append(", sex=").append(super.getSex());
		sb.append(", idCard=").append(idCard);
		sb.append(", birthday=").append(birthday);
		sb.append(", phone=").append(phone);
		sb.append(", depName=").append(depName);
		sb.append(", depAddress=").append(depAddress);
		sb.append(", depTel=").append(depTel);
		sb.append(", email=").append(email);
		sb.append(", address=").append(address);
		sb.append(", headimgurl=").append(super.getHeadimgurl());
		sb.append(", position=").append(position);
		sb.append(", login=").append(login);
		sb.append(", province=").append(super.getProvince());
		sb.append(", city=").append(super.getCity());
		sb.append(", country=").append(super.getCountry());
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		User other = (User) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
				&& (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
				&& (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
				&& (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
				&& (this.getAuth() == null ? other.getAuth() == null : this.getAuth().equals(other.getAuth()))
				&& (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
				&& (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
				&& (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
				&& (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
				&& (this.getDepName() == null ? other.getDepName() == null : this.getDepName().equals(other.getDepName()))
				&& (this.getDepAddress() == null ? other.getDepAddress() == null : this.getDepAddress().equals(other.getDepAddress()))
				&& (this.getDepTel() == null ? other.getDepTel() == null : this.getDepTel().equals(other.getDepTel()))
				&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
				&& (this.getHeadimgurl() == null ? other.getHeadimgurl() == null : this.getHeadimgurl().equals(other.getHeadimgurl()))
				&& (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
				&& (this.getLogin() == null ? other.getLogin() == null : this.getLogin().equals(other.getLogin()))
				&& (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
				&& (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
				&& (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
		result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
		result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
		result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
		result = prime * result + ((getAuth() == null) ? 0 : getAuth().hashCode());
		result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
		result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
		result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result + ((getDepName() == null) ? 0 : getDepName().hashCode());
		result = prime * result + ((getDepAddress() == null) ? 0 : getDepAddress().hashCode());
		result = prime * result + ((getDepTel() == null) ? 0 : getDepTel().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
		result = prime * result + ((getHeadimgurl() == null) ? 0 : getHeadimgurl().hashCode());
		result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
		result = prime * result + ((getLogin() == null) ? 0 : getLogin().hashCode());
		result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
		result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
		result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
		return result;
	}
}