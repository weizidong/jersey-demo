package com.wzd.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.SocketType;
import com.wzd.service.SocketService;

@ServerEndpoint("/websocket/{userid}")
public class WebSocket {
	private static final Logger log = LogManager.getLogger(WebSocket.class);
	@Autowired
	private SocketService service;

	@OnMessage
	public void onMessage(String message, @PathParam("userid") Integer userid, Session session) throws IOException, InterruptedException {
		log.debug("接到websocket：", userid, "===>", message);
		service.push(JSON.parseObject(message, SocketMsg.class), session);
	}

	@OnOpen
	public void onOpen(@PathParam("userid") String userid, Session session) {
		log.debug("开启websocket：", userid);
		service.save(userid, session);
		service.send(session, new SocketMsg(SocketType.开启.getValue(), null));
	}

	@OnClose
	public void onClose(@PathParam("userid") String userid, Session session) {
		log.debug("关闭websocket：", userid);
		service.clear(userid);
		service.send(session, new SocketMsg(SocketType.开启.getValue(), null));
	}
}
