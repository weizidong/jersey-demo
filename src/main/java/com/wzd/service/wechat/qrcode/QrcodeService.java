package com.wzd.service.wechat.qrcode;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzd.client.RestClientUtil;
import com.wzd.web.dto.exception.WebException;

/**
 * 二维码API
 * 
 * @author WeiZiDong
 *
 */
public class QrcodeService {
	// 二维码最大有效时间
	public static final Integer MAX_TIME = 2592000;

	/**
	 * 获取临时二维码
	 */
	public static String getTempQrcocde(String path, String token, Long sceneid, Integer expireSeconds) {
		return getQrcodeUrl(path, token, generateTempTicket(expireSeconds, sceneid));
	}

	/**
	 * 获取临时二维码(最长时间30天)
	 */
	public static String getTempQrcocde(String path, String token, Long sceneid) {
		return getQrcodeUrl(path, token, generateTempTicket(MAX_TIME, sceneid));
	}

	/**
	 * 获取永久二维码
	 */
	public static String getFixedQrcodeUrl(String path, String token, String param) {
		return getQrcodeUrl(path, token, generateFixedTicket(param));
	}

	/**
	 * 获取带参数二维码
	 */
	private static String getQrcodeUrl(String path, String token, String param) {
		String result = RestClientUtil.postJson(MessageFormat.format(path, token), param, String.class);
		JSONObject nodes = JSONObject.parseObject(result);
		if (nodes.containsKey("errcode")) {
			throw new WebException(nodes.getInteger("errcode"), nodes.getString("errmsg"));
		}
		return nodes.getString("ticket");
	}

	/**
	 * 生成永久二维码参数
	 */
	private static String generateFixedTicket(String param) {
		Map<String, Object> sceneId = new HashMap<String, Object>();
		sceneId.put("scene_str", param);
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		actionInfo.put("scene", sceneId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("action_name", "QR_LIMIT_STR_SCENE");
		params.put("action_info", actionInfo);
		return JSON.toJSONString(params);
	}

	/**
	 * 生成临时二维码参数
	 */
	private static String generateTempTicket(Integer expireSeconds, Long sceneid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("expire_seconds", expireSeconds);
		params.put("action_name", "QR_SCENE");
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> sceneId = new HashMap<String, Object>();
		sceneId.put("scene_id", sceneid);
		actionInfo.put("scene", sceneId);
		params.put("action_info", actionInfo);
		return JSON.toJSONString(params);
	}

}
