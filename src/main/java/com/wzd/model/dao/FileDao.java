package com.wzd.model.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Files;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.FilesMapper;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;

/**
 * 文件数据库操作
 * 
 * @author WeiZiDong
 *
 */
@Component
public class FileDao {
	@Autowired
	private FilesMapper mapper;

	/**
	 * 创建
	 */
	public Files create(Files file) {
		file.setCreated(new Date());
		file.setDeleted(DeleteType.未删除.getValue());
		file.setUpdated(new Date());
		file.setStatus(StateType.启用.getValue());
		mapper.insert(file);
		return file;
	}

	/**
	 * 物理删除
	 */
	public void delete(String id) {
		Files f = new Files();
		f.setId(id);
		mapper.delete(f);
	}

	/**
	 * 根据ID查找文件
	 */
	public Files getById(String id) {
		Files f = new Files();
		f.setId(id);
		return mapper.selectOne(f);
	}

	/**
	 * 更新不为空的字段
	 */
	public void update(Files f) {
		f.setUpdated(new Date());
		mapper.updateByPrimaryKeySelective(f);
	}

	/**
	 * 获取文件列表
	 */
	public PageInfo<Files> list(PageParam param) {
		Example e = new Example(Files.class);
		// 设置条件
		PageParam.setCondition(e, param, Files.class);
		// 开始分页
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Files>(mapper.selectByExample(e));
	}
}
