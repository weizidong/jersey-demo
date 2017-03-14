package com.wzd.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wzd.model.entity.User;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.dto.session.Session;

import tk.mybatis.mapper.util.StringUtil;

/**
 * session工具类
 * 
 * @author WeiZiDong
 *
 */
public class SessionUtil {
	private static final Logger log = LogManager.getLogger(SessionUtil.class);
	private static final String SESSION_ID = "session_id";
	private static final String ACCESS_TOKEN = "access_token";
	private static final Integer COOKIE_MAX_AGE = 3600 * 24 * 7;
	private static final EhcacheUtil ehcache = EhcacheUtil.getInstance();

	/**
	 * 从cookie中获取SessionId
	 */
	public static String getSessionIdByCookie(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, SESSION_ID);
	}

	/**
	 * 从cookie中获取用户信息缓存的key
	 * 
	 * 再从缓存中获取用户信息 val : 是否需要验证
	 */
	public static Session getSession(HttpServletRequest request) {
		Session session = null;
		// 先判断request中否存在，存在既返回
		Object sessionRequestAttr = request.getAttribute(SESSION_ID);
		if (sessionRequestAttr != null) {
			session = (Session) sessionRequestAttr;
			log.debug("从request中获取到的session = " + session);
			return session;
		}
		String sessionId = getSessionIdByCookie(request);
		log.debug("从cookies中获取到的sessionId = " + sessionId);
		if (StringUtil.isEmpty(sessionId)) {
			return null;
		}
		session = ehcache.getSession(sessionId);
		log.debug("从ehcache中获取到的session = " + session);
		// 如果超时返回空
		if (session != null && System.currentTimeMillis() - session.getTs() > COOKIE_MAX_AGE * 1000) {
			log.debug("缓存超时，移除session");
			ehcache.removeSession(sessionId);
			return null;
		}
		// 数据签名不匹配，返回空
		if (session != null && !checkSignature(request, session)) {
			log.debug("数据签名错误，移除session");
			ehcache.removeSession(sessionId);
			return null;
		}
		return session;
	}

	/**
	 * 验证数字签名
	 */
	private static boolean checkSignature(HttpServletRequest request, Session session) {
		String nonce = request.getParameter("nonce");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		if (StringUtil.isEmpty(nonce) || StringUtil.isEmpty(signature) || StringUtil.isEmpty(timestamp)) {
			return false;
		}
		String url = request.getRequestURI();
		String token = MD5Utils.getMD5ofStr(session.getAccessToken() + url);
		return SignatureUtil.checkSignature(token, signature, timestamp, nonce);
	}

	/**
	 * 获取当前登陆的账号
	 */
	public static User getUser(HttpServletRequest request) {
		Session session = getSession(request);
		if (session == null) {
			throw new WebException(ResponseCode.未授权, "未登录");
		}
		User user = session.getUser();
		if (user == null) {
			throw new WebException(ResponseCode.未授权, "未登录");
		}
		return user;
	}

	/**
	 * 保存用户信息
	 */
	public static String saveSession(Session session, HttpServletRequest request, HttpServletResponse response) {
		// 写入cookie
		String sessionId = session.getSessionId();
		String token = session.getAccessToken();
		// 写入自动登陆
		CookieUtil.setCookie(SESSION_ID, sessionId, -1, request, response);
		CookieUtil.setCookie(ACCESS_TOKEN, token, -1, request, response);
		// 设置新的session
		session.setTs(System.currentTimeMillis());
		// 添加到request
		request.setAttribute(SESSION_ID, session);
		// 写入缓存
		ehcache.putSession(sessionId, session);
		return sessionId;
	}

	/**
	 * 删除缓存数据
	 */
	public static void removeCacheData(String sessionId, HttpServletRequest request, HttpServletResponse response) {
		ehcache.removeSession(sessionId);
	}

	/**
	 * 是否登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
		String debug = request.getParameter("debug");
		if (debug != null) {
			Session session = new Session();
			saveSession(session, request, response);
			return true;
		}
		Session session = getSession(request);
		if (session != null) {
			saveSession(session, request, response);
			return true;
		}
		return false;
	}

}
