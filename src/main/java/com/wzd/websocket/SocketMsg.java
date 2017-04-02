package com.wzd.websocket;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.SocketType;

/**
 * webSocket消息体
 * 
 * @author WeiZiDong
 *
 */
public class SocketMsg {
	private String command;
	private Object data;

	public SocketMsg() {
		super();
	}

	public SocketMsg(Object data) {
		this(SocketType.通知, data);
	}

	public SocketMsg(SocketType command, Object data) {
		this.command = command.getValue();
		this.data = data;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
