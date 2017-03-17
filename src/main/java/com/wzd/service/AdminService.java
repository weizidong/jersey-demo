package com.wzd.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzd.model.dao.AdminDao;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.service.wechat.user.WxUserService;
import com.wzd.web.dto.PageDto;
import com.wzd.web.param.PageParam;

/**
 * 管理员业务
 * 
 * @author WeiZiDong
 *
 */
@Service
public class AdminService {
	private static final Logger log = LogManager.getLogger(AdminService.class);
	@Autowired
	private AdminDao dao;
	@Autowired
	private WxUserService wxService;

	/**
	 * 登录
	 */
	public void login(Admin admin) {
		// TODO Auto-generated method stub

	}

	/**
	 * 创建
	 */
	public void create(Admin admin) {
		// TODO Auto-generated method stub

	}

	/**
	 * 删除
	 */
	public void delete(Integer id, DeleteType type) {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改
	 */
	public void update(Admin admin) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询详情
	 */
	public Admin findById(Integer id, DeleteType parse) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 条件分页查询
	 */
	public PageDto find(PageParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 同步企业号人员
	 */
	public void sync() {
		// TODO Auto-generated method stub
	}

}
