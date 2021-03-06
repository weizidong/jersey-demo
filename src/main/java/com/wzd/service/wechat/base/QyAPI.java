package com.wzd.service.wechat.base;

/**
 * 企业号API接口
 * 
 * @author WeiZiDong
 *
 */
public abstract class QyAPI {
	/**
	 * 获取AccessToken，请求方式: GET
	 */
	public static final String GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
	/**
	 * 企业获取code，请求方式: GET
	 */
	public static final String AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_base&state={2}#wechat_redirect";
	/**
	 * 登录授权页，请求方式: GET
	 */
	public static final String LOGINPAGE = "https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id={0}&redirect_uri={1}&state={2}&usertype=all";
	/**
	 * 根据code获取成员信息，请求方式：GET
	 */
	public static final String GETUSERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={0}&code={1}";
	/**
	 * userid转换成openid接口，请求方式: POST
	 */
	public static final String CONVERT_TO_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token={0}";
	/**
	 * openid转换成userid接口，请求方式: POST
	 */
	public static final String CONVERT_TO_USERID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token={0}";
	/**
	 * 获取企业号登录用户信息，请求方式: POST
	 */
	public static final String GET_LOGIN_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token={0}";
	/**
	 * 获取登录企业号官网的url，请求方式: POST
	 */
	public static final String GET_LOGIN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_url?access_token={0}";
	/**
	 * 获取企业号应用，请求方式: GET
	 */
	public static final String GET_AGENT = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token={0}&agentid={1}";
	/**
	 * 设置企业号应用，请求方式: POST
	 */
	public static final String SET_AGENT = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token={0}";
	/**
	 * 获取应用概况列表，请求方式: GET
	 */
	public static final String AGENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token={0}";
	/**
	 * 二次验证，请求方式：GET
	 */
	public static final String AUTHSUCC = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token={0}&userid={1}";
	// =================== 消息  =================
	/**
	 * 发送消息，请求方式: POST
	 */
	public static final String SEND_MSG = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={0}";
	// ===================  菜单  ===============
	/**
	 * 创建应用菜单，请求方式: POST
	 */
	public static final String CREATE_MENU = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token={0}&agentid={1}";
	/**
	 * 删除菜单，请求方式：GET
	 */
	public static final String DELETE_MENU = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token={0}&agentid={1}";
	/**
	 * 获取菜单列表，请求方式：GET
	 */
	public static final String GET_MENU = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token={0}&agentid={1}";
	// ===================== 部门 ================
	/**
	 * 创建部门，请求方式: POST
	 */
	public static final String CREATE_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token={0}";
	/**
	 * 更新部门，请求方式: POST
	 */
	public static final String UPDATE_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token={0}";
	/**
	 * 更新部门，请求方式: GET
	 */
	public static final String DELETE_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token={0}&id={1}";
	/**
	 * 获取部门列表，请求方式: GET
	 */
	public static final String LIST_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token={0}&id={1}";
	// =============== 成员 ===================
	/**
	 * 创建成员，请求方式: POST
	 */
	public static final String CREATE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";
	/**
	 * 更新成员，请求方式: POST
	 */
	public static final String UPDATE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token={0}";
	/**
	 * 删除成员，请求方式: GET
	 */
	public static final String DELETE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token={0}&userid={1}";
	/**
	 * 批量删除成员，请求方式: POST
	 */
	public static final String BATCHDELETE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token={0}";
	/**
	 * 获取成员，请求方式: GET
	 */
	public static final String GET_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token={0}&userid={1}";
	/**
	 * 获取部门成员，请求方式: GET
	 */
	public static final String USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token={0}&department_id={1}&fetch_child=0&status=0";
	/**
	 * 获取部门成员(详情)，请求方式: GET
	 */
	public static final String USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token={0}&department_id={1}&fetch_child=0&status=0";
	// =============== 标签 ======================
	/**
	 * 创建标签，请求方式: POST
	 */
	public static final String CREATE_TAG = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token={0}";
	/**
	 * 更新标签名字，请求方式: POST
	 */
	public static final String UPDATE_TAG = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token={0}";
	/**
	 * 删除标签，请求方式: GET
	 */
	public static final String DELETE_TAG = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token={0}&tagid={1}";
	/**
	 * 获取标签成员，请求方式: GET
	 */
	public static final String GET_TAG = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token={0}&tagid={1}";
	/**
	 * 增加标签成员，请求方式: POST
	 */
	public static final String ADDTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token={0}";
	/**
	 * 删除标签成员，请求方式: POST
	 */
	public static final String DELTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token={0}";
	/**
	 * 获取标签列表，请求方式: GET
	 */
	public static final String TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token={0}";

}
