package com.wzd.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.mapper.RecruitMapper;

/**
 * 招聘信息数据库操作类
 * 
 * @author WeiZiDong
 *
 */
@Component
public class RecruitDao {
	@Autowired
	private RecruitMapper mapper;
}
