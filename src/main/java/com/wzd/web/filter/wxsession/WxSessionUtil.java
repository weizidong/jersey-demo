//package com.wzd.web.filter.wxsession;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.lifesense.framework.util.StringUtil;
//import com.lifesense.framework.util.log.LoggerAdapter;
//import com.lifesense.framework.util.log.LoggerAdapterFacory;
//import com.lifesense.healthcenter.model.dao.family.AccountDao;
//import com.lifesense.healthcenter.model.entity.family.Account;
//import com.lifesense.healthcenter.service.wechat.WechartServiceNoService;
//import com.lifesense.healthcenter.service.wechat.oauth.dto.AccessToken;
//import com.lifesense.healthcenter.utils.CachedClient;
//import com.lifesense.healthcenter.utils.MD5Utils;
//import com.lifesense.healthcenter.web.utils.CookieUtil;
//
//public class WxSessionUtil {
//
//    private static LoggerAdapter log = LoggerAdapterFacory.getLogger(WxSessionUtil.class);
//
//    private static final String REQUEST_ATTR_FOR_CACHE_DATA = "__cacheData";
//
//    private static AccountDao accountDao = new AccountDao();
//
//    /**
//     * 从cookie中获取WxSessionId
//     * 
//     * @param request
//     * @return
//     */
//    public static String getWxSessionIdByCookie(HttpServletRequest request) {
//        return CookieUtil.getCookieValue(request, WxSessionConfig.getCookieName());
//    }
//
//    /**
//     * 从cookie中获取用户信息缓存的key
//     * 
//     * 再从缓存中获取用户信息
//     * 
//     * @param request
//     * @return
//     */
//    public static WxSession getWxSession(HttpServletRequest request) {
//
//        WxSession wxSession = null;
//
//        // 先判断request中否存在，存在既返回
//        Object wxSessionRequestAttr = request.getAttribute(REQUEST_ATTR_FOR_CACHE_DATA);
//
//        if (wxSessionRequestAttr != null) {
//            wxSession = (WxSession) wxSessionRequestAttr;
//            return wxSession;
//        }
//
//        // 是否开始debug，开启的话返回固定openId
//        if (isDebug(request)) {
//            log.debug("开启了debug模式。");
//            wxSession = generateDebugWxSession();
//
//            if (wxSession == null) {
//                return wxSession;
//            }
//
//            wxSession.setTs(System.currentTimeMillis());
//            return wxSession;
//        }
//
//        String wxSessionId = getWxSessionIdByCookie(request);
//        log.debug("从cookies中获取到的sessionId = " + wxSessionId);
//        if (StringUtil.isEmpty(wxSessionId)) {
//            wxSessionId = request.getHeader("sessionId");
//            log.debug("从header中获取到的sessionId = " + wxSessionId);
//        }
//        if (StringUtil.isEmpty(wxSessionId)) {
//            return null;
//        }
//
//        wxSession = (WxSession) CachedClient.get(wxSessionId);
//
//        log.debug("从cache中获取到的userInfo = " + wxSession);
//
//        // 如果超时返回空
//        if (wxSession != null && System.currentTimeMillis() - wxSession.getTs() > WxSessionConfig.getCookieMaxAge() * 1000) {
//
//            log.debug("缓存超时，移除userInfo");
//            CachedClient.remove(wxSessionId);
//            return null;
//        }
//
//        return wxSession;
//    }
//
//    /**
//     * 是否debug模式
//     * 
//     * @param request
//     * @return
//     */
//    private static boolean isDebug(HttpServletRequest request) {
//
//        if (WxSessionConfig.isDebug()) {
//            return true;
//        }
//
//        // 判断是否非微信内置浏览器发出的请求
//        String userAgent = request.getHeader("user-agent");
//        return !StringUtil.isEmpty(userAgent) && userAgent.toLowerCase().indexOf("micromessenger") < 0;
//    }
//
//    /**
//     * 获取WxSession的openId
//     * 
//     * @param request
//     * @return
//     */
//    public static String getOpenId(HttpServletRequest request) {
//    	long startTs = System.currentTimeMillis();
//    	log.debug("从request中获取openId");
//        WxSession wxSession = getWxSession(request);
//        if (wxSession == null) {
//            return null;
//        }
//        log.debug("从request中获取openId成功。" + (System.currentTimeMillis() - startTs));
//        return wxSession.getOpenId();
//    }
//
//    /**
//     * 获取WxSession的accountId
//     * 
//     * @param request
//     * @return
//     */
//    public static String getAccountId(HttpServletRequest request) {
//    	long startTs = System.currentTimeMillis();
//    	log.debug("从request中获取accountId");
//        WxSession wxSession = getWxSession(request);
//        if (wxSession == null) {
//            return null;
//        }
//        log.debug("从request中获取accountId成功。" + (System.currentTimeMillis() - startTs));
//        return wxSession.getAccountId();
//    }
//
//    /**
//     * 更新用户信息
//     * 
//     * @param wxSession
//     * @param request
//     * @param response
//     */
//    public static String saveWxSession(WxSession wxSession, HttpServletRequest request, HttpServletResponse response) {
//
//    	log.debug("微信授权-保存会session 2");
//        // 添加到request
//        request.setAttribute(REQUEST_ATTR_FOR_CACHE_DATA, wxSession);
//
//        // 写入cookie
////        String sessionId = getWxSessionIdByCookie(request);
////        if (StringUtil.isEmpty(sessionId)) {
////            sessionId = generateSessionId(wxSession.getOpenId());
////        }
//        String sessionId = generateSessionId(wxSession.getOpenId());
//        
//        CookieUtil.setCookie(WxSessionConfig.getCookieName(), sessionId, WxSessionConfig.getCookieMaxAge(), request, response);
//
//        // 写入缓存
//        wxSession.setTs(new Date().getTime());
//        wxSession.setSessionId(sessionId);
//        if (!isDebug(request)) {
//            CachedClient.put(sessionId, wxSession, WxSessionConfig.getCookieMaxAge());
//        }
//        log.debug("微信授权-保存会session 3");
//        return sessionId;
//
//    }
//
//    public static String saveWxSession(AccessToken accessToken, HttpServletRequest request, HttpServletResponse response) {
//    	log.debug("微信授权-保存会session 1");
//        String openId = accessToken.getOpenid();
//
////        Account account = accountDao.getByOpenId(openId);
//        String accountId = accountDao.getAccountId(openId);
//        
//        log.debug("微信授权-保存会session accountId="+accountId);
//
//        log.debug("微信授权-保存会session 1.1");
////        if (account == null) {
//        if(StringUtil.isEmpty(accountId)){
//            throw new RuntimeException("找不到对应openId(" + openId + ")家庭帐号");
//        }
//
//        WxSession wxSession = new WxSession();
//        wxSession.setOpenId(openId);
//        wxSession.setAccountId(accountId);
//
//        return saveWxSession(wxSession, request, response);
//    }
//
//    /**
//     * 删除缓存数据
//     * 
//     * @param serviceNo
//     *            公众号ID
//     * @param openId
//     *            用户openid
//     */
//    public static void removeCacheData(String openId) {
//        CachedClient.remove(generateSessionId(openId));
//    }
//
//    /**
//     * debug模式或pc端访问情况生成的wxSession
//     * 
//     * @return
//     */
//    private static WxSession generateDebugWxSession() {
//
//        String openId = WxSessionConfig.getDebugOpenId();
//
//        Account account = accountDao.getByOpenId(openId);
//
//        if (account == null) {
//            throw new RuntimeException("找不到对应openId(" + openId + ")家庭帐号");
//        }
//
//        WxSession wxSession = new WxSession();
//        wxSession.setOpenId(openId);
//        wxSession.setAccountId(account.getId());
//
//        return wxSession;
//    }
//
//    /**
//     * 生成sessionId = md5(wxServiceNo+openId)
//     * 
//     * @param openId
//     * @return
//     */
//    private static String generateSessionId(String openId) {
//        return MD5Utils.getMD5ofStr(WechartServiceNoService.getServiceNo() + openId);
//    }
//
//}
