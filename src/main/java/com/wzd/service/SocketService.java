package com.wzd.service;

import java.io.IOException;
import java.util.Map;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;

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
@Service
public class SocketService {
	private static final Logger log = LogManager.getLogger(SocketService.class);
	private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

	/**
	 * 处理客户端发过来的指令
	 */
	public void push(SocketMsg msg, Session session) {
		switch (SocketType.parse(msg.getCommand())) {
		case 心跳:
			send(session, new SocketMsg(SocketType.心跳.getValue(), null));
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
	public void send(String userid, SocketMsg msg) {
		try {
			Session session = sessionMap.get(userid);
			if (session != null) {
				session.getBasicRemote().sendText(JSON.toJSONString(msg));
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 发送指令
	 */
	public void send(Session session, SocketMsg msg) {
		try {
			if (session != null) {
				session.getBasicRemote().sendText(JSON.toJSONString(msg));
			}
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * 保存连接
	 */
	public void save(String userid, Session session) {
		if (sessionMap.containsKey(userid)) {
			sessionMap.replace(userid, session);
		} else {
			sessionMap.put(userid, session);
		}
	}

	/**
	 * 清理连接
	 */
	public void clear(String userid) {
		sessionMap.remove(userid);
	}
}
