package com.wzd.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wzd.model.dao.SettingDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Setting;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.AuthType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.EntryType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.enums.SceneType;
import com.wzd.model.enums.SexType;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.msg.dto.ARTICLE;
import com.wzd.service.wechat.qrcode.QrcodeService;
import com.wzd.utils.Configs;
import com.wzd.utils.DateUtil;
import com.wzd.utils.EhcacheUtil;
import com.wzd.utils.MD5Utils;

/**
 * 系统操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class SystemService {
	public static final String ID = "00000000000000000000000000000000";
	public static final String USER_ID = "ceshiguanliyuan";
	@Autowired
	private SettingDao settingDao;
	@Autowired
	private AdminService adminService;
	@Autowired
	private FwWxService fwService;
	@Autowired
	private QyWxService qyService;
	@Autowired
	private WelfareService welfareService;
	@Autowired
	private ActivityService activityService;

	/**
	 * 初始化系统
	 */
	public void init() {
		// 同步服务号
		fwService.syncUser("");
		// 同步企业号
		qyService.sync();
		// 初始化系统设置
		setting(new Setting("userfiles/logo.png", "龙泉驿职工之家", JSON.toJSONString(new ARTICLE("欢迎关注\"龙泉驿职工之家\" |点我签到", "签到获得更多积分，可以兑换工会提供的各项福利以及参加各类活动！惊喜不断！你准备好了么？",
				Configs.hostname + "view/fwh/center", Configs.hostname + "userfiles/signPic.png")), 100, 50));
	}

	/**
	 * 创建测试数据
	 */
	public void test() {
		Admin a = new Admin(ID, USER_ID, "测试管理员", "测试", "13000000000", SexType.男, "ceshi@163.com", null, "userfiles/logo.png", AuthType.所有权限, MD5Utils.getMD5ofStr("123456", 2),
				AuditType.审核成功);
		a.setDepartments("|1|");
		adminService.create(a);
		createWelfare(15, "一元红包", 500, 2, 3000, HistoryType.红包福利);
		createWelfare(10, "电影票兑换", 1000, 1, 500, HistoryType.券票福利);
		createWelfare(20, "优惠券兑换", 2000, 1, 200, HistoryType.券票福利);
		createActivity(a);
	}

	/**
	 * 删除测试数据
	 */
	public void delTest() {
		adminService.delete(USER_ID, DeleteType.永久删除);
		welfareService.deleteByAdmin(ID, DeleteType.永久删除);
		activityService.deleteByAdmin(ID, DeleteType.永久删除);
	}

	/**
	 * 清理缓存
	 */
	public void clear() {
		EhcacheUtil.getInstance().clear();
	}

	/**
	 * 系统设置
	 */
	public void setting(Setting s) {
		s.setId(ID);
		Setting db = settingDao.getById(ID);
		if (db != null && StringUtils.isBlank(db.getSportsTicket())) {
			s.setSportsTicket(QrcodeService.getFixedQrcodeUrl(FwAPI.CREATE_QRCODE, FwWxService.getToken(), SceneType.服务号健身运动签到.getValue()));
		}
		if (db == null) {
			settingDao.create(s);
		} else {
			settingDao.update(s);
		}
	}

	/**
	 * 获取系统设置
	 */
	public Setting getSetting() {
		return settingDao.getById(ID);
	}

	/**
	 * 创建福利
	 */
	private void createWelfare(Integer days, String title, Integer score, Integer time, Integer total, HistoryType type) {
		Welfare w = new Welfare();
		Date date = new Date();
		w.setProvider("成都爱创业科技有限公司"); // 提供者
		w.setWebsite("http://www.ichuangye.cn"); // 提供者网站
		w.setStartTime(DateUtil.getBeforeDate(date, days)); // 开始时间
		w.setEndTime(DateUtil.getAfterDate(date, days)); // 结束时间
		w.setName(title); // 名称
		w.setScore(score); // 积分
		w.setTime(time); // 次数
		w.setTotal(total); // 总的个数
		w.setType(type); // 类型
		w.setRule("1、福利期间每人只能兑换" + time + "次福利。"); // 规则
		Admin a = new Admin();
		a.setId(ID);
		welfareService.create(w, a);
	}

	/**
	 * 创建活动
	 */
	private void createActivity(Admin admin) {
		Date d = new Date();
		Activity a = new Activity("user", "龙泉驿区职工年中盛典", DateUtil.getBeforeDate(d, 10), DateUtil.getAfterDate(d, 10), DateUtil.getBeforeDate(d, 8), DateUtil.getAfterDate(d, 5),
				EntryType.所有用户可报名, 500, "龙泉驿区总工会", "成都爱创业科技有限公司", "成都爱创业科技有限公司", "成都市龙泉驿区红光路128号兴隆广场127号1002会议室", "http://www.ichuangye.cn", "1、福利期间每人只能兑换次福利。", "1、福利期间每人只能兑换次福利。",
				2000, ActivityType.普通活动);
		activityService.create(a, admin);
	}

}
