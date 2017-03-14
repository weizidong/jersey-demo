//package com.wzd.web.filter.wxsession;
//
//import java.io.IOException;
//import java.util.Map;
//
//import com.lifesense.framework.util.properties.PropertiesUtil;
//
//public class WxSessionConfig {
//
//    private static String cookieName = "cookieName";
//
//    private static int cookieMaxAge = 0;
//
//    private static boolean isDebug = false;
//
//    private static String debugOpenId = "";
//
//    /**
//     * 初始化微信会话配置
//     */
//    static {
//        try {
//            Map<String, String> propMap = PropertiesUtil.readPropertiesForMap("/env-config/wx_session_config.properties");
//
//            isDebug = Boolean.valueOf(propMap.get("is_debug"));
//            debugOpenId = propMap.get("debug_open_id");
//            cookieName = propMap.get("cookie_name");
//            cookieMaxAge = Integer.valueOf(propMap.get("cookie_max_age"));
//        } catch (IOException e) {
//            throw new RuntimeException("初始化微信会话配置。", e);
//        }
//    }
//
//    /**
//     * 保存sessionId的cookie的名称
//     * 
//     * @return
//     */
//    public static String getCookieName() {
//        return cookieName;
//    }
//
//    /**
//     * sessionId的cookie生命周期(秒)
//     * 
//     * @return
//     */
//    public static int getCookieMaxAge() {
//        return cookieMaxAge;
//    }
//
//    /**
//     * 是否debug模式，是的话不经过微信授权，直接生成wxSession
//     * 
//     * @return
//     */
//    public static boolean isDebug() {
//        return isDebug;
//    }
//
//    /**
//     * 获取debug模式的openId
//     * 
//     * @return
//     */
//    public static String getDebugOpenId() {
//        return debugOpenId;
//    }
//}
