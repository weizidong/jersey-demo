package com.wzd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.WxActivityDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.entity.Wxactivity;
import com.wzd.model.enums.AuditType;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.web.dto.PageDto;
import com.wzd.web.param.PageParam;

/**
 * 微信活动业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class WxActivityService {
	@Autowired
	private WxActivityDao dao;

	/**
	 * 创建活动
	 */
	public void create(Wxactivity activity, Admin admin) {
		dao.create(activity);
	}

	/**
	 * 删除活动
	 */
	public void delete(Integer id, DeleteType type, Admin admin) {
		// TODO Auto-generated method stub
	}

	/**
	 * 修改活动
	 */
	public void update(Wxactivity activity, Admin admin) {
		// TODO Auto-generated method stub
	}

	/**
	 * 根据id查询
	 */
	public Wxactivity findById(Integer id, DeleteType type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 条件查询所有
	 */
	public PageDto find(PageParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 审核
	 */
	public void auditing(AuditType parse, Admin user) {
		// TODO Auto-generated method stub

	}

	/**
	 * 暂停/启用
	 */
	public void changeState(StateType parse, Admin user) {
		// TODO Auto-generated method stub

	}
}
