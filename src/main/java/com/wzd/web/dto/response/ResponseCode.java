package com.wzd.web.dto.response;

public enum ResponseCode implements ResponseCodeType {

	成功(200, "成功"),

	错误请求(400, "错误请求"),

	未授权(401, "未授权"),

	要求付款(402, "要求付款"),

	禁止访问(403, "禁止访问"),

	找不到路径(404, "找不到路径"),

	不允许此方法(405, "不允许此方法"),

	不支持的媒体类型(415, "不支持的媒体类型"),

	不允许为空(418, "不允许为空"),

	不允许重复(419, "不允许重复"),

	资源不存在(420, "资源不存在"),

	错误JSON(499, "错误JSON"),

	服务器异常(500, "服务器异常"),

	未登录(600, "未登录"),

	数据签名错误(601, "数据签名错误"),

	登录超时(602, "登录超时"),

	用户不存在(603, "用户不存在"),

	密码错误(604, "密码错误"),

	导出失败(605, "导出失败"),

	生成Excel表格数据失败(606, "生成Excel表格数据失败"),

	加密失败(607, "加密失败"), 解密失败(608, "解密失败"), 类型错误(609, "类型错误"), 已经签到(610, "已经签到"), 已领完(611, "已领完");

	private int code;

	private String msg;

	ResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
