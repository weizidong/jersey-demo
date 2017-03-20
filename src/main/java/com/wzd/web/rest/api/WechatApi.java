package com.wzd.web.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import com.wzd.utils.HttpUtils;
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
		// URL解码
		String sReqMsgSig = HttpUtils.ParseUrl(msg_signature);
		String sReqTimeStamp = HttpUtils.ParseUrl(timestamp);
		String sReqNonce = HttpUtils.ParseUrl(nonce);
		// 对用户回复的消息解密
		String sMsg;
		try {
			sMsg = QyWxService.wxcpt().DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, data);
		} catch (AesException e) {
			throw new WebException(ResponseCode.不允许此方法, "解密回复信息密文失败！");
		}
		log.debug("参数：\n" + sMsg);
		// 解析xml
		WechatMsg msg = WeChatXmlUtil.xmlToBean(sMsg, WechatMsg.class);
		String rMsg = qyService.push(msg);
		log.debug("结果：\n" + rMsg);
		// 回包加密
		try {
			return QyWxService.wxcpt().EncryptMsg(rMsg, sReqTimeStamp, sReqNonce);
		} catch (Exception e) {
			throw new WebException(ResponseCode.不允许此方法, "回包加密失败失败！");
		}
	}

	/**
	 * 同步企业号
	 */
	@GET
	@Path("/sync")
	public String sync() {
		return qyService.sync();
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
	public String fwVerifyURL(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr) {
		return fwService.VerifyURL(msg_signature, timestamp, nonce, echostr);
	}

}
