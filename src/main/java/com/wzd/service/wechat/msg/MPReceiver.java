//package com.wzd.service.wechat.msg;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//
//import com.lifesense.healthcenter.common.Constant;
//import com.lifesense.healthcenter.common.CustomerEnum;
//import com.lifesense.healthcenter.jobs.CheckCustomerTimmer;
//import com.lifesense.healthcenter.model.dao.devices.DeviceDao;
//import com.lifesense.healthcenter.model.entity.devices.Device;
//import com.lifesense.healthcenter.service.async.AsyncExecutorService;
//import com.lifesense.healthcenter.service.devices.enums.CommunicationTypeEnum;
//import com.lifesense.healthcenter.service.wechat.constants.MsgType;
//import com.lifesense.healthcenter.service.wechat.dto.WechatMessage;
//import com.lifesense.healthcenter.service.wechat.dto.device_text.enums.DeviceTypeEnum;
//import com.lifesense.healthcenter.service.wechat.dto.message.WXCustomerMessge;
//import com.lifesense.healthcenter.utils.DateUtils;
//import com.lifesense.healthcenter.utils.StringUtil;
//import com.lifesense.healthcenter.utils.TimmerUtils;
//
///**
// * 接受微信发送的消息
// * 
// * @author lyb
// * 
// */
//public class MPReceiver {
//
//	private static Logger log = Logger.getLogger(MPReceiver.class);
//
//	private DeviceDao deviceDao = new DeviceDao();
//
//	private AsyncExecutorService asyncExecutorService = new AsyncExecutorService();
//
//	/**
//	 * 工众号发送的消息接受处理
//	 * 
//	 * @param message
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	public String receive(WechatMessage message, HttpServletRequest request)
//			throws IOException {
//
//		String msgType = message.getMsgType();
//		
//		String result = "";
//		
//		if (StringUtil.equals(MsgType.VOICE.toString(), msgType)) {// 处理语音血压计语音
//			this.deBpVoice(message);
//		}else{
//			// 处理多客服
//			result = this.dealCustomerMessage(message);
//		}
//		return result;
//	}
//
//	/**
//	 * 处理语音血压计语音
//	 * @param message
//	 */
//	private void deBpVoice(WechatMessage message) {
//		String openId = message.getFromUserName();
//
//		String mediaId = message.getMediaId();
//		// 根据关联的openId获取设备信息
//		List<Device> deviceList = deviceDao.getByOpenId(openId);
//		
//		for (Device devcie : deviceList) {
//			if (StringUtil.equalsIgnoreCase(DeviceTypeEnum.血压计.getCode(), devcie.getDeviceType())
//					&&CommunicationTypeEnum.WiFi_GPRS.code==devcie.getCommunicationType()) {// 如果是语音血压计则发送语音消息给网关
//				String deviceId = devcie.getId();
//				asyncExecutorService.sendBpVoice(deviceId, openId, mediaId, "0", "0");
//			}
//		}
//
//	}
//
//	/**
//	 * 处理多客服
//	 * 
//	 * @param message
//	 * @throws IOException
//	 */
//	private String dealCustomerMessage(WechatMessage message) throws IOException {
//		// 公众号id
//		String serviceNo = message.getToUserName();
//		String openId = message.getFromUserName();
//		String content = message.getContent();
//
//		log.debug("\n@@@dealCustomerMessage content = " + content);
//		WXCustomerMessge customer = WXCustomerMessge.getWXCustomerMessge(openId);
//		
////		// 重阳节活动(10月21日20:00点 至 22:00点)
////		if (StringUtil.equals("登高", StringUtil.trim(content))) {
////			Date now = new Date();
////			Date Start = DateUtils.parseToDate("2015-10-21 20:00:00", DateUtils.P_TIMESTAMP);
////			Date end = DateUtils.parseToDate("2015-10-21 22:00:00", DateUtils.P_TIMESTAMP);
////			if(now.after(Start)&&now.before(end)){// 判断时间
////				String num = SequenceUtils.getSequence("denggao",1, 100000000)+"";
////				String textMessage = "";
////				switch (num) {
////				case "9":
////				case "399":
////				case "999":
////				case "3999":
////				case "9999":
////				case "39999":
////				case "99999":
////					textMessage = "恭喜成功攀上"+num+"楼，获得登高大礼包一份！立刻把乐心血压计和运动手环带回家。请在这里回复您的姓名，联系电话和收货地址，我们将会安排奖品配送~";
////					MPSender.sendTextMessage(openId, textMessage);
////					return CustomerEnum.微信多客服.code;
////				default:
////					textMessage = "欲穷千里目，更上一层楼。你踩到了"+num+"层，还得继续努力往上攀哦~";
////					MPSender.sendTextMessage(openId, textMessage);
////					return CustomerEnum.其他.code;
////				}
////			}else{//不再时间范围
////				MPSender.sendTextMessage(openId, "踩楼登高活动时间：10月21日20:00点 至 22:00点。");
////				return CustomerEnum.其他.code;
////			}
////		}
//
//		// 发送微信消息,回复用户
//		if (System.currentTimeMillis() - customer.getTimestample() > 300000) {
//			String textMessage = "";
//			textMessage = "你好，欢迎关注乐心健康！乐心健康，智护家庭。";
//	    	textMessage += "\n\n";
//	    	textMessage += "若您对乐心智能产品有任何疑问，欢迎前来咨询。";
//	    	textMessage += "\n\n";
//	    	textMessage += "乐心血压计：<a href='"+Constant.getPatientHostname()+"/view/#/problems/issue'>常见问题</a>";
//	    	textMessage += "\n\n";
//	    	textMessage += "输入“0”直接进入人工服务，工作时间（周一至周五上午9:00到12:00，下午1:30到6:30）内我们一定及时回复您。您也可以拨打官方服务电话400-600-2323咨询。";
//	    	textMessage += "\n\n";
//	    	textMessage += "乐心健康团队再次感谢您的支持！";
//			MPSender.sendTextMessage(openId, textMessage);
//			WXCustomerMessge.writeCache(serviceNo, openId, false);
//			return CustomerEnum.其他.code;
//		}
//
//		// 如果发送的是关键字，连接多客服，写缓存
//		if (StringUtil.equals("0", StringUtil.trim(content))) {
//			String textMessage = "";
//			textMessage = "亲，欢迎前来咨询，我们将马上为您服务。";
//			MPSender.sendTextMessage(openId, textMessage);
//			CheckCustomerTimmer timmer = new CheckCustomerTimmer();
//			timmer.setOpenId(openId);
//			// 任务5分钟后执行一次
//			TimmerUtils.schedule(timmer, 300000);
//
//			// 写缓存
//			WXCustomerMessge.writeCache(serviceNo, openId, false);
//			return CustomerEnum.微信多客服.code;
//		}
//
//		// 如果发送的是关键字，发送语音血压计
//		if (StringUtil.equals("1", StringUtil.trim(content))) {
//			// 写缓存
//			WXCustomerMessge.writeCache(serviceNo, openId, false);
//			return CustomerEnum.语音血压计语音.code;
//		}
//
//		// 如果发送的不是关键字，写缓存
//		WXCustomerMessge.writeCache(serviceNo, openId, false);
//		return CustomerEnum.其他.code;
//	}
//	
//	public static void main(String[] args) {
//		Date now = DateUtils.parseToDate("2015-10-21 21:59:59",  DateUtils.P_TIMESTAMP);
//		// 重阳节活动(10月21日20:00点 至 22:00点)
//		Date Start = DateUtils.parseToDate("2015-10-21 20:00:00", DateUtils.P_TIMESTAMP);
//		Date end = DateUtils.parseToDate("2015-10-21 22:00:00", DateUtils.P_TIMESTAMP);
//		System.out.println(now.after(Start));
//		System.out.println(now.before(end));
//	}
//
//}
