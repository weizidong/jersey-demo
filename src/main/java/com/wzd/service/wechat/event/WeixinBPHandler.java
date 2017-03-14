//package com.lifesense.healthcenter.service.wechat.event;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.lifesense.framework.model.util.MyBatisUtil;
//import com.lifesense.framework.util.log.LoggerAdapter;
//import com.lifesense.framework.util.log.LoggerAdapterFacory;
//import com.lifesense.healthcenter.common.QrcodePrefixEnum;
//import com.lifesense.healthcenter.model.cache.device.DeviceCache;
//import com.lifesense.healthcenter.model.entity.devices.Device;
//import com.lifesense.healthcenter.model.entity.family.Account;
//import com.lifesense.healthcenter.service.base.MpAccessLogService;
//import com.lifesense.healthcenter.service.base.enums.MpLogTypeEnum;
//import com.lifesense.healthcenter.service.devices.DeviceService;
//import com.lifesense.healthcenter.service.devices.enums.DeviceBindEnum;
//import com.lifesense.healthcenter.service.doctor.DoctorService;
//import com.lifesense.healthcenter.service.family.AccountService;
//import com.lifesense.healthcenter.service.family.MemberService;
//import com.lifesense.healthcenter.service.thirdpart.impl.LuoHuAppService;
//import com.lifesense.healthcenter.service.wechat.WechartServiceNoService;
//import com.lifesense.healthcenter.service.wechat.constants.MsgType;
//import com.lifesense.healthcenter.service.wechat.dto.WechatMessage;
//import com.lifesense.healthcenter.service.wechat.dto.message.WXCustomerMessge;
//import com.lifesense.healthcenter.service.wechat.msg.BPSender;
//import com.lifesense.healthcenter.service.wechat.msg.MPSender;
//import com.lifesense.healthcenter.utils.CachedClient;
//import com.lifesense.healthcenter.utils.MD5Utils;
//import com.lifesense.healthcenter.utils.StringUtil;
//import com.lifesense.healthcenter.utils.ThreadPoolUtils;
//
///**
// * 血压公众号微信消息接收器
// * @author juzhi
// *
// */
//public class WeixinBPHandler {
//
//	private AccountService accountService = new AccountService();
//	
//	private MemberService memberService = new MemberService();
//	
//	private DeviceService deviceService = new DeviceService();
//	
//	private LuoHuAppService luoHuAppService = new LuoHuAppService();
//	
//	private DoctorService doctorService = new DoctorService();
//	
//	private MpAccessLogService mpAccessLogService = new MpAccessLogService();
//	
//	private LoggerAdapter log = LoggerAdapterFacory.getLogger(WeixinBPHandler.class);
//	
//	/**
//	 * 处理微信发送过来的消息
//	 * 
//	 * @param xml
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	public String receiveMessage(WechatMessage message,
//			HttpServletRequest request) {
//
//		long startTime = System.currentTimeMillis();
//		
//		String msgType = message.getMsgType();
//
//		String openId = message.getFromUserName();
//
//		String content = message.getContent();
//
//		log.debug("来自患者端的消息content=" + content + "\n");
//
//		String replyContent = ""; // 回复内容
//		
//		String event = message.getEvent();
//
//		// 处理微信多客服接入与断开事件
//		if(StringUtil.equals(MsgType.EVENT.toString(),msgType)
//				&&(StringUtil.equals(MsgType.Event.KF_CLOSE_SESSION,event)||StringUtil.equals(MsgType.Event.KF_CREATE_SESSION,event))){
//			this.dealCustomerEvent(message);
//			return "";
//		}
//		// 处理微信多客服接入与断开事件 end
//		
//		// 点击公众号底部菜单VIEW，直接返回, 减少回应微信服务器时间
//		if (StringUtil.equals(MsgType.Event.VIEW.toString(), event)) {
//			String url = message.getEventKey();
//			// 创建公众号访问日志
//			mpAccessLogService.createLog(openId, MpLogTypeEnum.公众号点击菜单日志, url);
//			return replyContent;
//		}
//		
//		if (StringUtil.equals(MsgType.EVENT.toString(), msgType)) { // 事件消息
//
//			String eventKey = message.getEventKey();
//			
//			// 关注/取消关注事件
//			if (StringUtil.isBlank(eventKey)) { 
//				this.subscribe(message);
//				return replyContent;
//			}
//			
//			// 自定义点击菜单事件
//			if (StringUtil.equalsIgnoreCase(MsgType.Event.CLICK.toString(), event)) {
//				this.clickMenu(message);
//			} else { // 扫描带参数二维码事件
//				this.scan(message);
//			}
//			
//
//		} else if (StringUtil.equals(MsgType.DEVICE_EVENT.toString(), msgType)) { // 设备绑定消息
//
//			String deviceId = message.getDeviceID().toLowerCase();
//			
//			if (StringUtil.equals(MsgType.DeviceEvent.BIND.toString(), event)) { // 绑定设备
//
//				log.debug("\n==============bind event==============");
//				log.debug("  DeviceType = " + message.getDeviceType());
//				log.debug("  OpenID     = " + message.getOpenID());
//				log.debug("  DeviceID   = " + deviceId);
//				log.debug("\n==============bind event==============");
//
//				// 绑定设备
//				ThreadPoolUtils.excuteCachedThreadPool(()->this.bindDevice(openId, deviceId));
//
//			} else if (StringUtil.equals(MsgType.DeviceEvent.UNBIND.toString(), event)) { // 解绑设备
//				
////				String openId = message.getOpenID();
////				asyncService.unbindDevcie(openId);
//				
//			} else if (StringUtil.equals(MsgType.DeviceEvent.SUBSCRIBE_STATUS.toString(), event)) { // 订阅设备状态
//				// 异步上传设备状态（wifi设备;jprs设备）
//				ThreadPoolUtils.excuteCachedThreadPool(()->deviceService.updataDeviceStatu(openId, deviceId));
//				// 创建公众号访问日志
//				mpAccessLogService.createLog(openId, MpLogTypeEnum.公众号设备订阅日志, message.getUrl());
//			}
//
//		}
//
//		log.debug("接收微信端消息耗时：" + ( System.currentTimeMillis() - startTime) + "毫秒，openid=" + openId + "\n");
//
//		// 直接返回""，微信服务器不会对此作任何处理，并且不会发起请求重试(重试三次)。
//
//		return replyContent;
//
//	}
//	
//	/**
//	 * 绑定设备
//	 * @param openId
//	 * @param deviceId
//	 */
//	private void bindDevice(String openId, String deviceId) {
//		try {
//			// 先删除缓存，从数据库中查询
//			DeviceCache deviceCache = new DeviceCache();
//			deviceCache.delete(deviceId);
//			Device device = deviceService.getDevice(Device.ID_KEY, deviceId);
//			if (null == device) {
//				String content = "系统中没有该设备[" + deviceId + "]";
//				// 微信扫一扫触发事件调用绑定设备,发送微信消息
//				MPSender.sendTextMessage(openId, content);
//				return;
//			};
//			
//			// 判断是否已经关注了公众号
//			Account account = accountService.getByOpenId(openId);
//			Boolean isFirstAdd = false;
//			if(account==null||StringUtil.isBlankOrNull(account.getId())){
//				isFirstAdd = true;
//			}
//			
//			// add by lyb 首次扫描时，对罗湖app绑定设备，这里做更新账号及绑定信息 begin
//			if(isFirstAdd){
//				boolean dealedFalg = luoHuAppService.dealAccountAndSendMeg(openId, device);
//				if(dealedFalg){// 如果处理好第三方设备的绑定关系了，则不用再去绑定设备了
//					return;
//				}
//			}
//			// add by lyb 首次扫描时，对罗湖app绑定设备，这里做更新账号及绑定信息 end
//			
//			// 如果绑定消息比关注消息先到，先处理帐号
//			accountService.saveAccount(openId);
//			// 及时提交
//			MyBatisUtil.commitSession();
//			
//			// 绑定设备
//			deviceService.bind(openId, device, DeviceBindEnum.正常绑定, 1, isFirstAdd);
//			
//			MyBatisUtil.commitSession();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			MyBatisUtil.closeSession();
//		}
//		
//	}
//
//
//	/**
//	 * 关注/取消关注事件
//	 * @param message
//	 */
//	private void subscribe(WechatMessage message) {
//
//		String event = message.getEvent();
//
//		String openId = message.getFromUserName();
//
//		// 关注事件
//		if (StringUtil.equals(MsgType.Event.SUBSCRIBE.toString(), event)) {
//
////			MPSender.sendTextMessage(openId, "欢迎使用乐心血压...");
//
//			// 异步调用,创建家庭帐号
//			ThreadPoolUtils.excuteCachedThreadPool(()->{
//				// 如果是扫描的设备，则让绑定设备先执行，在执行关注
//				try {
//					Thread.sleep(3000);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				accountService.subscribeWxServiceNo(openId);
//			});
//		}
//
//		// 取消关注事件
//		if (StringUtil.equals(MsgType.Event.UNSUBSCRIBE.toString(), event)) {
//			// 删除缓存
//			removeCacheData(openId);
//			
//			// 异步删除设备绑定关系，修改帐号状态
//			ThreadPoolUtils.excuteCachedThreadPool(()->accountService.unsubscribeWxServiceNo(openId));
//		}
//		
//	}
//	
//	/**
//	 * 自定义点击菜单事件
//	 * @param message
//	 */
//	private void clickMenu(WechatMessage message) {
//		
//		String eventKey = message.getEventKey();
//
//		String serviceNo = message.getToUserName();
//
//		String openId = message.getFromUserName();
//		
//		if (StringUtil.equalsIgnoreCase("ZXKF", eventKey)) { // 点击【咨询客服】菜单事件
//			BPSender.sendOnChat(serviceNo, openId, "请在微信聊天框进行咨询或拨打客服热线：400-600-2323(上班时间：周一至周五8:00-17:30)");
//		}
//		
//		if (StringUtil.equalsIgnoreCase("BPRISKREPORT", eventKey)) { // 点击【高血压评估】菜单事件(周报)
//			ThreadPoolUtils.excuteCachedThreadPool(()->accountService.sendBpRiskReportMessage(serviceNo, openId));
//		}
//		
//	}
//	
//	/**
//	 * 扫描带参数二维码事件
//	 * 
//	 * @param message
//	 */
//	private void scan(WechatMessage message) {
//
//		String event = message.getEvent();
//
//		// 微信用户openid
//		String openId = message.getFromUserName();
//
//		System.out.println("\n ============= scan ============");
//		
//		// 扫描带参数二维码事件：2.用户已关注时的事件推送
//
//		// 获得带参数二维码的场景ID
//		String qrsceneId = this.getQrsceneId(message);
//		
//		if (qrsceneId.startsWith(QrcodePrefixEnum.乐心电子血压计升级到乐心健康.prefix)) {// 乐心电子血压计升级到乐心血压
//			ThreadPoolUtils.excuteCachedThreadPool(()-> accountService.upgreadHealthcenter(qrsceneId, openId));
//			return;
//		}
//
//		// 扫描带参数二维码事件：1.用户未关注时，进行关注后的事件推送
//		if (StringUtil.equalsIgnoreCase(MsgType.Event.SUBSCRIBE.toString(), event)) {
//
//			/*
//			 * 由于需要关联医生及共享，需要先创建帐号，所有不能异步调用,创建家庭帐号;
//			 * 因为医生患者关联关系在这里还没有确定，先不通知app端某某患者患者已经重新关注公众号
//			 */
//			accountService.subscribeWxServiceNo(openId);
//			MyBatisUtil.commitSession();
//		}
//
//		
//		
//		log.debug("====qrsceneId==="+qrsceneId);
//
//		if (StringUtil.isBlank(qrsceneId)) {
//			return;
//		}
//
//		if(qrsceneId.startsWith(QrcodePrefixEnum.社区医生患者.prefix)){// 社区医生患者带参数二维码
//			ThreadPoolUtils.excuteCachedThreadPool(()-> memberService.modeCommnnityMember(qrsceneId, openId));
//		} else if (qrsceneId.length() == 10) {// 如果是10位，肯定是分享成员给朋友
//			ThreadPoolUtils.excuteCachedThreadPool(()-> accountService.acceptSharedMember(qrsceneId, openId));
//		} else if(qrsceneId.startsWith(QrcodePrefixEnum.家庭医生名片.prefix)
//				||qrsceneId.startsWith(QrcodePrefixEnum.社区医生名片.prefix)){
//			// 建立医生患者关联关系，并通知以前关联的医生：xx已经重新关注公众号了
//			String doctroId = qrsceneId.replace(QrcodePrefixEnum.家庭医生名片.prefix, "")
//					.replace(QrcodePrefixEnum.社区医生名片.prefix, "");
//			ThreadPoolUtils.excuteCachedThreadPool(()-> doctorService.addPatientRelationByLifesenseId(doctroId, openId));
//		}
//			
//	}
//	
//
//	/**
//	 * 获得带参数二维码的场景ID（如:医生名片、市场推广二维码）（获取永久二维码场景值）
//	 * @param message
//	 * @return
//	 */
//	private String getQrsceneId(WechatMessage message) {
//		String evnentKey = message.getEventKey();
//	    String ticket = message.getTicket();
//	    String qrsceneId = null;
//	    // 如果存在ticket和eventKey，用户扫描的带参数的二维码。（如:医生名片、市场推广二维码）
//	    if(StringUtil.isNotBlank(evnentKey)&&StringUtil.isNotBlank(ticket)){
//	    	qrsceneId = evnentKey.replace("qrscene_", "");
//	    }
//		return qrsceneId;
//	}
//	
//	 /**
//     * 删除缓存数据
//     * 
//     * @param serviceNo
//     *            公众号ID
//     * @param openId
//     *            用户openid
//     */
//    private void removeCacheData(String openId) {
//        CachedClient.remove(generateSessionId(openId));
//    }
//
//    /**
//     * 生成sessionId = md5(wxServiceNo+openId)
//     * 
//     * @param openId
//     * @return
//     */
//    private static String generateSessionId(String openId) {
//        return MD5Utils.getMD5ofStr(WechartServiceNoService.getServiceNo() + openId);
//    }
//	
//    
//    /**
//	 * 处理多客服事件
//	 * 
//	 * @param message
//	 */
//	private void dealCustomerEvent(WechatMessage message) {
//		String serviceNo = message.getToUserName();
//		String event = message.getEvent();
//		String openId = message.getFromUserName();
//		
//		if(StringUtil.equals(MsgType.Event.KF_CLOSE_SESSION,event)){// 结束会话
//			WXCustomerMessge.removeWXCustomerMessge(openId);
//			String content = "本次会话结束，有其他问题可以再找我。";
//			MPSender.sendTextMessage(openId, content);
//			return;
//		}
//		
//		if(StringUtil.equals(MsgType.Event.KF_CREATE_SESSION,event)){// 结束会话
//			WXCustomerMessge.writeCache(serviceNo, openId, true);
//			return;
//		}
//		
//	}
//}
