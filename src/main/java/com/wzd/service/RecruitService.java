package com.wzd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.RecruitDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.Recruit;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.utils.FileUtil;
import com.wzd.utils.PoiExcelUtils;
import com.wzd.web.dto.entryForm.EntryFormDto;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.PageParam;

/**
 * 招聘信息业务类
 * 
 * @author WeiZiDong
 *
 */
@Service
public class RecruitService {
	@Autowired
	private RecruitDao recruitDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryformDao entryformDao;

	/**
	 * 创建
	 */
	public Recruit create(Recruit r, Admin admin) {
		r.setAdminId(admin.getId());
		recruitDao.create(r);
		return r;
	}

	/**
	 * 报名
	 */
	public void entry(String id, User u) {
		Entryform ef = new Entryform(u.getOpenid(), id, ActivityType.招聘活动);
		if (entryformDao.isEntry(ef)) {
			throw new WebException(ResponseCode.已报名);
		}
		userDao.update(u);
		ef.setExp(u.getExp());
		entryformDao.entry(ef);
	}

	/**
	 * 获取详情
	 */
	public Recruit getById(String id) {
		return recruitDao.getById(id);
	}

	/**
	 * 获取列表
	 */
	public PageInfo<Recruit> find(PageParam param) {
		return recruitDao.find(param);
	}

	/**
	 * 导出应聘列表
	 */
	public String export(PageParam param, String id) {
		String[] headers = new String[] { "姓名@name", "生日@birthday@date", "年龄@birthday@age", "性别@sex@sex", "婚姻@marriage@mar", "联系电话@phone","籍贯@province|city", "有无相关工作经验@exp@exp" };
		param.setPageSize(null);
		List<EntryFormDto> dataList = entryformDao.entryList(param, id);
		return PoiExcelUtils.createExcel2FilePath("应聘名单", "应聘名单", FileUtil.BASE_PATH, headers, dataList);
	}

	/**
	 * 获取应聘列表
	 */
	public PageInfo<EntryFormDto> entryList(PageParam param, String id) {
		return new PageInfo<EntryFormDto>(entryformDao.entryList(param, id));
	}
}
