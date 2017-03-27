package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.WelfareDao;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.utils.Configs;

/**
 * 系统操作服务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class InitService {
	@Autowired
	private WelfareDao welfareDao;
	@Autowired
	private FwWxService fwService;
	@Autowired
	private QyWxService qyService;

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
	public void initWelfare() {
		welfareDao.delete(null, DeleteType.永久删除);
		Welfare w = new Welfare();
		w.setName("积分签到");
		w.setType(HistoryType.积分签到.getValue());
		w.setScore(Integer.parseInt(Configs.get("score")));
		welfareDao.create(w);
	}

}
