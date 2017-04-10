package com.wzd.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wzd.model.dao.SettingDao;
import com.wzd.model.entity.Setting;
import com.wzd.model.enums.SceneType;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.service.wechat.msg.dto.ARTICLE;
import com.wzd.service.wechat.qrcode.QrcodeService;
import com.wzd.utils.Configs;
import com.wzd.utils.EhcacheUtil;

/**
 * 系统操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class SystemService {
	public static final String ID = "00000000000000000000000000000000";
	@Autowired
	private SettingDao settingDao;
	@Autowired
	private FwWxService fwService;
	@Autowired
	private QyWxService qyService;

	/**
	 * 初始化系统
	 */
	public void init() {
		// 同步服务号
		fwService.syncUser("");
		// 同步企业号
		qyService.sync();
		// 初始化系统设置
		setting(new Setting("userfiles/logo.png", "龙泉驿职工之家", JSON.toJSONString(
				new ARTICLE("欢迎关注\"龙泉驿职工之家\" |点我签到", "签到获得更多积分，可以兑换工会提供的各项福利以及参加各类活动！惊喜不断！你准备好了么？", Configs.hostname + "view/fwh/signin", Configs.hostname + "userfiles/sign.png")),
				100, 50));
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
		if (db == null || StringUtils.isBlank(db.getSportsTicket())) {
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
}
