package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.service.wechat.utils.AesException;
import com.wzd.service.wechat.utils.WeChatXmlUtil;
import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.filter.formatjson.FormatJson;
import com.wzd.web.filter.formatjson.FormatJsonType;
import com.wzd.web.filter.log.RequestLog;
import com.wzd.web.filter.log.RequestLogType;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 接收微信推送接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/wechat")
@Produces(MediaType.TEXT_XML)
@Consumes(MediaType.APPLICATION_JSON)
public class WechatApi {
	private static final Logger log = LogManager.getLogger(WechatApi.class);
	@Autowired
	private QyWxService qyService;
	@Autowired
	private FwWxService fwService;

	/**
	 * 企业号回调过来的信息(密文传输)
	 */
	@POST
	@Path("/qy")
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	@Consumes(MediaType.TEXT_XML)
	public String qyPush(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce, String data) {
		log.debug("接收到企业号推送的消息。。。");
		// 对用户回复的消息解密
		String sMsg;
		try {
			sMsg = QyWxService.wxcpt().DecryptMsg(msg_signature, timestamp, nonce, data);
		} catch (AesException e) {
			throw new WebException(ResponseCode.解密失败, "解密回复信息密文失败！");
		}
		log.debug("参数：\n" + sMsg);
		// 解析xml
		WechatMsg msg = WeChatXmlUtil.xmlToBean(sMsg, WechatMsg.class);
		String rMsg = qyService.push(msg);
		log.debug("结果：\n" + rMsg);
		// 回包加密
		try {
			return QyWxService.wxcpt().EncryptMsg(rMsg, timestamp, nonce);
		} catch (Exception e) {
			throw new WebException(ResponseCode.加密失败, "回包加密失败失败！");
		}
	}

	/**
	 * 验证回调URL
	 */
	@GET
	@Path("/qy")
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	public String qyVerifyURL(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr) {
		return qyService.VerifyURL(msg_signature, timestamp, nonce, echostr);
	}

	/**
	 * 服务号回调过来的信息(明文传输)
	 */
	@POST
	@Path("/fw")
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	@Consumes(MediaType.TEXT_XML)
	public String fwPush(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@Context HttpServletRequest request, String data) {
		log.debug("接收到服务号推送的消息。。。");
		log.debug("参数：\n" + data);
		WechatMsg msg = WeChatXmlUtil.xmlToBean(data, WechatMsg.class);
		String rMsg = fwService.push(msg);
		log.debug("结果：\n" + rMsg);
		return rMsg;
	}

	/**
	 * 验证回调URL
	 */
	@GET
	@Path("/fw")
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	@RequestLog(RequestLogType.NOTSUPPORTED)
	public String fwVerifyURL(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr) {
		return fwService.VerifyURL(signature, timestamp, nonce, echostr);
	}

	/**
	 * 获取服务号自定义菜单
	 */
	@GET
	@Path("/fwMenu")
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getFwMenu() {
		return fwService.getFwMenu();
	}

	/**
	 * 创建服务号自定义菜单
	 */
	@POST
	@Path("/fwMenu")
	@Consumes(MediaType.TEXT_PLAIN)
	public void createFwMenu(String menu) {
		fwService.createFwMenu(menu);
	}

	/**
	 * 删除服务号自定义菜单
	 */
	@DELETE
	@Path("/fwMenu")
	public void deleteFwMenu() {
		fwService.deleteFwMenu();
	}

}
