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

@ServerEndpoint("/websocket/{userid}")
public class WebSocket {
	private static final Logger log = LogManager.getLogger(WebSocket.class);
	private SocketService service = new SocketService();

	/**
	 * 收到客户端消息时触发
	 */
	@OnMessage
	public void onMessage(String message, @PathParam("userid") Integer userid, Session session) {
		log.debug("接到websocket：" + userid + "===>" + message);
		service.push(JSON.parseObject(message, SocketMsg.class), session);
	}

	/**
	 * 打开连接时触发
	 */
	@OnOpen
	public void onOpen(@PathParam("userid") String userid, Session session) {
		log.debug("开启websocket：" + userid);
		service.save(userid, session);
		service.send(session, new SocketMsg(SocketType.开启.getValue(), null));
	}

	/**
	 * 关闭连接时触发
	 */
	@OnClose
	public void onClose(@PathParam("userid") String userid, Session session) {
		log.debug("关闭websocket：" + userid);
		service.clear(userid);
	}

	/**
	 * 异常时触发
	 */
	@OnError
	public void onError(@PathParam("userid") String userid, Throwable throwable, Session session) {
		log.error("websocket异常：" + userid + "===>" + throwable.getMessage());
		service.send(session, new SocketMsg(SocketType.异常.getValue(), throwable.getMessage()));
	}
}
