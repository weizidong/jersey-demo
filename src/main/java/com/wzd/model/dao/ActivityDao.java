package com.wzd.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.wzd.model.entity.Activity;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.ActivityMapper;
import com.wzd.service.wechat.qrcode.QrcodeService;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 线下活动数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class ActivityDao {
	@Autowired
	private ActivityMapper mapper;

	/**
	 * 创建
	 */
	public void create(Activity a) {
		a.setId(UUIDUtil.get());
		a.setCreated(new Date());
		a.setDeleted(DeleteType.未删除);
		a.setStatus(StateType.未开始);
		a.setCurrent(0);
		a.setTicket(QrcodeService.getFixedQrcodeUrl(a.getId()));
		mapper.insertSelective(a);
	}

	/**
	 * 修改活动
	 */
	public void update(Activity a) {
		mapper.updateByPrimaryKeySelective(a);
	}

	/**
	 * 删除活动
	 */
	public void delete(String id, DeleteType del) {
		Activity a = new Activity();
		a.setId(id);
		if (del == DeleteType.永久删除) {
			mapper.delete(a);
		} else {
			a.setDeleted(del);
			update(a);
		}
	}

	/**
	 * 根据ID查询
	 */
	public Activity findById(String id) {
		Activity a = new Activity();
		a.setId(id);
		return mapper.selectOne(a);
	}

	/**
	 * 条件查询活动列表
	 */
	public List<Activity> find(PageParam param) {
		Example e = new Example(Activity.class);
		PageParam.setCondition(e, "created", param, Activity.class);
		e.setOrderByClause(e.getOrderByClause() + ",created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return mapper.selectByExample(e);
	}

	/**
	 * 根据adminId删除
	 */
	public void deleteByAdmin(String adminId, DeleteType del) {
		Activity a = new Activity();
		a.setAdminId(adminId);
		if (del == DeleteType.永久删除) {
			mapper.delete(a);
		} else {
			a.setDeleted(del);
			update(a);
		}
	}

	/**
	 * 根据ID获取
	 */
	public Activity getById(String id) {
		Activity a = new Activity();
		a.setId(id);
		return mapper.selectOne(a);
	}
}
