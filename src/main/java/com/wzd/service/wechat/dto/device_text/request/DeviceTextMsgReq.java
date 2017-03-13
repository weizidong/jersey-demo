package com.lifesense.healthcenter.service.wechat.dto.device_text.request;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseReqMsg;

/*
 * 
{
    "ToUserName": "gh_ef20d754a1a1", 
    "FromUserName": "onwakjqedRRELEgGN5grqm2AK75I", 
    "CreateTime": 1429252475, 
    "Content": "Qzc2MEMyOEFEMDg2RTkzNDMwMzc0MjMwNDEzMDMyMzI0MTMwMzAzMTA4", 
    "Latitude": 0, 
    "Longitude": 0, 
    "Precision": 0, 
    "MsgID": 204631464, 
    "MsgType": "device_text", 
    "SessionID": "476654", 
    "OpenID": "onwakjqedRRELEgGN5grqm2AK75I", 
    "DeviceType": "gh_ef20d754a1a1", 
    "DeviceID": "4A041001B863"
    
    "Latitude": 0, 
    "Longitude": 0, 
    "Precision": 0, 
}
 */
public class DeviceTextMsgReq extends BaseReqMsg {

    private String DeviceType;
    private String DeviceID;
    
    private String OpenID;
    private String SessionID;
    
    private String Content;
    
    /** ==========地理位置 **/
    private double Latitude; // 地理位置纬度
	private double Longitude; // 地理位置经度
	private double Precision; // 地理位置精度


    public DeviceTextMsgReq() {
        setMsgType(MsgType.DEVICE_TEXT);
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


	public String getOpenID() {
		return OpenID;
	}


	public void setOpenID(String openID) {
		OpenID = openID;
	}


	public String getSessionID() {
		return SessionID;
	}


	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
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


	@Override
	public String toString() {
		return "DeviceText [DeviceType=" + DeviceType + ", DeviceID="
				+ DeviceID + ", OpenID=" + OpenID + ", SessionID=" + SessionID
				+ ", Content=" + Content + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + ", Precision=" + Precision
				+ ", msgId=" + msgId + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + "]";
	}
    
    

}
