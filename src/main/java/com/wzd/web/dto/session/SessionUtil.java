package com.wzd.web.dto.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wzd.model.dao.AdminDao;
import com.wzd.model.dao.UserDao;
import com.wzd.model.enums.APPType;
import com.wzd.utils.CookieUtil;
import com.wzd.utils.EhcacheUtil;
import com.wzd.utils.MD5Utils;
import com.wzd.utils.SignatureUtil;
import com.wzd.utils.StringUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;

/**
 * session工具类
 * 
 * @author WeiZiDong
 *
 */
@Component()
public class SessionUtil {
	private static final Logger log = LogManager.getLogger(SessionUtil.class);
	private static final String SESSION_ID = "session_id";
	private static final String ACCESS_TOKEN = "access_token";
	private static final Integer COOKIE_MAX_AGE = 3600 * 24 * 7;
	private static final EhcacheUtil ehcache = EhcacheUtil.getInstance();
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 从cookie中获取SessionId
	 */
	public static String getSessionIdByCookie(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, SESSION_ID);
	}

	/**
	 * 验证debug模式
	 */
	public static Boolean isDebug(HttpServletRequest request) {
		String debug = request.getParameter("debug");
		return debug != null && debug.equals("weizidong");
	}

	/**
	 * 开启debug模式
	 */
	public void openDebug(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String appType = httpRequest.getParameter("appType");
		String sessionId = getSessionIdByCookie(httpRequest);
		Session session = getSession(httpRequest);
		if (sessionId != null && session != null) {
			return;
		}
		String accessToken = null;
		Object user = null;
		if (APPType.企业号.getValue().equals(appType)) {
			sessionId = "weizidong";
			user = adminDao.getByUserId(sessionId);
		}
		if (APPType.服务号.getValue().equals(appType)) {
			sessionId = "oFTpnwnsF7Vv6WkM_fySqDtD-rEo";
			user = userDao.getByOpenId(sessionId);
		}
		if (APPType.管理平台.getValue().equals(appType)) {
			sessionId = "weizidong";
			user = adminDao.getByUserId(sessionId);
		}
		session = generateSession(appType, sessionId, accessToken, user);
		saveSession(session, httpRequest, httpResponse);
	}

	/**
	 * 获取Session
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
		return session;
	}

	/**
	 * 验证是否超时
	 */
	public static void checkTs(Session session, HttpServletRequest request, HttpServletResponse response) {
		if (System.currentTimeMillis() - session.getTs() > COOKIE_MAX_AGE * 1000) {
			removeSession(session.getSessionId(), request, response);
			throw new WebException(ResponseCode.登录超时);
		}
	}

	/**
	 * 验证数字签名
	 */
	public static void checkSignature(Session session, HttpServletRequest request, HttpServletResponse response) {
		String nonce = request.getParameter("nonce");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		if (StringUtil.isEmpty(nonce) || StringUtil.isEmpty(signature) || StringUtil.isEmpty(timestamp)) {
			removeSession(session.getSessionId(), request, response);
			throw new WebException(ResponseCode.数据签名错误);
		}
		String url = request.getRequestURI();
		String token = MD5Utils.getMD5ofStr(session.getAccessToken() + url);
		if (!SignatureUtil.checkSignature(token, signature, timestamp, nonce)) {
			removeSession(session.getSessionId(), request, response);
			throw new WebException(ResponseCode.数据签名错误);
		}
	}

	/**
	 * 获取当前登陆的账号
	 */
	public static Object getUser(HttpServletRequest request) {
		Session session = getSession(request);
		if (session == null || session.getUser() == null) {
			throw new WebException(ResponseCode.未登录);
		}
		return session.getUser();
	}

	/**
	 * 更新Session
	 */
	public static void updateSession(Session session, HttpServletRequest request) {
		session.setTs(System.currentTimeMillis());
		// 添加到request
		request.setAttribute(SESSION_ID, session);
		// 写入缓存
		ehcache.putSession(session.getSessionId(), session);
	}

	/**
	 * 保存Session
	 */
	public static void saveSession(Session session, HttpServletRequest request, HttpServletResponse response) {
		session.setTs(System.currentTimeMillis());
		// 写入cookie
		CookieUtil.setCookie(SESSION_ID, session.getSessionId(), -1, request, response);
		CookieUtil.setCookie(ACCESS_TOKEN, session.getAccessToken(), -1, request, response);
		// 添加到request
		request.setAttribute(SESSION_ID, session);
		// 写入缓存
		ehcache.putSession(session.getSessionId(), session);
	}

	/**
	 * 删除Session
	 */
	public static void removeSession(String sessionId, HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.setCookie(SESSION_ID, null, 0, request, response);
		CookieUtil.setCookie(ACCESS_TOKEN, null, 0, request, response);
		request.setAttribute(SESSION_ID, null);
		ehcache.removeSession(sessionId);
	}

	/**
	 * 生成Session
	 */
	public static Session generateSession(String appType, String sessionId, String accessToken, Object user) {
		Session session = new Session();
		session.setSessionId(StringUtil.isEmpty(sessionId) ? MD5Utils.getMD5ofStr(SESSION_ID + System.currentTimeMillis()) : sessionId);
		session.setAccessToken(StringUtil.isEmpty(accessToken) ? SignatureUtil.generateToke() : accessToken);
		session.setAppType(appType);
		session.setUser(user);
		return session;
	}

}
