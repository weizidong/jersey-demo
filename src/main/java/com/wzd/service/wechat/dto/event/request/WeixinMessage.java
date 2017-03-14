package com.wzd.service.wechat.dto.event.request;

import java.io.Serializable;

/**
 * 来自Wechat端的消息(所有字段)
 * 
 * @author juzhi
 * 
 */
@SuppressWarnings("serial")
public class WeixinMessage implements Serializable {

	private String URL;

	/**
	 * 微信公众号ID
	 */
	private String ToUserName;

	/**
	 * 发送方微信号(OpenID)
	 */
	private String FromUserName;

	/**
	 * 消息创建时间 
	 */
	private long CreateTime;
	
	/**==========文本消息内容**/
	private String Content;

	
	/**==========图片链接**/
	private String PicUrl;
	
	/**
	 * 媒体文件ID
	 */
	private String MediaId;
	
	
	/**==========扫描二维码**/
	private String Ticket;
	
	/**==========地理位置**/
	private double Latitude; // 地理位置纬度 
	
	private double Longitude; // 地理位置经度
	
	private double Precision; // 地理位置精度

	/**
	 * 消息id
	 */
	private long MsgID;
	
	/**
	 * 消息类型
	 */
	private String MsgType;
	
	/**
	 * 事件
	 */
	private String Event;
	
	/**
	 * 事件key
	 */
	private String EventKey;
	
	/**
	 * 微信客户端生成的id
	 */
	private String SessionID;
	
	/**
	 * 普通微信用户的openid
	 */
	private String OpenID;
	
	/**
	 * 请求类型：0-退订设备状态；1-心跳（心跳的处理方式跟订阅一样）；2-订阅设备状态
	 */
	private String OpType;
	
	/**
	 * 设备类型，目前即为微信公众号ID
	 */
	private String DeviceType;

	/**
	 * 设备ID
	 */
	private String DeviceID;
	
	
	/**
	 * 状态
	 */
	private String Status;

	/**
	 * 语音格式：amr
	 */
	private String Format;
	
	/**
	 * 语音识别结果，UTF8编码 
	 */
	private String Recognition;
	
	public WeixinMessage() {
		
	}
	
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getToUserName() {
//		return toUserName;
//	}
//
//	public void setToUserName(String toUserName) {
//		this.toUserName = toUserName;
//	}
//
//	public String getFromUserName() {
//		return fromUserName;
//	}
//
//	public void setFromUserName(String fromUserName) {
//		this.fromUserName = fromUserName;
//	}
//
//	public long getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(long createTime) {
//		this.createTime = createTime;
//	}
//
//	public String getContent() {
//		return content;
//	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getPrecision() {
		return Precision;
	}

	public void setPrecision(double precision) {
		Precision = precision;
	}

	public long getMsgID() {
		return MsgID;
	}

	public void setMsgID(long msgID) {
		MsgID = msgID;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

	public String getOpenID() {
		return OpenID;
	}

	public void setOpenID(String openID) {
		OpenID = openID;
	}

	public String getOpType() {
		return OpType;
	}

	public void setOpType(String opType) {
		OpType = opType;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

//	public long getMsgID() {
//		return msgID;
//	}
//
//	public void setMsgID(long msgID) {
//		this.msgID = msgID;
//	}
//
//	public String getPicUrl() {
//		return picUrl;
//	}
//
//	public void setPicUrl(String picUrl) {
//		this.picUrl = picUrl;
//	}
//
//	public String getMediaId() {
//		return mediaId;
//	}
//
//	public void setMediaId(String mediaId) {
//		this.mediaId = mediaId;
//	}
//
//	public String getTicket() {
//		return ticket;
//	}
//
//	public void setTicket(String ticket) {
//		this.ticket = ticket;
//	}
//
//	public double getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}
//
//	public double getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(double longitude) {
//		this.longitude = longitude;
//	}
//
//	public double getPrecision() {
//		return precision;
//	}
//
//	public void setPrecision(double precision) {
//		this.precision = precision;
//	}
//
//	public String getMsgType() {
//		return msgType;
//	}
//
//	public void setMsgType(String msgType) {
//		this.msgType = msgType;
//	}
//
//	public String getEvent() {
//		return event;
//	}
//
//	public void setEvent(String event) {
//		this.event = event;
//	}
//
//	public String getEventKey() {
//		return eventKey;
//	}
//
//	public void setEventKey(String eventKey) {
//		this.eventKey = eventKey;
//	}
//
//	public String getSessionID() {
//		return sessionID;
//	}
//
//	public void setSessionID(String sessionID) {
//		this.sessionID = sessionID;
//	}
//
//	public String getOpenID() {
//		return openID;
//	}
//
//	public void setOpenID(String openID) {
//		this.openID = openID;
//	}
//
//	public String getDeviceType() {
//		return deviceType;
//	}
//
//	public void setDeviceType(String deviceType) {
//		this.deviceType = deviceType;
//	}
//
//	public String getDeviceID() {
//		return deviceID.toLowerCase();
//	}
//
//	public void setDeviceID(String deviceID) {
//		this.deviceID = deviceID;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getFormat() {
//		return format;
//	}
//
//	public void setFormat(String format) {
//		this.format = format;
//	}
//
//	public String getRecognition() {
//		return recognition;
//	}
//
//	public void setRecognition(String recognition) {
//		this.recognition = recognition;
//	}
//
//	public String getOpType() {
//		return opType;
//	}
//
//	public void setOpType(String opType) {
//		this.opType = opType;
//	}
	
}
