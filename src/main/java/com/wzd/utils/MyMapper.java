package com.wzd.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 * @author wzd
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
