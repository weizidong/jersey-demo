package com.wzd.service.wechat.base;

/**
 * 微信公众平台API接口常量
 * 
 * @author WeiZiDong
 * 
 */
public abstract class FwAPI {
	// ======================== 微信网页授权 ============================
	/**
	 * 用户同意授权，获取code，请求方式: GET
	 */
	public static final String AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=SCOPE&state={2}#wechat_redirect";
	/**
	 * 通过code换取网页授权access_token，请求方式: GET
	 */
	public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	/**
	 * 通过code换取网页授权access_token，请求方式: GET
	 */
	public static final String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}";
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)，请求方式: GET
	 */
	public static final String USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN ";

	// ======================== ACCESS_TOKEN ============================
	/**
	 * 获取access_token，请求方式: GET
	 */
	public static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

	// ======================== 自定义菜单 ============================
	/**
	 * 自定义菜单创建接口，请求方式: POST
	 */
	public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";
	/**
	 * 自定义菜单查询接口，请求方式: GET
	 */
	public static final String GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";
	/**
	 * 自定义菜单删除接口，请求方式: GET
	 */
	public static final String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}";
	// ======================== 消息 ============================
	/**
	 * 发送模板消息，请求方式: POST
	 */
	public static final String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";
	/**
	 * 客服接口-发消息，请求方式: POST
	 */
	public static final String SEND_CUSTOM = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";
	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】，请求方式: POST
	 */
	public static final String SEND_MASS = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token={0}";
	// ======================== 用户 ============================
	/**
	 * 获取用户基本信息（包括UnionID机制），请求方式: GET
	 */
	public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
	/**
	 * 批量获取用户基本信息，请求方式: POST
	 */
	public static final String BATCHGET_INFO = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={0}";
	/**
	 * 获取用户列表，请求方式: GET
	 */
	public static final String GET_USER = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";
	/**
	 * 设置用户备注名，请求方式: POST
	 */
	public static final String UPDATEREMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token={0}";
	// ======================== 素材 ============================
	/**
	 * 新增永久图文素材,请求方式:POST
	 */
	public static final String ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token={0}";
	/**
	 * 上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】,请求方式:POST
	 */
	public static final String UPLOADIMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";
	/**
	 * 上传图文消息素材【订阅号与服务号认证后均可用】,请求方式:POST
	 */
	public static final String UPLOADNEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token={0}";
	// ======================== 红包 ============================
	/**
	 * 发放普通红包,请求方式:POST
	 */
	public static final String SEND_REDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	/**
	 * 发放裂变红包,请求方式 :POST
	 */
	public static final String SEND_GROUP_REDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
	// ======================== 生成带参数的二维码 ============================
	/**
	 * 创建二维码,请求方式:POST
	 */
	public static final String CREATE_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
}
