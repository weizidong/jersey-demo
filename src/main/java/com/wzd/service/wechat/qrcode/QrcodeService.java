package com.lifesense.healthcenter.service.wechat.qrcode;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lifesense.framework.util.jackson.JacksonUtils;
import com.lifesense.healthcenter.service.wechat.WeixinAPI;
import com.lifesense.healthcenter.service.wechat.token.TokenService;
import com.lifesense.healthcenter.utils.HttpClientManager;
import com.lifesense.healthcenter.utils.StringUtil;

/**
 * 
 * @author lifesense401
 *
 */
public class QrcodeService {

	/**
	 * 获取临时二维码
	 * 
	 * @param timestamp
	 * 
	 * @return
	 */
	public static String getTempQrcocde(String timestamp) {
		String qrcode = getQrcodeUrl(timestamp, false);
		return qrcode;
	}

	/**
	 * 获取永久二维码
	 * 
	 * @param weixinServiceNo
	 * @param lifesenseId
	 * @return
	 */
	public static String getFixedQrcodeUrl(String lifesenseId) {
		return getQrcodeUrl(lifesenseId, true);
	}

	/**
	 * 获取带参数二维码的url(永久)
	 *
	 * @param weixinServiceNo
	 * @return
	 */
	private static String getQrcodeUrl(String lifesenseId, boolean isFixed) {
		String accessToken = TokenService.getAccessToken();

		// 永久二维码url
		String url = MessageFormat.format(WeixinAPI.WX_GET_QRCODE_TICKET_URL, accessToken);

		// 生成永久二维码的ticket的post参数的json
		String json = "";
		if(isFixed){
			json = generateFixedTicket(lifesenseId);
		}else{
			json = generateTempTicket(lifesenseId);
		}

		System.out.println("/n qrcode json="+ json);
		
		if (json == null) {
			return null;
		}

		// 获取带参数二维码的ticket
		String result = HttpClientManager.postJson(url, json);

		JSONObject nodes = JSONObject.fromObject(result);

		if(nodes.containsKey("errcode")&&nodes.getInt("errcode")==40001){
			TokenService.updateAccessToken();
			getQrcodeUrl(lifesenseId, isFixed);
		}
		
		// 获取jsonnode
		if (!nodes.containsKey("url")) {
			return null;
		}

		String qrcodeUrl = nodes.getString("url");
		return qrcodeUrl;
	}

	/**
	 * 生成永久二维码ticket
	 * 
	 * @param lifesenseId
	 * @return
	 */
	private static String generateFixedTicket(String lifesenseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("action_name", "QR_LIMIT_STR_SCENE");
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> sceneId = new HashMap<String, Object>();
		sceneId.put("scene_str", lifesenseId);
		actionInfo.put("scene", sceneId);
		params.put("action_info", actionInfo);
		try {
			return JacksonUtils.toJson(params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 生成临时二维码ticket
	 * 
	 * @param lifesenseId
	 * @return
	 */
	private static String generateTempTicket(String lifesenseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 一个小时
		params.put("expire_seconds", 3600);
		params.put("action_name", "QR_SCENE");
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> sceneId = new HashMap<String, Object>();
		sceneId.put("scene_id", StringUtil.toLong(lifesenseId));
		actionInfo.put("scene", sceneId);
		params.put("action_info", actionInfo);
		try {
			return JacksonUtils.toJson(params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
