package com.wzd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.ActivityDao;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.FileDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.Files;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.FileType;
import com.wzd.model.enums.StateType;
import com.wzd.utils.FileUtil;
import com.wzd.utils.ThreadPoolUtils;
import com.wzd.web.dto.entryForm.EntryFormDto;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.PageParam;

/**
 * 活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class ActivityService {
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private EntryformDao entryformDao;
	// 定时器记录
	private static final Map<String, ScheduledFuture<?>> timmer = new HashMap<>();

	/**
	 * 创建活动
	 */
	public Activity create(Activity a, Admin admin) {
		if (a.getFiles() != null && a.getFiles().size() > 0) {
			fileDao.update(new Files(a.getId(), FileType.活动配图), a.getFiles().stream().map(Files::getId).collect(Collectors.toList()));
		}
		a.setAdminId(admin.getId());
		activityDao.create(a);
		setTimmer(a);
		return a;
	}

	/**
	 * 设置定时任务
	 */
	private void setTimmer(Activity a) {
		String id = a.getId();
		// 设置开始
		ScheduledFuture<?> start = timmer.get("START" + id);
		// 停止原来的
		if (start != null) {
			start.cancel(false);
		}
		start = ThreadPoolUtils.schedule(() -> {
			Activity act = activityDao.getById(id);
			if (act.getStatus() != StateType.暂停.getValue()) {
				act.setStatus(StateType.进行中);
				activityDao.update(act);
			}
		}, a.getEntryStart());
		timmer.put("START" + id, start);
		// 设置结束
		ScheduledFuture<?> end = timmer.get("END" + id);
		// 停止原来的
		if (end != null) {
			end.cancel(false);
		}
		end = ThreadPoolUtils.schedule(() -> {
			Activity act = activityDao.getById(id);
			if (act.getStatus() != StateType.暂停.getValue()) {
				act.setStatus(StateType.已结束);
				activityDao.update(act);
			}
		}, a.getEntryEnd());
		timmer.put("END" + id, end);
	}

	/**
	 * 清理定时任务
	 */
	private void clearTimmer(String id) {
		// 清理开始
		ScheduledFuture<?> start = timmer.get("START" + id);
		if (start != null) {
			start.cancel(false);
		}
		timmer.remove("START" + id);
		// 清理结束
		ScheduledFuture<?> end = timmer.get("END" + id);
		if (end != null) {
			end.cancel(false);
		}
		timmer.remove("END" + id, end);
	}

	/**
	 * 修改活动
	 */
	public void update(Activity a) {
		activityDao.update(a);
		if (a.getStatus() == StateType.暂停.getValue()) {
			clearTimmer(a.getId());
		} else {
			setTimmer(a);
		}
	}

	/**
	 * 删除活动
	 */
	public void delete(String id, DeleteType del) {
		activityDao.delete(id, del);
		if (del == DeleteType.永久删除) {
			fileDao.getByFk(new Files(id, FileType.活动配图)).forEach(f -> FileUtil.delete(f.getUrl()));
		}
		fileDao.deleteByFk(id, del);
		clearTimmer(id);
	}

	/**
	 * 根据id查询
	 */
	public Activity findById(String id) {
		Activity a = activityDao.findById(id);
		List<Files> files = fileDao.getByFk(new Files(a.getId(), FileType.活动配图));
		a.setFiles(files);
		return a;
	}

	/**
	 * 条件查询所有
	 */
	public PageInfo<Activity> find(PageParam param) {
		return new PageInfo<Activity>(activityDao.find(param));
	}

	/**
	 * 根据adminId删除
	 */
	public void deleteByAdmin(String adminId, DeleteType del) {
		activityDao.deleteByAdmin(adminId, del);
	}

	/**
	 * 报名
	 */
	public void entry(String id, User user) {
		Activity a = activityDao.getById(id);
		if (a.getStatus() == StateType.暂停.getValue()) {
			throw new WebException(ResponseCode.已暂停);
		}
		if (a.getStatus() == StateType.已结束.getValue()) {
			throw new WebException(ResponseCode.已结束);
		}
		if (a.getStatus() == StateType.未开始.getValue()) {
			throw new WebException(ResponseCode.未开始);
		}
		if (a.getCurrent() >= a.getTotal()) {
			throw new WebException(ResponseCode.已报满);
		}
		Entryform ef = new Entryform(user.getId(), id, ActivityType.工会活动);
		if (entryformDao.isEntry(ef)) {
			throw new WebException(ResponseCode.已报名);
		}
		entryformDao.entry(ef);
		a.setCurrent(a.getCurrent() + 1);
		activityDao.update(a);
	}

	/**
	 * 签到
	 */
	public void sign(String id, User user) {
		Entryform ef = new Entryform(user.getId(), id, ActivityType.工会活动);
		if (!entryformDao.isEntry(ef)) {
			throw new WebException(ResponseCode.未报名);
		}
		entryformDao.sign(ef);
	}

	/**
	 * 获取报名列表
	 */
	public PageInfo<EntryFormDto> entryList(PageParam param, String id) {
		return entryformDao.entryList(param, id);
	}

	/**
	 * 开启关闭活动
	 */
	public void pause(String id) {
		Activity a = activityDao.getById(id);
		if (a == null) {
			throw new WebException(ResponseCode.资源不存在, "活动" + id + "不存在");
		}
		if (a.getStatus() != StateType.暂停.getValue()) {
			a.setStatus(StateType.暂停);
		} else {
			a.setStatus(StateType.未开始);
		}
		update(a);
	}
}
