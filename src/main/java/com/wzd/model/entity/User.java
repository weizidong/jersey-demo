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
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	private String id;
	// 微信属性
	private Integer subscribe; // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	@JsonIgnore
	private String openid; // 用户的标识，对当前公众号唯一
	private String city; // 用户所在城市
	private String country; // 用户所在国家
	private String province; // 用户所在省份
	private String language; // 用户的语言，简体中文为zh_CN
	private Long subscribe_time; // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	@JsonIgnore
	private String unionid; // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private Integer groupid; // 用户所在的分组ID（兼容旧的用户分组接口）
	@JsonIgnore
	@Transient
	private List<Integer> tagid_list; // 用户被打上的标签ID列表
	// 自定义属性
	private String headimgurl; // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String nickname; // 用户的昵称
	private Integer sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String remark; // 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	// 自有属性
	private Date birthday; // 出生日期
	private Integer marriage; // 婚姻
	private String phone; // 联系电话
	private String address; // 住址
	// 认证属性
	private String name; // 姓名
	private String dep_id; // 部门
	private String id_card; // 身份证编号
	private String position; // 岗位
	private Integer audit; // 状态，0：未审核，1：审核通过，2：审核未通过
	private Integer auditor; // 审核人
	// 系统属性
	private Date login; // 登录时间
	private Date updated; // 修改时间
	private String auth; // 权限
	private Long score; // 积分
	// 关联属性
	@Transient
	private Integer msg_time;// 未读消息个数
	@Transient
	private Integer welf_time;// 福利个数
	@Transient
	private Integer act_time;// 活动个数

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDep_id() {
		return dep_id;
	}

	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getAuditor() {
		return auditor;
	}

	public void setAuditor(Integer auditor) {
		this.auditor = auditor;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
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

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Integer getMsg_time() {
		return msg_time;
	}

	public void setMsg_time(Integer msg_time) {
		this.msg_time = msg_time;
	}

	public Integer getWelf_time() {
		return welf_time;
	}

	public void setWelf_time(Integer welf_time) {
		this.welf_time = welf_time;
	}

	public Integer getAct_time() {
		return act_time;
	}

	public void setAct_time(Integer act_time) {
		this.act_time = act_time;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", city=" + city + ", country=" + country + ", province=" + province + ", language=" + language
				+ ", subscribe_time=" + subscribe_time + ", unionid=" + unionid + ", groupid=" + groupid + ", tagid_list=" + tagid_list + ", headimgurl=" + headimgurl
				+ ", nickname=" + nickname + ", sex=" + sex + ", remark=" + remark + ", birthday=" + birthday + ", marriage=" + marriage + ", phone=" + phone + ", address="
				+ address + ", name=" + name + ", dep_id=" + dep_id + ", id_card=" + id_card + ", position=" + position + ", audit=" + audit + ", auditor=" + auditor + ", login="
				+ login + ", updated=" + updated + ", auth=" + auth + ", score=" + score + ", msg_time=" + msg_time + ", welf_time=" + welf_time + ", act_time=" + act_time + "]";
	}

}