package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.WelfareDao;
import com.wzd.model.entity.User;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.APPType;
import com.wzd.web.dto.session.Session;
import com.wzd.web.param.PageParam;

/**
 * 福利业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WelfareService {
	@Autowired
	private WelfareDao welfareDao;
	@Autowired
	private HistoryDao historyDao;

	/**
	 * 创建福利
	 */
	public Welfare create(Welfare w) {
		// TODO 创建福利
		return null;
	}

	/**
	 * 条件查询福利列表
	 */
	public PageInfo<Welfare> find(PageParam param, Session session) {
		PageInfo<Welfare> page = welfareDao.find(param);
		if (session.getAppType() == null || !session.getAppType().equals(APPType.服务号) || page.getList() == null || page.getList().size() == 0) {
			return page;
		}

		User user = (User) session.getUser();
		page.getList().forEach(wel -> {
			wel.setDraw(wel.getTime() - historyDao.isDraw(wel.getId(), user.getId()));
		});
		return page;
	}

}
