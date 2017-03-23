package com.wzd.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 加载系统配置
 * 
 * @author WeiZiDong
 *
 */
public class Configs {
	private static final Logger log = LogManager.getLogger(Configs.class);
	// 配置路径
	private static String PROPERTIES = "configs/jdbc.properties";
	// 所有配置
	private static Map<String, String> propMap = null;
	/**
	 * 版本号
	 */
	public static String version = "1.0.0";
	/**
	 * 本机域名
	 */
	public static String hostname = "127.0.0.1";
	/**
	 * 企业号唯一凭证
	 */
	public static String sCorpID = "";
	/**
	 * 企业号唯一凭证密钥
	 */
	public static String sSecret = "";
	/**
	 * 企业号EncodingAESKey用于消息体的加密，是AES密钥的Base64编码。
	 */
	public static String sEncodingAESKey = "";
	/**
	 * 企业号Token可由企业任意填写，用于生成签名。
	 */
	public static String sToken = "";
	/**
	 * 服务号原始ID
	 */
	public static String bId = "";
	/**
	 * 服务号唯一凭证
	 */
	public static String bAppid = "";
	/**
	 * 服务号唯一凭证密钥
	 */
	public static String bSecret = "";
	/**
	 * 服务号EncodingAESKey用于消息体的加密，是AES密钥的Base64编码。
	 */
	public static String bEncodingAESKey = "";
	/**
	 * 服务号Token可由企业任意填写，用于生成签名。
	 */
	public static String bToken = "";

	static {
		try {
			propMap = PropertiesUtil.readPropertiesForMap(PROPERTIES);
			version = propMap.get("version");
			hostname = propMap.get("hostname");
			sCorpID = propMap.get("sCorpID");
			sSecret = propMap.get("sSecret");
			sEncodingAESKey = propMap.get("sEncodingAESKey");
			sToken = propMap.get("sToken");
			bId = propMap.get("bId");
			bAppid = propMap.get("bAppid");
			bSecret = propMap.get("bSecret");
			bEncodingAESKey = propMap.get("bEncodingAESKey");
			bToken = propMap.get("bToken");
		} catch (IOException e) {
			throw new RuntimeException("加载服务器配置发生异常。", e);
		} finally {
			log.info("启动成功！版本：" + version);
		}
	}

	/**
	 * 获取相关配置
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return propMap.get(key);
	}
}
