package com.wzd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.SportsDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.History;
import com.wzd.model.entity.Sports;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.enums.StateType;
import com.wzd.utils.DateUtil;
import com.wzd.web.dto.entryForm.EntryFormDto;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.PageParam;

/**
 * 健身活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class SportsService {
	@Autowired
	private SportsDao sportsDao;
	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private EntryformDao entryformDao;

	/**
	 * 创建健身活动
	 */
	public Sports create(Sports s, Admin admin) {
		s.setAdminId(admin.getId());
		return sportsDao.create(s);
	}

	/**
	 * 修改健身活动
	 */
	public void update(Sports s) {
		sportsDao.update(s);
	}

	/**
	 * 开启或关闭健身活动
	 */
	public void pause(String id) {
		Sports s = sportsDao.getById(id);
		if (s == null) {
			throw new WebException(ResponseCode.资源不存在, "健身活动" + id + "不存在");
		}
		if (s.getStatus() != StateType.暂停.getValue()) {
			s.setStatus(StateType.暂停);
		} else {
			s.setStatus(StateType.进行中);
		}
		update(s);
	}

	/**
	 * 删除健身活动
	 */
	public void delete(String id, DeleteType del) {
		sportsDao.delete(id, del);
	}

	/**
	 * 获取健身活动列表
	 */
	public PageInfo<Sports> list(PageParam param) {
		return sportsDao.list(param);
	}

	/**
	 * 获取健身活动报名列表
	 */
	public PageInfo<EntryFormDto> entryList(PageParam param, String id) {
		return entryformDao.entryList(param, id);
	}

	/**
	 * 报名健身活动
	 */
	public void entry(Sports s, User user) {
		if (s == null || s.getDates().size() != 1 || s.getStarts().size() == 0 || s.getEnds().size() == 0) {
			throw new WebException(ResponseCode.错误请求, "参数错误");
		}
		Sports db = sportsDao.getById(s.getId());
		if (db.getStatus() == StateType.暂停.getValue()) {
			throw new WebException(ResponseCode.已暂停);
		}
		if (db.getStatus() == StateType.已结束.getValue()) {
			throw new WebException(ResponseCode.已结束);
		}
		if (db.getStatus() == StateType.未开始.getValue()) {
			throw new WebException(ResponseCode.未开始);
		}
		if (db.getScore() > user.getScore()) {
			throw new WebException(ResponseCode.积分不够);
		}
		String date = DateUtil.formatDate(s.getDates().get(0), DateUtil.P_DATE);
		for (int i = 0; i < s.getStarts().size(); i++) {
			String start = DateUtil.formatDate(s.getDates().get(0), DateUtil.P_TIME);
			String end = DateUtil.formatDate(s.getEnds().get(0), DateUtil.P_TIME);
			Entryform ef = new Entryform(user.getOpenid(), s.getId(), ActivityType.健身活动, DateUtil.parseToDate(date + start, DateUtil.P_DATETIME),
					DateUtil.parseToDate(date + end, DateUtil.P_DATETIME));
			if (entryformDao.isEntry(ef)) {
				throw new WebException(ResponseCode.已报名);
			}
			entryformDao.entry(ef);
			historyDao.create(new History(user.getId(), db.getName(), null, -db.getScore(), null, HistoryType.健身活动, db.getId()));
		}
	}

	/**
	 * 签到健身活动
	 */
	public ResponseCode sign(User user) {
		List<Entryform> efs = entryformDao.list(new Entryform(user.getOpenid(), ActivityType.健身活动, DeleteType.未删除, StateType.进行中), new Date());
		if (efs == null || efs.size() < 1) {
			return ResponseCode.未报名;
		}
		Entryform ef = efs.get(0);
		entryformDao.sign(ef);
		return ResponseCode.成功;
	}

	/**
	 * 获取健身活动详情
	 */
	public Sports getById(String id) {
		return sportsDao.getById(id);
	}

}
