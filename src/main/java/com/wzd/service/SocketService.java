package com.wzd.service;

import java.io.IOException;
import java.util.Map;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.wzd.model.enums.SocketType;
import com.wzd.websocket.SocketMsg;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * webSocket业务处理
 * 
 * @author WeiZiDong
 *
 */
public class SocketService {
	private static final Logger log = LogManager.getLogger(SocketService.class);
	private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

	/**
	 * 处理客户端发过来的指令
	 */
	public static void push(SocketMsg msg, Session session) {
		switch (SocketType.parse(msg.getCommand())) {
		case 心跳:
			send(session, new SocketMsg(SocketType.心跳, null));
			break;
		case 数据:

			break;
		case 通知:

			break;
		default:
			break;
		}
	}

	/**
	 * 发送指令
	 */
	public static void send(String sessionId, SocketMsg msg) {
		try {
			Session session = sessionMap.get(sessionId);
			if (session != null) {
				String jsonMsg = JSON.toJSONString(msg);
				log.debug("发送websocket:" + jsonMsg);
				session.getBasicRemote().sendText(jsonMsg);
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 发送指令
	 */
	public static void send(Session session, SocketMsg msg) {
		try {
			if (session != null) {
				String jsonMsg = JSON.toJSONString(msg);
				log.debug("发送websocket:" + jsonMsg);
				session.getBasicRemote().sendText(jsonMsg);
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 保存连接
	 */
	public static void save(String sessionId, Session session) {
		if (sessionMap.containsKey(sessionId)) {
			sessionMap.replace(sessionId, session);
		} else {
			sessionMap.put(sessionId, session);
		}
	}

	/**
	 * 清理连接
	 */
	public static void clear(String sessionId) {
		sessionMap.remove(sessionId);
	}
}
