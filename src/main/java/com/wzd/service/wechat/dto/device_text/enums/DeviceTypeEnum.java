package com.lifesense.healthcenter.service.wechat.dto.device_text.enums;

import com.lifesense.healthcenter.utils.StringUtil;

/**
 * 设备类型
 */
public enum DeviceTypeEnum {

	体重秤("01", "view/device/user?deviceType=01&device_id={0}"), 
	手环("04", "view/device/user?deviceType=04&device_id={0}"), 
	血糖仪("06", "view/device/user?deviceType=06&device_id={0}"),
	血压计("08", "view/device/user?deviceType=08&device_id={0}");


	private String code;
	private String bindHtmlurl;

	private DeviceTypeEnum(String code, String bindHtmlurl) {
		this.code = code;
		this.bindHtmlurl = bindHtmlurl;
	}

	public String getCode() {
		return code;
	}

	public String getBindHtmlurl() {
		return bindHtmlurl;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static DeviceTypeEnum getDeviceType(String code) {
		for (DeviceTypeEnum item : DeviceTypeEnum.values()) {
			if (StringUtil.equals(item.getCode(), code)) {
				return item;
			}
		}

		throw new RuntimeException("值[" + code + "]不是" + DeviceTypeEnum.class
				+ "有效值。");
	}
}
