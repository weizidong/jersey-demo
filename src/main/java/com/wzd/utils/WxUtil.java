package com.wzd.utils;

import com.wzd.client.RestClientUtil;

/**
 * 微信开发工具类
 * 
 * @author WeiZiDong
 *
 */
public class WxUtil {

	public static <T> T get(String uri, Class<T> clazz) {
		return RestClientUtil.get(RestClientUtil.buildWebTarget(uri), clazz);
	}

	public static <T> T post(String uri, Class<T> clazz) {
		return RestClientUtil.get(RestClientUtil.buildWebTarget(uri), clazz);
	}
}
