//package com.wzd.service.wechat.msg;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import com.lifesense.healthcenter.common.Constant;
//import com.lifesense.healthcenter.common.MeasureAdvicesBp;
//import com.lifesense.healthcenter.common.MeasureAdvicesGlucometer;
//import com.lifesense.healthcenter.model.entity.records.BpRecord;
//import com.lifesense.healthcenter.model.entity.records.GlucometerRecord;
//import com.lifesense.healthcenter.model.entity.records.WalkingRecord;
//import com.lifesense.healthcenter.model.entity.records.WeightRecord;
//import com.lifesense.healthcenter.service.records.bp.enums.BPLevelEnum;
//import com.lifesense.healthcenter.service.records.glucometer.enums.BloodGlucoseLevels;
//import com.lifesense.healthcenter.service.records.glucometer.enums.PhysicalCondition;
//import com.lifesense.healthcenter.service.wechat.dto.device_text.enums.DeviceTypeEnum;
//import com.lifesense.healthcenter.service.wechat.dto.push.Article;
//import com.lifesense.healthcenter.service.wechat.dto.push.ValueData;
//import com.lifesense.healthcenter.service.wechat.dto.push.bp.BpData;
//import com.lifesense.healthcenter.service.wechat.dto.push.device.DeviceVoiceData;
//import com.lifesense.healthcenter.service.wechat.dto.push.device.UnbindDeviceData;
//import com.lifesense.healthcenter.utils.DateUtils;
//import com.lifesense.healthcenter.utils.NumericalUtil;
//import com.lifesense.healthcenter.utils.StringUtil;
//
///**
// * 血压微信信息推送
// * 
// * @author leisel
// * 
// */
//public class BPSender {
//	
//	@SuppressWarnings("unused")
//	private static Logger log = Logger.getLogger(BPSender.class);
//
//	/**
//	 * 关注成功后推送使用秘笈
//	 * 
//	 * @param domain
//	 *            域名
//	 * @param serviceNo
//	 *            服务号
//	 * @param openId
//	 *            消息发送目标
//	 * @return
//	 */
////	public static void sendOnSubscribe(String domain, String serviceNo,
////			String openId) {
////
////		// 设置图文消息对象
////		List<Article> articles = new ArrayList<Article>();
////
////		// 设置图文消息对象
////		Article article = new Article();
////
////		article.setTitle("操作指南");
////		article.setUrl(domain + BPMPAccount.ATTENTION_HTMLURL_BP);
////		article.setPicurl(domain + BPMPAccount.ATTENTION_IMGURL_BP);
////		articles.add(article);
////
////		// 发送欢迎消息，图文
////		MPSender.sendNewsMessage(serviceNo, openId, articles);
////
////	}
////	
////	/**
////	 * 关注普通二维码成功后推送欢迎体验
////	 * 
////	 * @param domain
////	 *            域名
////	 * @param serviceNo
////	 *            服务号
////	 * @param openId
////	 *            消息发送目标
////	 * @return
////	 */
////	public static void sendOnSubscribeForExperience(String domain, String serviceNo,
////			String openId) {
////		
////		// 设置图文消息对象
////		List<Article> articles = new ArrayList<Article>();
////		
////		// 设置图文消息对象
////		Article article = new Article();
////		
////		article.setTitle("欢迎体验");
////		article.setUrl(domain + "");
////		article.setPicurl(domain + BPMPAccount.ATTENTION_IMGURL_BP);
////		articles.add(article);
////		
////		// 发送欢迎消息，图文
////		MPSender.sendNewsMessage(serviceNo, openId, articles);
////		
////	}
//
//	/**
//	 * 绑定设备成功后推送消息(根据产品型号)
//	 * 
//	 * @param domain
//	 *            域名
//	 * @param serviceNo
//	 *            服务号
//	 * @param openId
//	 *            消息发送目标
//	 * @return
//	 */
//	public static void sendArticleMsg(String htmlUrl, String picUrl, String openId, String title) {
//
//		// 设置图文消息对象
//		List<Article> articles = new ArrayList<Article>();
//
//		// 设置图文消息对象
//		Article article = new Article();
//
//		article.setTitle(title);
//		article.setUrl(htmlUrl);
//		article.setPicurl(picUrl);
//		articles.add(article);
//
//		// 发送欢迎消息，图文
//		MPSender.sendNewsMessage(openId, articles);
//	}
//	
//	
//	/**
//	 * 绑定设备成功后推送消息(根据产品型号)
//	 * 
//	 * @param domain
//	 *            域名
//	 * @param serviceNo
//	 *            服务号
//	 * @param openId
//	 *            消息发送目标
//	 * @param title
//	 *            标题
//	 * @param description
//	 *            描述
//	 * @return
//	 */
//	public static void sendArticleMsg(String htmlUrl, String picUrl, String openId, String title, String description) {
//
//		// 设置图文消息对象
//		List<Article> articles = new ArrayList<Article>();
//
//		// 设置图文消息对象
//		Article article = new Article();
//
//		article.setTitle(title);
//		article.setUrl(htmlUrl);
//		article.setPicurl(picUrl);
//		article.setDescription(description);
//		articles.add(article);
//
//		// 发送欢迎消息，图文
//		MPSender.sendNewsMessage(openId, articles);
//	}
//	
////	/**
////	 * 发送图文消息
////	 * 
////	 * @param domain
////	 *            域名
////	 * @param serviceNo
////	 *            服务号
////	 * @param openId
////	 *            消息发送目标
////	 * @return
////	 */
////	public static void sendPicTextMsg(String serviceNo,
////			String openId, String title, String url, String picUrl) {
////
////		// 设置图文消息对象
////		List<Article> articles = new ArrayList<Article>();
////
////		// 设置图文消息对象
////		Article article = new Article();
////
////		article.setTitle(title);
////		article.setUrl(url);
////		article.setPicurl(picUrl);
////		articles.add(article);
////
////		// 发送欢迎消息，图文
////		MPSender.sendNewsMessage(serviceNo, openId, articles);
////
////	}
//	
////	/**
////	 * 发送请求会话咨询图文消息
////	 * 
////	 * @param domain
////	 *            域名
////	 * @param serviceNo
////	 *            服务号
////	 * @param openId
////	 *            消息发送目标
////	 * @return
////	 */
////	public static void sendOnRequestChat(String domain, String serviceNo, String openId, String requestUserName) {
////
////		// 设置图文消息对象
////		List<Article> articles = new ArrayList<Article>();
////
////		// 设置图文消息对象
////		Article article = new Article();
////
////		article.setTitle(requestUserName+"发起了咨询？ 同意回复y，不同意或在忙回复n。");
////		article.setUrl("");
////		article.setPicurl(domain + "userfiles/img/blood/welcome.png");
////		articles.add(article);
////
////		// 发送欢迎消息，图文
////		MPSender.sendNewsMessage(serviceNo, openId, articles);
////
////	}
//
//	/**
//	 * 回复用户的文本消息
//	 * 
//	 * @param serviceNo
//	 * @param touser
//	 * @param content
//	 */
//	public static void sendOnChat(String serviceNo, String touser,
//			String content) {
//
//		// 发送咨询提醒文本消息
//		MPSender.sendTextMessage(touser, content);
//
//	}
//
////	/**
////	 * 删除绑定消息通知（模板消息）
////	 * 
////	 * @param serviceNo
////	 *            服务号
////	 * @param openId
////	 *            消息发送目标
////	 * @return
////	 */
////	public static void sendOnRemoveBind(String serviceNo, String openId, String deviceSn) {
////
////		// 封装模板信息 start ====================
////		com.lifesense.wxbp.model.dto.pushweixinmessage.bp.UnbindMsgData data = new com.lifesense.wxbp.model.dto.pushweixinmessage.bp.UnbindMsgData();
////
////		data.setFirst(new ValueData("您已解绑设备，不再接收测量数据。", "#e65b5b"));
////		data.setKeyword1(new ValueData(deviceSn, "#173177"));
////		data.setKeyword2(new ValueData(DateUtils.formatDate(new Date(), "yyyy年MM月dd日 HH:mm"), "#173177"));
////		
////		String remark = "可通过以下方法找回设备：测量血压后，30分钟内在微信聊天框输入当次测量值：高压/低压/脉搏，如90/60/71。";
////		
////		data.setRemark(new ValueData(remark, "#173177"));
////
////		// 封装模板信息 end ====================
////
////		// 发送模板信息
////		MPSender.sendTemplateMessage(serviceNo, BPMPAccount.TEMPLATE_ID_UNBIND,
////				openId, "#", "#e65b5b", data);
////
////	}
//	
//	
//	/**
//	 * 发送血压模板消息
//	 * 
//	 * @param bpRecord
//	 * @param healthAdvice
//	 * @param name
//	 * @param openId
//	 * @param serviceNo
//	 * @param isManager 是否是该设备的管理者
//	 */
//	public static boolean sendBPTemplateMessage(BpRecord bpRecord , String memberName, String openId, boolean isManager, String nickname) {
//
//		// 收缩压
//		int systolicPressure = StringUtil.toInteger(bpRecord.getSystolicPressure(), 0);
//
//		// 舒张压
//		int diastolicPressure =  StringUtil.toInteger(bpRecord.getDiastolicPressure(), 0);
//
//		// 心率
//		int heartRate = bpRecord.getHeartRate();
//
//		// 封装模板信息 start ====================
//		BpData data = new BpData();
//
//		// 血压水平
//		BPLevelEnum bpLevelEnum = BPLevelEnum.getBpLevelEnum(systolicPressure, diastolicPressure);
//
//		String bpLevelTemplateColor = BPLevelEnum.getBpLevelTemplateColor(systolicPressure, diastolicPressure);
//
//		String title = DateUtils.formatTemplateDate(bpRecord.getMeasurementDate(), "{0}月{1}日，{2}点{3}分，");
//		title += memberName + "测量了血压：";
//		
//		if(bpRecord.getUserNo()==0){// 表示用户刷身份证卡过来的数据
//			String rfid = StringUtil.isBlankOrNull(bpRecord.getRfid())?"未知":bpRecord.getRfid();
//			title = "您收到一笔身份卡是【"+rfid+"】的血压测量结果，测量时间：" + DateUtils.formatDate(bpRecord.getMeasurementDate(), "yyyy-MM-dd HH:mm") + "。";
//		}else{// 区分管理者和分享者的测量结果标题
//			if (!isManager) {
//				if (StringUtil.isBlank(nickname)) 
//					title = "您收到一笔"+memberName+"的血压分享结果，测量时间：" + DateUtils.formatDate(bpRecord.getMeasurementDate(), "yyyy-MM-dd HH:mm") + "。";
//				else
//					title = "您收到一笔"+nickname+"家人"+memberName+"的血压分享结果，测量时间：" + DateUtils.formatDate(bpRecord.getMeasurementDate(), "yyyy-MM-dd HH:mm") + "。";
//			}
//		}
//
//		data.setFirst(new ValueData(title, "#000000"));
//		data.setRemark(new ValueData("\n"+MeasureAdvicesBp.getAdvice(bpRecord.getBpstate()), "#717171"));
//		data.setHighPressure(new ValueData(systolicPressure + "mmHg", "#173177"));
//		data.setLowPressure(new ValueData(diastolicPressure + "mmHg", "#173177"));
//		data.setHeart(new ValueData(heartRate + "次/分", "#173177"));
//		data.setBloodPressureLevel(new ValueData(bpLevelEnum.toString(), bpLevelTemplateColor));
//
//		// 点击模板消息链接
//		String url = "";
//		
//		if (bpRecord.isMovementError()) { // 抖动的血压数据
//			data.setRemark(new ValueData("测量时手臂或手腕出现了抖动，测量不准确，数据未保存。测量过程请保持放松、安静。", "#0000ff"));
//		}else if (StringUtil.isBlank(bpRecord.getMemberId())) { // 无匹配用户
//			data.setRemark(new ValueData("您有一笔未匹配成员的数据，请点击分配。", "#ee756f"));
//			url = Constant.getPatientHostname()
//					+ "/view/records/matching_user?recordId=" + bpRecord.getId()
//					+ "&deviceType=" + DeviceTypeEnum.血压计.getCode();
//		}else if(isManager){
//			url = Constant.getPatientHostname() + "/view/#/records/bp_chart?member_id=" + bpRecord.getMemberId() + "&member_name=" + memberName;
//		}
//
//		// 封装模板信息 end ====================
//
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getBpTemplateId(), openId, url, bpLevelTemplateColor, data);
//
//		return result;
//
//	}
//	
//	
//	/**
//	 * 发送运动测量数据模板消息
//	 * 
//	 * @param bpRecord
//	 * @param healthAdvice
//	 * @param name
//	 * @param openId
//	 * @param serviceNo
//	 */
//	public static boolean sendStepTemplateMessage(WalkingRecord record, String healthAdvice, String memberName, String openId) {
//
//		// 封装模板信息 start ====================
//		com.lifesense.healthcenter.service.wechat.dto.push.step.StepData data = new com.lifesense.healthcenter.service.wechat.dto.push.step.StepData();
//
//
//		String title = DateUtils.formatTemplateDate(record.getMeasurementDate(), "{0}月{1}日，{2}点{3}分，");
//		
//		title += memberName + "进行了运动：";
//
//		// 卡路里
//		Double calories = record.getCalories();
//		
//		// 步数
//		int step = record.getSteps();
//		
//		data.setFirst(new ValueData(title, "#000000"));
//		data.setKeyword1(new ValueData(step + "步", "#173177"));
//		
//		if (calories >= 100)
//			data.setKeyword2(new ValueData(NumericalUtil.DoubleToInteger(calories) + "大卡", "#173177"));
//		else
//			data.setKeyword2(new ValueData(NumericalUtil.DoubleToInteger(calories*1000) + "卡", "#173177"));
//		data.setRemark(new ValueData("亲，坚持运动，有助健康喔~", "#ee756f"));
//
//		// 封装模板信息 end ====================
//
//		String templateColor = "#000000";
//		
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getStepTemplateId(), openId, "", templateColor, data);
//
//		return result;
//
//	}
//	
//	
//	/**
//	 * 发送体重测量数据模板消息
//	 * 
//	 * @param bpRecord
//	 * @param healthAdvice
//	 * @param name
//	 * @param openId
//	 * @param serviceNo
//	 */
//	public static boolean sendWeightTemplateMessage(WeightRecord record, String memberName, String openId) {
//
//		// 步数
//		Double weight = record.getWeight();
//
//		// 卡路里
//		Double bmi =  record.getBmi();
//
//
//		// 封装模板信息 start ====================
//		com.lifesense.healthcenter.service.wechat.dto.push.weight.WeightData data = new com.lifesense.healthcenter.service.wechat.dto.push.weight.WeightData();
//
//		String title = DateUtils.formatTemplateDate(record.getMeasurementDate(), "{0}月{1}日，{2}点{3}分，");
//		title += memberName + "测量了体重：";
//
//		data.setFirst(new ValueData(title, "#000000"));
//		data.setWeight(new ValueData(weight + "kg", "#173177"));
//		data.setBmi(new ValueData(bmi + "", "#173177"));
//		
//		// 点击模板消息链接
//		String url = "";
//		
//		if (StringUtil.isBlank(record.getMemberId())) { // 无匹配用户
//			data.setRemark(new ValueData("未知用户，点击认领", "#ee756f"));
//			url = Constant.getPatientHostname()
//					+ "/view/records/matching_user?recordId=" + record.getId()
//					+ "&deviceType=" + DeviceTypeEnum.体重秤.getCode();
//			
//			System.out.println("\n@@@@@@url@@@@@@="+url);
//		}
//
//		// 封装模板信息 end ====================
//
//		String templateColor = "#000000";
//		
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getWeightTemplateId(), openId, url, templateColor, data);
//
//		return result;
//
//	}
//	
//	
//	/**
//	 * 发送解绑设备模板消息
//	 * @param openId
//	 * @param deviceSn
//	 * @param title
//	 * @return
//	 */
//	public static boolean sendUnbindDeviceTemplateMessage(String openId, String deviceSn, String title) {
//
//
//		// 封装模板信息 start ====================
//		UnbindDeviceData data = new UnbindDeviceData();
//
//		data.setFirst(new ValueData(title, "#ee756f"));
//		data.setKeyword1(new ValueData(deviceSn, "#000000"));
//		data.setKeyword2(new ValueData(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm"), "#000000"));
//		
//		data.setRemark(new ValueData("您对我们的智能产品有任何疑问，请联系我们！", "#000000"));
//
//		// 封装模板信息 end ====================
//
//		String templateColor = "#000000";
//		
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getUnbindDeviceTemplateId(), openId, "", templateColor, data);
//
//		return result;
//
//	}
//	
//	
//	
//	/**
//	 * 发送语音血压计语音模板消息
//	 * @param openId
//	 * @param deviceSn
//	 * @param title
//	 * @return
//	 */
//	public static boolean sendDeviceVoiceTemplateMessage(String openId, String title, String content, Date date, String url, String describe) {
//
//		// 封装模板信息 start ====================
//		DeviceVoiceData data = new DeviceVoiceData();
//
//		data.setFirst(new ValueData(title, "#ee756f"));
//		data.setKeyword1(new ValueData(content, "#000000"));
//		data.setKeyword2(new ValueData(DateUtils.formatDate(date, "HH:mm"), "#000000"));
//		
//		data.setRemark(new ValueData(describe, "#06e461"));
//
//		// 封装模板信息 end ====================
//
//		String templateColor = "#000000";
//		
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getDeviceVoiceTemplateId(), openId, url, templateColor, data);
//
//		return result;
//
//	}
//	
//	
//	
//	/**
//	 * 发送 血糖测试 
//	 * @param openId
//	 * @param measurementDate
//	 * @param glucoseConcentration
//	 * @param meal
//	 * @return
//	 * 
//	 * {{first.DATA}}
//		血糖值：{{keyword1.DATA}}
//		血糖水平：{{keyword2.DATA}}
//		{{remark.DATA}}
//	 */
//	public static boolean sendGlucometerTemplateMessage(
//			GlucometerRecord record,
//			String openId ,
//			boolean isManager, 
//			String memberName,
//			String nickname
//			) {
//		
//		PhysicalCondition physicalCondition = PhysicalCondition.valueOf(StringUtil.toInteger(record.getMeal(),0));
//		BloodGlucoseLevels bloodGlucoseLevels = BloodGlucoseLevels.getBloodGlucoseLevelsCode(record.getGlucoseConcentration(),physicalCondition);
//		
//		// 封装模板信息 start ====================
//		com.lifesense.healthcenter.service.wechat.dto.push.glucometer.GlucometerData data = new com.lifesense.healthcenter.service.wechat.dto.push.glucometer.GlucometerData();
//
//		String title = DateUtils.formatTemplateDate(record.getMeasurementDate(), "{0}月{1}日，{2}点{3}分，");
//		title += memberName + "测量了血糖：";
//
//		// 区分管理者和分享者的测量结果标题
//		if (!isManager) {
//			if (StringUtil.isBlank(nickname)) 
//				title = "您收到一笔"+memberName+"的血糖分享结果，测量时间：" + DateUtils.formatDate(record.getMeasurementDate(), "yyyy-MM-dd HH:mm") + "。";
//			else
//				title = "您收到一笔"+nickname+"家人"+memberName+"的血糖分享结果，测量时间：" + DateUtils.formatDate(record.getMeasurementDate(), "yyyy-MM-dd HH:mm") + "。";
//		}
//		
//		data.setFirst(new ValueData(title, "#000000"));
//		data.setKeyword1(new ValueData(record.getGlucoseConcentration() + " mmol/L", "#173177"));
//		data.setKeyword2(new ValueData(bloodGlucoseLevels.toString() + "", bloodGlucoseLevels.getColor()));
////		data.setRemark(new ValueData("身体状况:  "+physicalCondition.toString(), "#000000"));
//		String advice = MeasureAdvicesGlucometer.getAdvice(physicalCondition.getCode(), bloodGlucoseLevels, record.getGlucoseConcentration());
//		data.setRemark(new ValueData("\n"+advice, "#717171"));
//		
//		
//		// 点击模板消息链接
//		String url = "";
//
//		if (StringUtil.isBlank(record.getMemberId())) { // 无匹配用户
//			data.setRemark(new ValueData("您有一笔未匹配成员的数据，请点击分配。", "#ee756f"));
//			url = Constant.getPatientHostname() + "/view/records/matching_user?recordId=" + record.getId() + "&deviceType=" + DeviceTypeEnum.血糖仪.getCode();
//		}else if(isManager){
//			url = Constant.getPatientHostname()+"/view/#/records/glucometer_chart?member_id=" + record.getMemberId() + "&member_name=" + memberName;
//		}
//		
//		// 封装模板信息 end ====================
//
//		String templateColor = "#000000";
//		
//		// 发送模板信息
//		boolean result = MPSender.sendTemplateMessage(Constant.getBloodGlucoseTemplateId(), openId, url, templateColor, data);
//
//		return result;
//
//	}
//	
//}
