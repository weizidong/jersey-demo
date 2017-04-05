package com.wzd.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.SocketType;
import com.wzd.service.SocketService;

@ServerEndpoint("/websocket/{sessionId}")
public class WebSocket {
	private static final Logger log = LogManager.getLogger(WebSocket.class);

	/**
	 * 收到客户端消息时触发
	 */
	@OnMessage
	public void onMessage(String message, @PathParam("sessionId") Integer sessionId, Session session) {
		log.debug("接到websocket：" + sessionId + "===>" + message);
		SocketService.push(JSON.parseObject(message, SocketMsg.class), session);
	}

	/**
	 * 打开连接时触发
	 */
	@OnOpen
	public void onOpen(@PathParam("sessionId") String sessionId, Session session) {
		log.debug("开启websocket：" + sessionId);
		SocketService.save(sessionId, session);
		SocketService.send(session, new SocketMsg(SocketType.开启, null));
	}

	/**
	 * 关闭连接时触发
	 */
	@OnClose
	public void onClose(@PathParam("sessionId") String sessionId, Session session) {
		log.debug("关闭websocket：" + sessionId);
		SocketService.clear(sessionId);
	}

	/**
	 * 异常时触发
	 */
	@OnError
	public void onError(@PathParam("sessionId") String sessionId, Throwable throwable, Session session) {
		log.error("websocket异常：" + sessionId + "===>" + throwable.getMessage());
		SocketService.send(session, new SocketMsg(SocketType.异常, throwable.getMessage()));
	}
}
