package com.wzd.model.dao;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Admin;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.mapper.AdminMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.IdListParam;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 管理员数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class AdminDao {
	@Autowired
	private AdminMapper mapper;

	/**
	 * 创建
	 */
	public Admin create(Admin admin) {
		if (StringUtils.isBlank(admin.getId())) {
			admin.setId(UUIDUtil.get());
		}
		admin.setAudit(0);
		admin.setCreated(new Date());
		admin.setDeleted(DeleteType.未删除);
		admin.setUpdated(new Date());
		mapper.insertSelective(admin);
		return admin;
	}

	/**
	 * 根据UserId获取用户信息
	 */
	public Admin getByUserId(String userId) {
		Admin admin = new Admin();
		admin.setUserid(userId);
		return mapper.selectOne(admin);
	}

	/**
	 * 修改(更新不为null的值)
	 */
	public void update(Admin admin) {
		mapper.updateByPrimaryKeySelective(admin);
	}

	/**
	 * 修改(更新全部字段)
	 */
	public void updateAll(Admin admin) {
		mapper.updateByPrimaryKey(admin);
	}

	/**
	 * 修改或创建
	 */
	public void save(Admin admin) {
		if (getByUserId(admin.getUserid()) == null) {
			create(admin);
		} else {
			update(admin);
		}
	}

	/**
	 * 根据电话号码获取用户信息
	 */
	public Admin login(Admin a, DeleteType del) {
		Example e = new Example(Admin.class);
		e.or().andEqualTo("userid", a.getUserid());
		e.or().andEqualTo("mobile", a.getUserid());
		e.or().andEqualTo("email", a.getUserid());
		e.createCriteria().andEqualTo("deleted", del.getValue());
		return mapper.selectByExample(e).get(0);
	}

	/**
	 * 刪除
	 */
	public void delete(String userid, DeleteType type) {
		Admin admin = new Admin();
		admin.setUserid(userid);
		if (type == DeleteType.永久删除) {
			mapper.delete(admin);
		} else {
			admin.setDeleted(type);
			update(admin);
		}
	}

	/**
	 * 根据UserId或者email查找
	 */
	public Admin find(String userid, String email) {
		Admin admin = new Admin();
		if (userid != null) {
			admin.setUserid(userid);
		}
		if (email != null) {
			admin.setEmail(email);
		}
		return mapper.selectOne(admin);
	}

	/**
	 * 根据条件分页查询
	 */
	public PageInfo<Admin> find(PageParam param) {
		Example e = new Example(Admin.class);
		PageParam.setCondition(e, "created", param, Admin.class);
		e.setOrderByClause("created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Admin>(mapper.selectByExample(e));
	}

	/**
	 * 批量删除
	 */
	public void delete(IdListParam<String> param) {
		Example e = new Example(Admin.class);
		e.createCriteria().andIn("id", param.getIds());
		if (param.getType() == DeleteType.永久删除.getValue()) {
			mapper.deleteByExample(e);
		} else {
			Admin a = new Admin();
			a.setDeleted(param.getType());
			mapper.updateByExampleSelective(a, e);
		}
	}

	/**
	 * 根据Id获取
	 */
	public Admin getById(String id) {
		Admin a = new Admin();
		a.setId(id);
		return mapper.selectOne(a);
	}
}
