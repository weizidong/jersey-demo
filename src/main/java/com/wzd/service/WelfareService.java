package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.TicketDao;
import com.wzd.model.dao.WelfareDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.History;
import com.wzd.model.entity.Ticket;
import com.wzd.model.entity.User;
import com.wzd.model.entity.Welfare;
import com.wzd.model.enums.APPType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
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
	@Autowired
	private TicketDao ticketDao;

	/**
	 * 创建福利
	 */
	public Welfare create(Welfare w, Admin admin) {
		w.setAdminId(admin.getId());
		w = welfareDao.create(w);
		if (w.getType() == HistoryType.券票福利.getValue()) {
			ticketDao.create(w.getTotal(), w.getId());
		}
		return w;
	}

	/**
	 * 条件查询福利列表
	 */
	public PageInfo<Welfare> find(PageParam param, DeleteType del, Session session) {
		PageInfo<Welfare> page = welfareDao.find(param, del);
		if (session.getAppType() == null || !session.getAppType().equals(APPType.服务号) || page.getList() == null || page.getList().size() == 0) {
			return page;
		}
		User user = (User) session.getUser();
		page.getList().forEach(wel -> {
			wel.setDraw(wel.getTime() - historyDao.isDraw(wel.getId(), user.getId()));
		});
		return page;
	}

	/**
	 * 兑换福利
	 */
	public synchronized void convert(String welfareId, User user) {
		Welfare welfare = welfareDao.getById(welfareId, DeleteType.未删除);
		if (welfare == null) {
			throw new WebException(ResponseCode.资源不存在, "福利不存在");
		}
		Integer num = historyDao.isDraw(welfareId, user.getId());
		if (num >= welfare.getTime()) {
			throw new WebException(ResponseCode.不允许重复, "已领取过该福利");
		}
		if (welfare.getCurrent() >= welfare.getTotal()) {
			throw new WebException(ResponseCode.已领完, "该福利已领完");
		}
		String ticket = null;
		if (welfare.getType() == HistoryType.券票福利.getValue()) {
			Ticket t = ticketDao.getNew(welfare.getId());
			ticketDao.draw(t);
			ticket = t.getTicket();
		}
		if (welfare.getType() == HistoryType.红包福利.getValue()) {
			// TODO 发放福利，记录历史
			// RedPackService.sendRedPack(FwAPI.SEND_REDPACK, new
			// RedPack(send_name, user.getOpenid(), total_amount, wishing,
			// welfare.getName(), remark, risk_info));
		}
		welfare.setCurrent(welfare.getCurrent() + 1);
		welfareDao.update(welfare);
		historyDao.create(new History(user.getId(), welfare.getName(), null, -welfare.getScore(), ticket, welfare.getType(), welfareId));
	}

	/**
	 * 查询福利详情
	 */
	public Welfare findById(String welfareId, Integer delType) {
		return welfareDao.getById(welfareId, DeleteType.parse(delType));
	}

	/**
	 * 修改福利
	 */
	public void update(Welfare wel) {
		welfareDao.update(wel);
	}

	/**
	 * 删除福利
	 */
	public void delete(String welfareId, DeleteType type) {
		// TODO Auto-generated method stub

	}

}
