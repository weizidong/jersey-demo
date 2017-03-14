package com.wzd.service.wechat.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;


/**
 * 来自Wechat端的消息(所有字段)
 */
@SuppressWarnings("serial")
public class WechatMessage implements Serializable {
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

	/** ==========文本消息内容 **/
	private String Content = "";

	/** ==========图片链接 **/
	private String PicUrl;

	/**
	 * 媒体文件ID
	 */
	private String MediaId;

	/** ==========VOICE **/
	private String Format;
	private String Recognition;

	/** ==========VIDEO **/
	private String ThumbMediaId;

	/** ==========LOCATION坐标 **/
	private double Location_X;
	private double Location_Y;
	private int Scale;
	private String Label;

	/** ==========LINK **/
	private String Title;
	private String Description;
	private String Url;

	/** ==========扫描二维码 **/
	private String Ticket;

	/** ==========地理位置 **/
	private double Latitude; // 地理位置纬度

	private double Longitude; // 地理位置经度

	private double Precision; // 地理位置精度

	/**
	 * 消息id
	 */
	private String MsgID;

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

	/** ==========文本消息内容 密文 **/
	private String Encrypt;

	/**
	 * 粉丝数
	 */
	private int TotalCount = 0;

	/**
	 * 过滤后，准备发送的粉丝数
	 */
	private int FilterCount = 0;

	/**
	 * 发送成功的粉丝数
	 */
	private int SentCount = 0;

	/**
	 * 发送失败的粉丝数
	 */
	private int ErrorCount = 0;

	private String KfAccount = "";

	private int OpType = 0;

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

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		try {
			String result = new String(content.toString().getBytes("UTF-8"));
			this.Content = result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.Content = content;
		}
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

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public double getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(double location_X) {
		Location_X = location_X;
	}

	public double getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(double location_Y) {
		Location_Y = location_Y;
	}

	public int getScale() {
		return Scale;
	}

	public void setScale(int scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
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

	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
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

	public String getEncrypt() {
		return Encrypt;
	}

	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public int getFilterCount() {
		return FilterCount;
	}

	public void setFilterCount(int filterCount) {
		FilterCount = filterCount;
	}

	public int getSentCount() {
		return SentCount;
	}

	public void setSentCount(int sentCount) {
		SentCount = sentCount;
	}

	public int getErrorCount() {
		return ErrorCount;
	}

	public void setErrorCount(int errorCount) {
		ErrorCount = errorCount;
	}

	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}

	public int getOpType() {
		return OpType;
	}

	public void setOpType(int opType) {
		OpType = opType;
	}

	@Override
	public String toString() {
		return "WechatMessage [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", Content="
				+ Content + ", PicUrl=" + PicUrl + ", MediaId=" + MediaId
				+ ", Format=" + Format + ", Recognition=" + Recognition
				+ ", ThumbMediaId=" + ThumbMediaId + ", Location_X="
				+ Location_X + ", Location_Y=" + Location_Y + ", Scale="
				+ Scale + ", Label=" + Label + ", Title=" + Title
				+ ", Description=" + Description + ", Url=" + Url + ", Ticket="
				+ Ticket + ", Latitude=" + Latitude + ", Longitude="
				+ Longitude + ", Precision=" + Precision + ", MsgID=" + MsgID
				+ ", MsgType=" + MsgType + ", Event=" + Event + ", EventKey="
				+ EventKey + ", SessionID=" + SessionID + ", OpenID=" + OpenID
				+ ", DeviceType=" + DeviceType + ", DeviceID=" + DeviceID
				+ ", Status=" + Status + ", Encrypt=" + Encrypt
				+ ", TotalCount=" + TotalCount + ", FilterCount=" + FilterCount
				+ ", SentCount=" + SentCount + ", ErrorCount=" + ErrorCount
				+ ", KfAccount=" + KfAccount + ", OpType=" + OpType + "]";
	}

	
}
