package com.wzd.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 用户
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class User extends BaseResp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ID

	// 微信属性
	private Integer subscribe; // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	@JsonIgnore
	private String openid; // 用户的标识，对当前公众号唯一
	private String nickname; // 用户的昵称
	private Integer sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String city; // 用户所在城市
	private String country; // 用户所在国家
	private String province; // 用户所在省份
	private String language; // 用户的语言，简体中文为zh_CN
	private String headimgurl; // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private Long subscribe_time; // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	@JsonIgnore
	private String unionid; // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String remark; // 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private Integer groupid; // 用户所在的分组ID（兼容旧的用户分组接口）
	@JsonIgnore
	@Transient
	private List<Integer> tagid_list; // 用户被打上的标签ID列表

	// 自有属性
	private Date updated; // 修改时间
	private Integer status; // 状态，0：未审核，1：审核通过，2：审核未通过
	private String auth; // 权限
	private String id_card; // 身份证编号
	private Date birthday; // 出生日期
	private String phone; // 联系方式
	private String dep_name; // 部门名称
	private String dep_address; // 单位地址
	private String dep_tel; // 单位电话
	private String email; // 电子邮箱
	private String address; // 住址
	private String position; // 岗位
	private Date login; // 登录时间

	// 关联属性

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public List<Integer> getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(List<Integer> tagid_list) {
		this.tagid_list = tagid_list;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_address() {
		return dep_address;
	}

	public void setDep_address(String dep_address) {
		this.dep_address = dep_address;
	}

	public String getDep_tel() {
		return dep_tel;
	}

	public void setDep_tel(String dep_tel) {
		this.dep_tel = dep_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", city=" + city + ", country=" + country
				+ ", province=" + province + ", language=" + language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid=" + unionid + ", remark="
				+ remark + ", groupid=" + groupid + ", tagid_list=" + tagid_list + ", updated=" + updated + ", status=" + status + ", auth=" + auth + ", id_card=" + id_card
				+ ", birthday=" + birthday + ", phone=" + phone + ", dep_name=" + dep_name + ", dep_address=" + dep_address + ", dep_tel=" + dep_tel + ", email=" + email
				+ ", address=" + address + ", position=" + position + ", login=" + login + "}";
	}

}