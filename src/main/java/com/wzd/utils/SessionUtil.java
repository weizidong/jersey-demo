//package com.wzd.utils;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.logging.log4j.Logger;
//
//import com.lifesense.communityhospital.model.entity.authority.Account;
//import com.lifesense.communityhospital.model.entity.doctor.CommunityDoctor;
//import com.lifesense.communityhospital.service.doctor.CommunityDoctorService;
//import com.lifesense.utils.redis.JedisUtil;
//import com.lifesense.utils.redis.cmd.JedisHash;
//import com.wzd.model.enums.RoleType;
//import com.wzd.web.dto.exception.WebException;
//import com.wzd.web.dto.response.ResponseCode;
//import com.wzd.web.dto.session.LoginSession;
//import com.wzd.web.dto.session.Session;
//import com.wzd.web.filter.UrlFilter;
//
//import tk.mybatis.mapper.util.StringUtil;
//
///**
// * session工具类
// * 
// * @author WeiZiDong
// *
// */
//public class SessionUtil {
//	private static final Logger log = Logger.getLogger(SessionUtil.class);
//
//	private static final String SESSION_KEY = Session.class.getName() + UrlFilter.getVersion();
//
//	private static final String SESSION_ID = "session_id";
//
//	private static final String ACCESS_TOKEN = "access_token";
//
//	private static final Integer COOKIE_MAX_AGE = 3600 * 24 * 7;
//
//	private static JedisHash<LoginSession> sessionMap = JedisUtil.hash(SESSION_KEY, LoginSession.class);
//
//	/**
//	 * 从cookie中获取SessionId
//	 */
//	public static String getSessionIdByCookie(HttpServletRequest request) {
//		return CookieUtil.getCookieValue(request, SESSION_ID);
//	}
//
//	/**
//	 * 从cookie中获取用户信息缓存的key
//	 * 
//	 * 再从缓存中获取用户信息 val : 是否需要验证
//	 */
//	public static LoginSession getSession(HttpServletRequest request) {
//		try{
//			LoginSession session = null;
//			// 先判断request中否存在，存在既返回
//			Object sessionRequestAttr = request.getAttribute(SESSION_ID);
//			if (sessionRequestAttr != null) {
//				session = (LoginSession) sessionRequestAttr;
//				log.debug("从request中获取到的userInfo = " + session);
//				return session;
//			}
//			String sessionId = getSessionIdByCookie(request);
//			log.debug("从cookies中获取到的sessionId = " + sessionId);
//			if (StringUtil.isEmpty(sessionId)) {
//				return null;
//			}
//			session = sessionMap.get(sessionId);
//			log.debug("从jedis中获取到的userInfo = " + session);
//			// 如果超时返回空
//			if (session != null && System.currentTimeMillis() - session.getTs() > COOKIE_MAX_AGE * 1000) {
//				log.debug("缓存超时，移除userInfo");
//				sessionMap.delete(sessionId);
//				return null;
//			}
//			// 数据签名不匹配，返回空
//			if (session != null && !checkSignature(request, session)) {
//				log.debug("数据签名错误，移除userInfo");
//				sessionMap.delete(sessionId);
//				return null;
//			}
//			return session;
//		} finally {
//			JedisUtil.close();
//		}
//	}
//
//	/**
//	 * 验证数字签名
//	 */
//	private static boolean checkSignature(HttpServletRequest request, LoginSession session) {
//		String nonce = request.getParameter("nonce");
//		String signature = request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		if (StringUtil.isEmpty(nonce) || StringUtil.isEmpty(signature) || StringUtil.isEmpty(timestamp)) {
//			return false;
//		}
//		String url = request.getRequestURI();
//		String token = MD5Utils.getMD5ofStr(session.getAccessToken() + url);
//		return SignatureUtil.checkSignature(token, signature, timestamp, nonce);
//	}
//
//	/**
//	 * 获取Session的doctor
//	 */
//	private static CommunityDoctorService service = new CommunityDoctorService();
//
//	public static CommunityDoctor getDoctor(HttpServletRequest request) {
//		LoginSession session = getSession(request);
//		if (session == null) {
//			throw new WebException(ResponseCode.未授权, "未登录");
//		}
//		CommunityDoctor doctor = session.getDoctor();
//		// Long time = System.currentTimeMillis() - session.getTs();
//		if (doctor == null) {
//			log.debug("更新session：" + session);
//			doctor = service.getLoginDoctor(session);
//			session.setDoctor(doctor);
//			session.setTs(System.currentTimeMillis());
//			// 添加到request
//			request.setAttribute(SESSION_ID, session);
//			// 写入缓存
//			try {
//				sessionMap.set(session.getSessionId(), session);
//			} finally {
//				JedisUtil.close();
//			}
//		}
//		return doctor;
//	}
//
//	/**
//	 * 获取当前登陆的账号
//	 */
//	public static Account getAccount(HttpServletRequest request) {
//		LoginSession session = getSession(request);
//		if (session == null) {
//			throw new WebException(ResponseCode.未授权, "未登录");
//		}
//		Account account = session.getAccount();
//		if (account == null) {
//			throw new WebException(ResponseCode.未授权, "当前登陆的不是管理员");
//		}
//		return account;
//	}
//
//	/**
//	 * 获取当前登陆账号的权限
//	 */
//	public static List<RoleType> getRoles(HttpServletRequest request) {
//		LoginSession session = getSession(request);
//		if (session == null) {
//			throw new WebException(ResponseCode.未授权, "未登录");
//		}
//		int[] roles = session.getRole();
//		if (roles == null || roles.length == 0) {
//			throw new WebException(ResponseCode.未授权, "权限不足");
//		}
//		return Arrays.stream(roles).mapToObj((code) -> RoleType.parse(code)).collect(Collectors.toList());
//	}
//
//	/**
//	 * 保存用户信息
//	 */
//	public static String saveSession(LoginSession session, HttpServletRequest request, HttpServletResponse response) {
//		// 写入cookie
//		String sessionId = session.getSessionId();
//		String token = session.getAccessToken();
//		// 写入自动登陆
//		// if (session.isAutologin()) {
//		// CookieUtil.setCookie(SESSION_ID, sessionId, COOKIE_MAX_AGE, request,
//		// response);
//		// CookieUtil.setCookie(ACCESS_TOKEN, token, COOKIE_MAX_AGE, request,
//		// response);
//		// } else {
//		CookieUtil.setCookie(SESSION_ID, sessionId, -1, request, response);
//		CookieUtil.setCookie(ACCESS_TOKEN, token, -1, request, response);
//		// }
//		// 设置新的session
//		session.setTs(System.currentTimeMillis());
//		// 添加到request
//		request.setAttribute(SESSION_ID, session);
//		// 写入缓存
//		try {
//			sessionMap.set(sessionId, session);
//		} finally {
//			JedisUtil.close();
//		}
//		return sessionId;
//	}
//
//	/**
//	 * 删除缓存数据
//	 */
//	public static void removeCacheData(String sessionId, HttpServletRequest request, HttpServletResponse response) {
//		try{
//			sessionMap.delete(sessionId);
//			CookieUtil.deleteCookie(request, response);
//		} finally {
//			JedisUtil.close();
//		}
//	}
//
//}
