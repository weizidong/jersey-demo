package com.wzd.service.wechat.qrcode;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzd.client.RestClientUtil;
import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.base.FwAPI;
import com.wzd.web.dto.exception.WebException;

/**
 * 二维码API
 * 
 * @author WeiZiDong
 *
 */
public class QrcodeService {
	// 二维码最大有效时间
	private static final Integer MAX_TIME = 2592000;

	/**
	 * 获取临时二维码
	 */
	public static String getTempQrcocde(String timestamp) {
		String qrcode = getQrcodeUrl(timestamp, false);
		return qrcode;
	}

	/**
	 * 获取永久二维码
	 */
	public static String getFixedQrcodeUrl(String lifesenseId) {
		return getQrcodeUrl(lifesenseId, true);
	}

	/**
	 * 获取带参数二维码
	 */
	private static String getQrcodeUrl(String param, boolean isFixed) {
		if (isFixed) {
			param = generateFixedTicket(param);
		} else {
			param = generateTempTicket(Long.parseLong(param));
		}
		String result = RestClientUtil.postJson(MessageFormat.format(FwAPI.CREATE_QRCODE, FwWxService.getToken()), param, String.class);
		JSONObject nodes = JSONObject.parseObject(result);
		if (nodes.containsKey("errcode")) {
			throw new WebException(nodes.getInteger("errcode"), nodes.getString("errmsg"));
		}
		return nodes.getString("url");
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

	/**
	 * 生成临时二维码参数(时间30天)
	 */
	private static String generateTempTicket(Long sceneid) {
		return generateTempTicket(MAX_TIME, sceneid);
	}

}
