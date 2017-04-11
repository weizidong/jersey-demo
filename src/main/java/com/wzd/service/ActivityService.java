package com.wzd.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wzd.model.dao.ActivityDao;
import com.wzd.model.dao.AdminDao;
import com.wzd.model.dao.EntryformDao;
import com.wzd.model.dao.FileDao;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.entity.Activity;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Entryform;
import com.wzd.model.entity.Files;
import com.wzd.model.entity.History;
import com.wzd.model.entity.User;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.FileType;
import com.wzd.model.enums.HistoryType;
import com.wzd.model.enums.SignType;
import com.wzd.model.enums.StateType;
import com.wzd.model.enums.ViewPage;
import com.wzd.service.wechat.msg.WxMsgSender;
import com.wzd.service.wechat.msg.dto.ARTICLE;
import com.wzd.service.wechat.msg.dto.NEWS;
import com.wzd.utils.FileUtil;
import com.wzd.utils.PoiExcelUtils;
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
	private AdminDao adminDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryformDao entryformDao;
	@Autowired
	private HistoryDao historyDao;
	// 定时器记录
	private static final Map<String, ScheduledFuture<?>> timmer = new HashMap<>();

	/**
	 * 创建活动
	 */
	public Activity create(Activity a, Admin admin) {
		a.setAdminId(admin.getId());
		activityDao.create(a);
		if (a.getFiles() != null && a.getFiles().size() > 0) {
			fileDao.update(new Files(a.getId(), FileType.活动配图), a.getFiles().stream().map(Files::getId).collect(Collectors.toList()));
		}
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
		a.setAdmin(adminDao.getById(a.getAdminId()));
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
		if (a.getScore() > user.getScore()) {
			throw new WebException(ResponseCode.积分不够);
		}
		if (a.getCurrent() >= a.getTotal()) {
			throw new WebException(ResponseCode.已报满);
		}
		Entryform ef = new Entryform(user.getOpenid(), id, ActivityType.工会活动);
		if (entryformDao.isEntry(ef)) {
			throw new WebException(ResponseCode.已报名);
		}
		entryformDao.entry(ef);
		historyDao.create(new History(user.getId(), a.getName() + "报名", null, -a.getScore(), null, HistoryType.工会活动, a.getId()));
		a.setCurrent(a.getCurrent() + 1);
		activityDao.update(a);
		user.setScore(user.getScore() - a.getScore());
		userDao.update(user);
	}

	/**
	 * 取消报名
	 */
	public void cacleEntry(String id, User user) {
		Activity a = activityDao.getById(id);
		entryformDao.delete(new Entryform(user.getOpenid(), id, ActivityType.工会活动), DeleteType.永久删除);
		historyDao.create(new History(user.getId(), a.getName() + "取消报名", null, a.getScore(), null, HistoryType.工会活动, a.getId()));
		a.setCurrent(a.getCurrent() - 1);
		activityDao.update(a);
		user.setScore(user.getScore() + a.getScore());
		userDao.update(user);
	}

	/**
	 * 签到
	 */
	public ResponseCode sign(String id, User user) {
		Entryform ef = entryformDao.get(new Entryform(user.getOpenid(), id, ActivityType.工会活动));
		if (ef == null) {
			return ResponseCode.未报名;
		}
		if (ef.getStatus() == SignType.已签到.getValue()) {
			return ResponseCode.已经签到;
		}
		entryformDao.sign(ef);
		return ResponseCode.成功;
	}

	/**
	 * 获取报名列表
	 */
	public PageInfo<EntryFormDto> entryList(PageParam param, String id) {
		return new PageInfo<EntryFormDto>(entryformDao.entryList(param, id));
	}

	/**
	 * 批量导出报名列表
	 */
	public String exportEntry(PageParam param, String id) {
		String[] headers = new String[] { "昵称@nickname", "姓名@name", "身份证@idCard", "生日@birthday@bir", "性别@sex@sex", "婚姻@marriage@mar", "联系电话@phone", "所属公司@depName", "职位名称@position",
				"报名时间@created", "用户类型@audit@user" };
		param.setPageSize(null);
		List<EntryFormDto> dataList = entryformDao.entryList(param, id);
		return PoiExcelUtils.createExcel2FilePath("活动报名表", "活动报名表", FileUtil.BASE_PATH, headers, dataList);
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
		List<Entryform> list = entryformDao.list(new Entryform(id, ActivityType.工会活动, DeleteType.未删除));
		String title = "活动\"" + a.getName() + "\"已经" + (a.getStatus() == StateType.暂停.getValue() ? "开启！" : "暂停！");
		WxMsgSender.batchSendToFw(list.stream().map(Entryform::getOpenId).collect(Collectors.toList()),
				new NEWS(Arrays.asList(new ARTICLE(title, title, ViewPage.genarate(ViewPage.activity, id), a.getPicUrl()))));
	}

}
