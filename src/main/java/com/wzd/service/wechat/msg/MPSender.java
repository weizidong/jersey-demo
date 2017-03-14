//package com.wzd.service.wechat.msg;
//
//import java.text.MessageFormat;
//import java.util.Date;
//import java.util.List;
//
//import net.sf.json.JSONObject;
//
//import org.apache.log4j.Logger;
//
//import com.lifesense.framework.model.util.MyBatisUtil;
//import com.lifesense.healthcenter.model.dao.basic.WeixinCustomMessageDao;
//import com.lifesense.healthcenter.model.entity.basic.WeixinCustomMessage;
//import com.lifesense.healthcenter.service.family.HealthappOpenIdVerify;
//import com.lifesense.healthcenter.service.wechat.WechartServiceNoService;
//import com.lifesense.healthcenter.service.wechat.WeixinAPI;
//import com.lifesense.healthcenter.service.wechat.constants.MsgType;
//import com.lifesense.healthcenter.service.wechat.dto.push.Article;
//import com.lifesense.healthcenter.service.wechat.dto.push.NewsMessage;
//import com.lifesense.healthcenter.service.wechat.dto.push.TemplateMessage;
//import com.lifesense.healthcenter.service.wechat.dto.push.TextMessage;
//import com.lifesense.healthcenter.service.wechat.dto.push.VoiceMessage;
//import com.lifesense.healthcenter.service.wechat.dto.push.WifiDeviceStatusMessage;
//import com.lifesense.healthcenter.service.wechat.token.TokenService;
//import com.lifesense.healthcenter.utils.DateUtils;
//import com.lifesense.healthcenter.utils.HttpClientManager;
//import com.lifesense.healthcenter.utils.JsonUtil;
//import com.lifesense.healthcenter.utils.StringUtil;
//
///**
// * 发送微信MP消息服务类
// * 
// * @author juzhi
// * 
// */
//public class MPSender {
//
//	private static WeixinCustomMessageDao customMessageDao = new WeixinCustomMessageDao();
//
//	private static Logger log = Logger.getLogger(MPSender.class);
//
//	/**
//	 * 发送客服文本消息
//	 * 
//	 * @param serviceNo
//	 *            服务号
//	 * @param touser
//	 *            普通用户openid
//	 * @param content
//	 *            文本内容
//	 */
//	public static boolean sendTextMessage(String openId, String content) {
//
//		if(StringUtil.isBlank(openId)){
//			return false;
//		}
//		
//		//如果是乐心健康的OPENDID则忽略
//		if(HealthappOpenIdVerify.isHealthApp(openId)){
//			return false;
//		}
//
//		String serviceNo = WechartServiceNoService.getServiceNo();
//		
//		TextMessage message = new TextMessage();
//
//		message.setTouser(openId);
//
//		message.setMsgtype(MsgType.TEXT.toString());
//
//		message.getText().setContent(content);
//
//		// 生成文本客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//
//		// 发送微信消息
//		int result = sendMessage(jsonMessage, serviceNo, openId, MsgType.TEXT.toString());
//		boolean flag = result==1?true:false;
//		return flag;
//	}
//	
//	/**
//	 * 保存客服文本消息，通过服务器转发患者与医生的聊天记录
//	 * 
//	 * @param serviceNo
//	 *            服务号
//	 * @param touser
//	 *            普通用户openid
//	 * @param content
//	 *            文本内容
//	 */
//	@Deprecated
//	public static boolean saveTextMessage(String openId, String content) {
//
//		TextMessage message = new TextMessage();
//
//		message.setTouser(openId);
//
//		message.setMsgtype(MsgType.TEXT.toString());
//
//		message.getText().setContent(content);
//
//		// 生成文本客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//		
//		// 保存微信消息
//		WeixinCustomMessage wcm = new WeixinCustomMessage();
//		String serviceNo = WechartServiceNoService.getServiceNo();
//		wcm.setServiceNo(serviceNo);
//		wcm.setOpenId(openId);
//		wcm.setMessageType(MsgType.TEXT.toString());
//		wcm.setMessageContent(jsonMessage);
//		wcm.setSendTime(new Date());
//		
//		// add in 2014-11-27
//		wcm.setOnchat(true);
//		
//		customMessageDao.create(wcm);
//		
//		MyBatisUtil.commitSession();
//
//		return true;
//	}
//
//	/**
//	 * 发送客服图文消息：图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应
//	 * 
//	 * @param serviceNo
//	 *            服务号
//	 * @param touser
//	 *            普通微信用户的openid
//	 * @param articles
//	 *            图文消息
//	 */
//	public static boolean sendNewsMessage(String openId, List<Article> articles) {
//		
//		if(StringUtil.isBlank(openId)){
//			return false;
//		}
//		
//		//如果是乐心健康的OPENDID则忽略
//		if(HealthappOpenIdVerify.isHealthApp(openId)){
//			return false;
//		}
//
//		NewsMessage message = new NewsMessage();
//
//		message.setTouser(openId);
//
//		message.setMsgtype(MsgType.NEWS.toString());
//
//		message.getNews().setArticles(articles);
//
//		// 生成图文客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//       
//		String serviceNo = WechartServiceNoService.getServiceNo();
//		
//		// 发送微信消息
//		int result = sendMessage(jsonMessage, serviceNo, openId, MsgType.NEWS.toString());
//		boolean flag = result==1?true:false;
//		return flag;
//	}
//	
//	/**
//	 * 发送客服语音消息
//	 * 
//	 * @param serviceNo
//	 *            服务号
//	 * @param touser
//	 *            普通微信用户的openid
//	 * @param mediaId 
//	 *            语音上传到微信上的媒体id
//     *  return 1-成功，0-失败，3-用户超过48小未与公众交互
//	 */
//	public static int sendVoiceMessage( String openId,
//			String mediaId) {
//
//		VoiceMessage message = new VoiceMessage();
//
//		message.setTouser(openId);
//
//		message.setMsgtype(MsgType.VOICE.toString());
//
//		message.getVoice().setMedia_id(mediaId);
//
//		// 生成图文客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//
//		String serviceNo = WechartServiceNoService.getServiceNo();
//		
//		// 发送微信消息
//		int result = sendMessage(jsonMessage, serviceNo, openId, MsgType.VOICE.toString());
//		return result;
//	}
//
//	/**
//	 * 发送模板信息
//	 * 
//	 * @param touser
//	 *            普通微信用户的openid
//	 * @param template_id
//	 *            模板id
//	 * @param url
//	 *            点击后的链接
//	 * @param topcolor
//	 *            顶部颜色
//	 * @param data
//	 *            模板信息对象
//	 */
//	public static boolean sendTemplateMessage( String template_id, String openId, String url, String topcolor, com.lifesense.healthcenter.service.wechat.dto.push.Data data) {
//
//		TemplateMessage message = new TemplateMessage();
//
//		message.setTouser(openId);
//
//		message.setTopcolor(topcolor);
//
//		message.setTemplate_id(template_id);
//
//	    message.setUrl(url);
//
//		message.setData(data);
//		
//		// 生成模板客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//		
//		String serviceNo = WechartServiceNoService.getSecret();
//
//		// 发送微信消息
//		int result = sendMessage(jsonMessage, serviceNo, openId, MsgType.TEMPLATE.toString());
//		boolean flag = result==1?true:false;
//		return flag;
//	}
//	
//	/**
//	 * 第三方主动发送设备状态消息给微信终端（wifi设备状态）
//	 * 
//	 * @param serviceNo
//	 *            服务号
//	 * @param touser
//	 *            普通微信用户的openid
//	 * @param template_id
//	 *            模板id
//	 * @param url
//	 *            点击后的链接
//	 * @param topcolor
//	 *            顶部颜色
//	 * @param data
//	 *            模板信息对象
//	 */
//	public static boolean sendDeviceStatusMessage(String serviceNo, String deviceId,
//			String openId, String deviceStatus) {
//
//		WifiDeviceStatusMessage message = new WifiDeviceStatusMessage();
//
//		message.setDevice_type(serviceNo);
//
//		message.setDevice_id(deviceId.toUpperCase());
//		
//		message.setOpen_id(openId);
//		
//		message.setMsg_type("2"); // 2--设备状态消息
//
//		message.setDevice_status(deviceStatus);
//
//		// 生成模板客服消息json字符串
//		String jsonMessage = JsonUtil.toJson(message);
//
//		// 发送微信消息
//		int result = sendMessage(jsonMessage, serviceNo, openId, MsgType.DEVICE_STATUS.toString());
//		boolean flag = result==1?true:false;
//		return flag;
//	}
//	
//	
//	/**
//	 * 发送微信消息
//	 * @param jsonMessage 微信消息json字符串
//	 * @param serviceNo 服务号
//	 * @param openId 普通用户的openid
//	 * @param messageType 消息类型
//	 */
//	private static int sendMessage(String jsonMessage, String serviceNo,
//			String openId, String messageType) {
//
//		// log.debug("send template message = " + jsonMessage);
//
//		// 保存微信消息
//		WeixinCustomMessage wcm = new WeixinCustomMessage();
//		wcm.setServiceNo(serviceNo);
//		wcm.setOpenId(openId);
//		wcm.setMessageType(messageType);
//		wcm.setMessageContent(jsonMessage);
//		wcm.setSendTime(new Date());
//		
//		log.debug("\n========content==========="+wcm.getMessageContent());
//
//		try {
//			
//			// 发送结果标识，true-成功，false-失败
//			int flag = 0;
//			
//			// TODO: 先直接发送，发送失败放入rides重发  P1； 日志将来放到mogoDb  P5
//			String id = customMessageDao.addOne(wcm);
//			
//			MyBatisUtil.commitSession();
//
//			// 获取token，数据库连接没有关闭
//			String accessToken = TokenService.getAccessToken();
//			
//			String apiUrl = getApiUrl(messageType);
//			
//			String body = HttpClientManager.postJson(
//					MessageFormat.format(apiUrl, accessToken), jsonMessage);
//
//			// log.debug(body);
//
//			if (StringUtil.isBlank(body)) {
//				// 需要重发
//				customMessageDao.updateSendStatus(id, 2, body);
//				MyBatisUtil.commitSession();
//				return flag;
//			}
//
//			// =========更新模板客服消息发送状态、发送结果 start
//
//			JSONObject json = JSONObject.fromObject(body);
//			
//			int resultCode = TokenService.checkErrcode(json);
//
//			if (0 == resultCode) { // 已发送成功
//				customMessageDao.updateSendStatus(id, 1, body);
//				flag = 1;
//			} else if (1 == resultCode) { // accessToken超时或失效后重发3次
//				boolean resendFlag = resendMessage(apiUrl, jsonMessage, serviceNo, id);
//				flag = resendFlag?1:0;
//			} else if(3==resultCode){// 回复时间超过限制(客服接口，用户与公众号超48小时没有交互)
//				flag = 3;
//				customMessageDao.updateSendStatus(id, 4, body);
//			} else { // 未知错误
//				customMessageDao.updateSendStatus(id, 2, body);
//			}
//
//			MyBatisUtil.commitSession();
//			
//			return flag;
//
//		} finally {
//			MyBatisUtil.closeSession();
//		}
//
//	}
//
//	/**
//	 * 重发1次
//	 */
//	private static boolean resendMessage(String url, String jsonMessage,
//			String serviceNo, String id) {
//
//		JSONObject json = null;
//
//		String accessToken = "";
//
//		String body = "";
//
//		// 发送状态：0-未发送，1-发送成功，2-发送失败
//		int sendStatus = 0;
//
//		// 发送结果标识，true-成功，false-失败
//		boolean flag = false;
//
//		// 更新token
//		accessToken = TokenService.updateAccessToken();
////		accessToken = TokenService.getAccessToken();
//
//		body = HttpClientManager.postJson(
//				MessageFormat.format(url, accessToken), jsonMessage);
//
//
//		if (StringUtil.isBlank(body)) {
//			return flag;
//		}
//
//		json = JSONObject.fromObject(body);
//		
//		// errcode判断结果代码
//		int resultCode = TokenService.checkErrcode(json);
//
//		if (0 == resultCode) { // 已发送成功
//			sendStatus = 1;
//			flag = true;
//		} else if (1 == resultCode) { // accessToken超时或失效，发送失败
//			sendStatus = 2;
//		} else { // 未知错误，后台待补发
//			sendStatus = 2;
//		}
//
//		customMessageDao.updateSendStatus(id, sendStatus, body);
//		
//		return flag;
//
//	}
//
//	/**
//	 * 根据messageType获取对应的API接口URL
//	 * 
//	 * @param messageType
//	 * @return
//	 */
//	private static String getApiUrl(String messageType) {
//		String apiUrl = null;
//		if (StringUtil.equals(MsgType.TEMPLATE.toString(), messageType)) {
//			apiUrl = WeixinAPI.TEMPLATE_SERVICE_URL;
//		} else if (StringUtil.equals(MsgType.TEXT.toString(), messageType)
//				|| StringUtil.equals(MsgType.NEWS.toString(), messageType)
//				|| StringUtil.equals(MsgType.VOICE.toString(), messageType)) {
//			apiUrl = WeixinAPI.CUSTOMER_SERVICE_URL;
//		} else if (StringUtil.equals(MsgType.DEVICE_STATUS.toString(), messageType)) {
//			apiUrl = WeixinAPI.TRANSMSG_URL;
//		}
//		return apiUrl;
//	}
//
//	/**
//	 * 定时补发文本、图文、模板等消息
//	 */
//	public static synchronized void timerReissueMessage() {
//
//		try {
//			
//			List<WeixinCustomMessage> wcmList = customMessageDao.getNotSendMsgList();
//			
//			if (null == wcmList || wcmList.isEmpty() || wcmList.size() == 0) {
//				return;
//			}
//			
//			String body = null;
//
//			String apiUrl = "";
//
//			JSONObject json = null;
//			log.debug("\n========= 定时补发发送失败的微信消息（包括文本、图文、模板消息）start in \n" + DateUtils.dateFormat(new Date()) + "\n timestamp : "+System.currentTimeMillis()+" =========");
//			log.debug("\n待补发消息数量=" + wcmList.size());
//
//			for (WeixinCustomMessage wcm : wcmList) {
//				
//				// 超过5次，不重发
//				if (wcm.getSendCount() >= 5) {
//					customMessageDao.updateSendStatus(wcm.getId(), 3, "中止发送");
//					MyBatisUtil.commitSession();
//					continue;
//				}
//
//				apiUrl = getApiUrl(wcm.getMessageType());
//
//				body = HttpClientManager.postJson(MessageFormat.format(apiUrl, TokenService.getAccessToken()),
//						wcm.getMessageContent());
//
//				if (StringUtil.isBlank(body)) {
//					continue;
//				}
//
//				// =========更新文本客服消息发送状态、发送结果 start
//
//				json = JSONObject.fromObject(body);
//				
//				// errcode判断结果代码
//				int resultCode = TokenService.checkErrcode(json);
//
//				if (0 == resultCode) { // 已发送成功
//					customMessageDao.updateSendStatus(wcm.getId(), 1, body);
//				}  else { // 未知错误
//					customMessageDao.updateSendStatus(wcm.getId(), 2, body);
//				}
//
//				MyBatisUtil.commitSession();
//
//			}
//
//			log.debug("\n========= 定时补发发送失败的微信消息（包括文本、图文、模板消息）end in " + DateUtils.dateFormat(new Date()) + " =========");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			MyBatisUtil.closeSession();
//		}
//
//	}
//	
//	/**
//	 * 定时转发微信消息（患者与医生之间的对话互相转发）
//	 */
//	public static synchronized void timerForwardMessage() {
//
//		try {
//			
//			List<WeixinCustomMessage> wcmList = customMessageDao.getNotSendOnChatMsgList();
//			
//			if (null == wcmList || wcmList.isEmpty() || wcmList.size() == 0) {
//				return;
//			}
//			
//			String body = null;
//
//			String apiUrl = "";
//
//			JSONObject json = null;
//			log.debug("\n========= 定时转发微信消息（患者与医生之间的对话互相转发）start in \n" + DateUtils.dateFormat(new Date()) + "\n timestamp : "+System.currentTimeMillis()+" =========");
//			log.debug("\n待转发消息数量=" + wcmList.size());
//
//			for (WeixinCustomMessage wcm : wcmList) {
//				
//				// 超过5次，不重发
//				if (wcm.getSendCount() >= 5) {
//					customMessageDao.updateSendStatus(wcm.getId(), 3, "中止发送");
//					MyBatisUtil.commitSession();
//					continue;
//				}
//
//				apiUrl = getApiUrl(wcm.getMessageType());
//
//				body = HttpClientManager.postJson(
//						MessageFormat.format(apiUrl, wcm.getAccessToken()),
//						wcm.getMessageContent());
//
//				if (StringUtil.isBlank(body)) {
//					continue;
//				}
//
//				// =========更新文本客服消息发送状态、发送结果 start
//
//				json = JSONObject.fromObject(body);
//				
//				// errcode判断结果代码
//				int resultCode = TokenService.checkErrcode(json);
//
//				if (0 == resultCode) { // 已发送成功
//					customMessageDao.updateSendStatus(wcm.getId(), 1, body);
//				} 
//				MyBatisUtil.commitSession();
//
//			}
//
//			log.debug("\n========= 定时转发微信消息（患者与医生之间的对话互相转发） end in " + DateUtils.dateFormat(new Date()) + " =========");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			MyBatisUtil.closeSession();
//		}
//
//	}
//
//	
//	/**
//	 * 清除7天的发送成功的微信日志
//	 */
//	public static void clearWXMessage(int remindDays) {
//		try {
//			customMessageDao.clearWXMessage(remindDays);
//			MyBatisUtil.commitSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			MyBatisUtil.closeSession();
//		}
//		
//	}
//
//
//}
