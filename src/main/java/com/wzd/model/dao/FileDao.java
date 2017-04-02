package com.wzd.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.Files;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.StateType;
import com.wzd.model.mapper.FilesMapper;
import com.wzd.utils.UUIDUtil;
import com.wzd.web.param.PageParam;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
		file.setId(UUIDUtil.get());
		file.setCreated(new Date());
		file.setDeleted(DeleteType.未删除);
		file.setStatus(StateType.进行中);
		mapper.insertSelective(file);
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
	 * 获取文件列表
	 */
	public PageInfo<Files> list(PageParam param, DeleteType del) {
		Example e = new Example(Files.class);
		Criteria c = PageParam.setCondition(e, "created", param, Files.class);
		if (del != DeleteType.全部) {
			c.andEqualTo("deleted", del.getValue());
		}
		e.setOrderByClause(e.getOrderByClause() + ",created DESC");
		PageHelper.startPage(param.getPage(), param.getPageSize());
		return new PageInfo<Files>(mapper.selectByExample(e));
	}

	/**
	 * 修改
	 */
	public void update(Files f) {
		mapper.updateByPrimaryKeySelective(f);
	}

	/**
	 * 批量更新
	 */
	public void update(Files f, List<String> ids) {
		Example e = new Example(Files.class);
		e.createCriteria().andIn("id", ids);
		mapper.updateByExampleSelective(f, e);
	}

	/**
	 * 根据外键查询
	 */
	public List<Files> getByFk(Files files) {
		return mapper.select(files);
	}

	/**
	 * 根据外键删除
	 */
	public void deleteByFk(String fk, DeleteType del) {
		Files f = new Files();
		f.setFk(fk);
		if (del == DeleteType.永久删除) {
			mapper.delete(f);
		} else {
			f.setDeleted(del);
			update(f);
		}
	}
}
