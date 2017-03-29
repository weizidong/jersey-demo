package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.entity.Setting;
import com.wzd.model.mapper.SettingMapper;
import com.wzd.utils.EhcacheUtil;

/**
 * 系统设置数据库操作类
 * 
 * @author WeiZiDong
 *
 */
@Component
public class SettingDao {
	@Autowired
	private SettingMapper mapper;

	/**
	 * 创建系统设置
	 */
	public void create(Setting s) {
		EhcacheUtil.getInstance().putCache("Setting" + s.getId(), s);
		mapper.insertSelective(s);
	}

	/**
	 * 更新系统设置
	 */
	public void update(Setting s) {
		EhcacheUtil.getInstance().putCache("Setting" + s.getId(), s);
		mapper.updateByPrimaryKeySelective(s);
	}

	/**
	 * 获取系统设置
	 */
	public Setting getById(String id) {
		Setting s = (Setting) EhcacheUtil.getInstance().getCache("Setting" + id);
		if (s == null) {
			s = new Setting();
			s.setId(id);
			s = mapper.selectOne(s);
			EhcacheUtil.getInstance().putCache("Setting" + id, s);
		}
		return s;
	}

}
