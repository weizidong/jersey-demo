package com.wzd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.WelfareDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.History;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.enums.SexType;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.utils.Configs;
import com.wzd.utils.DateUtil;
import com.wzd.utils.EhcacheUtil;
import com.wzd.utils.MD5Utils;
import com.wzd.utils.UUIDUtil;

/**
 * 系统操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class InitService {
	public static final String ID = "00000000000000000000000000000000";
	@Autowired
	private WelfareDao welfareDao;
	@Autowired
	private AdminService adminService;
	@Autowired
	private FwWxService fwService;
	@Autowired
	private QyWxService qyService;
	@Autowired
	private WelfareService welfareService;

	/**
	 * 初始化全部
	 */
	public void initAll() {
		initWelfare();
		fwService.syncUser("");
		qyService.sync();
	}

	/**
	 * 初始化福利
	 */
	private void initWelfare() {
		welfareDao.delete(null, DeleteType.永久删除);
		Welfare w = new Welfare();
		w.setName("积分签到");
		w.setType(HistoryType.积分签到.getValue());
		w.setScore(Integer.parseInt(Configs.get("score")));
		welfareDao.create(w);
	}

	/**
	 * 创建测试数据
	 */
	public void test() {
		adminService.create(new Admin(ID, "测试管理员", "测试", "13000000000", SexType.男, "ceshi@163.com", "", "", "", MD5Utils.getMD5ofStr("123456", 2), AuditType.审核成功));
		createWelfare();

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
		w.setType(type.getValue()); // 类型
		w.setRule(("1、福利期间每人只能兑换" + time + "次福利。").getBytes()); // 规则
		welfareService.create(w, null);
	}

	/**
	 * 创建福利
	 */
	private void createWelfare() {
		createWelfare(15, "一元红包", 500, 2, 3000, HistoryType.红包福利);
		createWelfare(10, "电影票兑换", 1000, 1, 500, HistoryType.券票福利);
		createWelfare(20, "优惠券兑换", 2000, 1, 200, HistoryType.券票福利);
	}

	/**
	 * 创建签到历史
	 */
	public void signHistory() {
		Date date = new Date();
		int t = DateUtil.getWeekOfDate(date);
		List<History> list = new ArrayList<>();
		for (int i = 0; i < t; i++) {
			History h = new History();
			h.setContent("积分签到");
			h.setTitle("积分签到");
			h.setUserId("d025138d9b574ccd9b2736bfe16748f6");
			h.setId(UUIDUtil.get());
			h.setDeleled(DeleteType.未删除.getValue());
			h.setRecording(DateUtil.getBeforeDate(date, i));
			h.setScore(Integer.parseInt(Configs.get("score")));
			h.setType(HistoryType.积分签到.getValue());
			list.add(h);
		}
	}

	/**
	 * 清理测试数据
	 */
	public void clear() {
		EhcacheUtil.getInstance().clear();

	}

}
