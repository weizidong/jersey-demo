//package com.wzd.service.wechat.device;
//
//import java.text.MessageFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import com.lifesense.framework.util.log.LoggerAdapter;
//import com.lifesense.framework.util.log.LoggerAdapterFacory;
//import com.lifesense.healthcenter.service.wechat.WeixinAPI;
//import com.lifesense.healthcenter.service.wechat.dto.device.CompelBindRequest;
//import com.lifesense.healthcenter.service.wechat.token.TokenService;
//import com.lifesense.healthcenter.utils.HttpClientManager;
//import com.lifesense.healthcenter.utils.JsonUtil;
//import com.lifesense.healthcenter.utils.StringUtil;
//
///**
// * 微信设备服务，例如：强制解绑/绑定用户和设备
// * 
// * @author juzhi
// * 
// */
//public class WxDeviceService {
//	
//	 private static LoggerAdapter log = LoggerAdapterFacory.getLogger(WxDeviceService.class);
//
//	/**
//	 * 向微信强制解绑用户和设备，TODO:待优化
//	 * 
//	 * @param openId
//	 * @param deviceId
//	 */
//	public static boolean compelUnbindDevice(String openId, String deviceId) {
//		
//		// 微信绑定的设备id都是用大写
//		deviceId = deviceId.toUpperCase();
//		
//		// 封装强制解绑用户和设备请求对象
//		CompelBindRequest compel = new CompelBindRequest();
//		compel.setDevice_id(deviceId.toUpperCase());
//		compel.setOpenid(openId);
//
//		String compelJson = JsonUtil.toJson(compel);
//
//		// 获取accessToken
//		String accessToken = TokenService.getAccessToken();
//		
//		String url = MessageFormat.format(WeixinAPI.COMPEL_UNBIND_DEVICE_URL, accessToken);
//
//		// 调用微信强制解绑用户和设备
//		String body = HttpClientManager.postJson(url, compelJson);
//		
//		log.debug("\ncompelUnbindDevice body=" + body);
//
//		// 发送结果标识，true-成功，false-失败
//		boolean flag = false;
//
//		if (StringUtil.isBlank(body)) {
//			return flag;
//		}
//
//		// =========更新模板客服消息发送状态、发送结果 start
//
//		JSONObject json = JSONObject.fromObject(body);
//		
//		// errcode判断结果代码
//		int resultCode = TokenService.checkErrcode(json);
//
//		if (0 == resultCode) { // 已发送成功
//			flag = true;
//		} else if (1 == resultCode) { // accessToken超时或失效后重新执行1次
//			flag = runAgain(url, compelJson);
//		} else { // 未知错误
//
//		}
//
//		return flag;
//
//	}
//
//	/**
//	 * 重新执行1次，TODO:待优化
//	 * @param url
//	 * @param jsonMessage
//	 * @return boolean 执行结果标识，true-成功，false-失败
//	 */
//	private static boolean runAgain(String url, String jsonMessage) {
//		
//		// 更新token
//		String accessToken = TokenService.updateAccessToken();
//
//		String body = HttpClientManager.postJson(
//				MessageFormat.format(url, accessToken), jsonMessage);
//
//		if (StringUtil.isBlank(body)) {
//			return false;
//		}
//
//		JSONObject json = JSONObject.fromObject(body);
//		
//		// errcode判断结果代码
//		int resultCode = TokenService.checkErrcode(json);
//
//		if (0 == resultCode) { // 执行成功
//			return true;
//		}
//
//		return false;
//
//	}
//	
//	
//	/**
//	 * 向微信强制绑定用户和设备，TODO:待优化
//	 * 
//	 * @param openId
//	 * @param deviceId
//	 */
//	public static boolean compelBindDevice(String openId, String deviceId) {
//		
//		// 微信绑定的设备id都是用大写
//		deviceId = deviceId.toUpperCase();
//		
//		// 封装强制解绑用户和设备请求对象
//		CompelBindRequest compel = new CompelBindRequest();
//		compel.setDevice_id(deviceId.toUpperCase());
//		compel.setOpenid(openId);
//
//		String compelJson = JsonUtil.toJson(compel);
//
//		// 获取accessToken
//		String accessToken = TokenService.getAccessToken();
//		
//		String url = MessageFormat.format(WeixinAPI.COMPEL_BIND_DEVICE_URL, accessToken);
//
//		// 调用微信强制解绑用户和设备
//		String body = HttpClientManager.postJson(url, compelJson);
//		
//		log.debug("\n compelBindDevice body=" + body);
//
//		// 发送结果标识，true-成功，false-失败
//		boolean flag = false;
//
//		if (StringUtil.isBlank(body)) {
//			return flag;
//		}
//
//		// =========更新模板客服消息发送状态、发送结果 start
//
//		JSONObject json = JSONObject.fromObject(body);
//		
//		// errcode判断结果代码
//		int resultCode = TokenService.checkErrcode(json);
//
//		if (0 == resultCode) { // 已发送成功
//			flag = true;
//		} else if (1 == resultCode) { // accessToken超时或失效后重新执行1次
//			flag = runAgain(url, compelJson);
//		} else { // 未知错误
//
//		}
//
//		return flag;
//
//	}
//	
//	
//	
//	
//	/**
//	 * 从获取获取openId下绑定的设备列表
//	 * 
//	 * @param openId
//	 */
//	public static List<String> getBindedDevices(String openId, int excuteTime) {
//		
//		// 获取accessToken
//		String accessToken = TokenService.getAccessToken();
//		
//		String url = MessageFormat.format(WeixinAPI.GET_BINDED_DEVCIE, accessToken, openId);
//
//		// 调用微信强制解绑用户和设备
//		String body = HttpClientManager.get(url);
//		
//		log.debug("\n getBindedDevices body=" + body);
//
//		if (StringUtil.isBlank(body)) {
//			return null;
//		}
//
//		JSONObject json = JSONObject.fromObject(body);
//		
//		// errcode判断结果代码
//		int resultCode = TokenService.checkErrcode(json);
//		
//		List<String> devcieList = new ArrayList<String>();
//		
//		// accessToken超时或失效后重新执行1次
//		if (1 == resultCode&&excuteTime<2) { 
//			 devcieList =  getBindedDevices(openId,1);
//			 return devcieList;
//		}
//		
//		devcieList = getDeviceIdList(json);
//		
//		return devcieList;
//
//	}
//
//	public static void main(String[] args) {
//		String json = "{\"resp_msg\":{\"ret_code\":0,\"error_info\":\"ok\"},\"openid\":\"onPZPuKOtDNyKySoQChRtFnDJ7p0\",\"device_list\":[{\"device_type\":\"gh_254b1b25716f\",\"device_id\":\"4C080838C590\"},{\"device_type\":\"gh_254b1b25716f\",\"device_id\":\"4C080838C590\"}]}";
//		JSONObject jsonObj = JSONObject.fromObject(json);
//		List<String> aa = getDeviceIdList(jsonObj);
//		for(String a : aa){
//			System.out.println(a);
//		}
//	}
//	
//	/**
//	 * 
//	 * @param json
//	 * @return
//	 */
//	private static List<String> getDeviceIdList(JSONObject json) {
//		if (!json.containsKey("resp_msg")) {
//			return null;
//		}
//		
//		JSONObject jsonObj = json.getJSONObject("resp_msg");
//		
//		if(jsonObj==null){
//			return null;
//		}
//		
//		Integer code = jsonObj.getInt("ret_code");
//		
//		if(code==null||code!=0){
//			return null;
//		}
//		
//		JSONArray array = json.getJSONArray("device_list");
//		
//		if(array==null){
//			return null;
//		}
//		
//		List<String> devcieIds = new ArrayList<String>();
//		
//		for (int i = 0; i < array.size(); i++) {
//			JSONObject item = (JSONObject) array.get(i);
//			String deviceId = item.getString("device_id");
//			devcieIds.add(deviceId);
//		}
//	   
//		return devcieIds;
//	}
//
//}
