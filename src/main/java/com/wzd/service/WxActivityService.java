package com.wzd.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.entity.Activity;
import com.wzd.model.enums.ActivityType;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.ActivityMapper;

/**
 * 微信活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WxActivityService {
	private static final Logger log = LogManager.getLogger(WxActivityService.class);
	@Autowired
	private ActivityMapper mapper;

	/**
	 * 创建活动
	 * 
	 * @param activity
	 */
	public void create(Activity activity) {
		log.debug("创建活动。。。");
		activity.setId(null);
		activity.setUpdated(new Date());
		activity.setCreated(new Date());
		activity.setDeleted(DeleteType.未删除.getValue());
		activity.setStatus(AuditType.未审核.getValue());
		activity.setType(ActivityType.普通活动.getValue());
		mapper.insert(activity);
		log.debug("创建成功：", activity);
	}

	/**
	 * 删除活动
	 * 
	 * @param id
	 * @param type
	 */
	public void delete(Integer id, DeleteType type) {
		log.debug("删除活动。。。");
		Activity activity = mapper.selectByPrimaryKey(id);
		activity.setDeleted(type.getValue());
		activity.setUpdated(new Date());
		mapper.updateByPrimaryKey(activity);
		log.debug("删除成功：", activity);
	}

	/**
	 * 修改活动
	 * 
	 * @param activity
	 */
	public void update(Activity activity) {
		log.debug("修改活动。。。");
		if (activity.getId() == null) {
			// TODO 定义异常
			return;
		}
		activity.setUpdated(new Date());
		mapper.updateByPrimaryKeySelective(activity);
		log.debug("修改成功：", activity);
	}

	/**
	 * 条件查询所有
	 * 
	 * @return
	 */
	public List<Activity> find(Activity activity) {
		activity.setId(null);
		return mapper.select(activity);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Activity findById(Integer id, DeleteType type) {
		Activity activity = new Activity();
		activity.setId(id);
		activity.setDeleted(type.getValue());
		return mapper.selectOne(activity);
	}
}
