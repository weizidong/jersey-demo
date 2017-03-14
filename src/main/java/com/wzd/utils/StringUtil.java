package com.wzd.utils;

/**
 * String 工具类
 * 
 * @author WeiZiDong
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		return str == null || str.length() <= 0;
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return true;
		}
		if (str1 != null && str2 != null && str1.toLowerCase().equals(str2.toLowerCase())) {
			return true;
		}
		return false;
	}

}
