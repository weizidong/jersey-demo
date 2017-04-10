package com.wzd.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.SubType;
import com.wzd.service.wechat.base.BaseResp;

/**
 * 用户实体
 * 
 * @author WeiZiDong
 *
 */
@SuppressWarnings("serial")
public class User extends BaseResp {
	@Id
	private String id;
	// 微信属性
	private Integer subscribe; // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String openid; // 用户的标识，对当前公众号唯一
	private String city; // 用户所在城市
	private String country; // 用户所在国家
	private String province; // 用户所在省份
	private String language; // 用户的语言，简体中文为zh_CN
	@Column(name = "subscribe_time")
	private Long subscribeTime;// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
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
	private String email; // 邮箱
	// 认证属性
	private String name; // 姓名
	@Column(name = "dep_id")
	private Integer depId; // 部门
	@Column(name = "dep_name")
	private String depName; // 部门名称
	@Column(name = "id_card")
	private String idCard; // 身份证编号
	private String position; // 岗位
	private Integer audit; // 认证状态
	private String auditor; // 审核人
	// 系统属性
	private Date login; // 登录时间
	private Date created; // 注册时间
	private Date updated; // 修改时间
	private Integer status; // 用户状态
	private String auth; // 权限
	private Integer score; // 积分
	@Column(name = "sign_num")
	private Integer signNum; // 连续签到次数
	private Integer deleted; // 删除标志
	// 关联属性
	@Transient
	private Integer msgNum;// 未读消息个数
	@Transient
	private Integer welfNum;// 福利个数
	@Transient
	private Integer actNum;// 活动个数
	@Transient
	private Integer sportNum;// 活动个数
	@Transient
	private Boolean isSign;// 是否已签到
	@Transient
	private Boolean exp; // 有无工作经验

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public Integer getSportNum() {
		return sportNum;
	}

	public void setSportNum(Integer sportNum) {
		this.sportNum = sportNum;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public void setSubscribe(SubType sub) {
		this.subscribe = sub.getValue();
	}

	public Boolean getExp() {
		return exp;
	}

	public void setExp(Boolean exp) {
		this.exp = exp;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setDeleted(DeleteType del) {
		this.deleted = del.getValue();
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

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public void setAudit(AuditType audit) {
		this.audit = audit.getValue();
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(Integer msgNum) {
		this.msgNum = msgNum;
	}

	public Integer getWelfNum() {
		return welfNum;
	}

	public void setWelfNum(Integer welfNum) {
		this.welfNum = welfNum;
	}

	public Integer getActNum() {
		return actNum;
	}

	public void setActNum(Integer actNum) {
		this.actNum = actNum;
	}

	public Boolean getIsSign() {
		return isSign;
	}

	public void setIsSign(Boolean isSign) {
		this.isSign = isSign;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSignNum() {
		return signNum;
	}

	public void setSignNum(Integer signNum) {
		this.signNum = signNum;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}