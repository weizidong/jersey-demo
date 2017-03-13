package com.lifesense.healthcenter.service.wechat.dto.device_text.enums;

import com.lifesense.healthcenter.utils.StringUtil;


/**
 * 蓝牙设备命令
 * @author linjinyu
 *
 */
public enum CommandEnum {

	
	CMD0x70发送血糖仪设备信息到乐心服务器("70"),
	CMD0x71发送血糖仪测量数据到乐心服务器("71"),
	
	CMD0xC0发送请求DeviceTypeDeviceIdManufactureData等信息("C0"),
	CMD0xC1发送请求UTC到乐心服务器("C1"),
	CMD0xC2发送身高测量数据到乐心服务器("C2"),
	CMD0xC3发送秤测量数据到乐心服务器("C3"),
	CMD0xC4发送计步器测量数据到乐心服务器("C4"),
	CMD0xC5发送请求计步器用户信息到乐心服务器("C5"),
	CMD0xC6发送血压计测量数据到乐心服务器("C6"),
	CMD0xC7发送请求计步器下载信息到乐心服务器("C7"),
	CMD0xC8发送产品烧码成功("C8"),
	CMD0xC9发送计步器测量数据到乐心服务器("C9"),
	CMD0xCA发送计步器每天的测量数据到乐心服务器("CA"),
	CMD0xCB发送计步器push用户信息确认到乐心服务器("CB"),
	CMD0xCC发送秤设备信息到乐心服务器("CC"),
	CMD0xCD发送血压计设备信息到乐心服务器("CD"),
	CMD0xCE发送计步器睡眠数据到乐心服务器("CE"),
	CMD0xCF发送计步器配对确认到乐心服务器("CF");
	
	

	private CommandEnum(String cmd ){
		this.cmd = cmd;
	}
	
	private String cmd;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public static CommandEnum getCmd(String cmd) {
		for (CommandEnum item : CommandEnum.values()) {
			if (StringUtil.equals(item.getCmd(), cmd)) {
				return item;
			}
		}

		throw new RuntimeException("值[" + cmd + "]不是" + CommandEnum.class
				+ "有效值。");
	}
	
}
