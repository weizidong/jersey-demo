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

import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.service.wechat.FwWxService;
import com.wzd.service.wechat.QyWxService;
import com.wzd.web.filter.formatjson.FormatJson;
import com.wzd.web.filter.formatjson.FormatJsonType;

/**
 * 接收微信推送接口
 * 
 * @author WeiZiDong
 *
 */
@Path("/wechat")
@Produces(MediaType.TEXT_XML)
@Consumes(MediaType.TEXT_XML)
public class WechatApi {
	@Autowired
	private QyWxService qyService;
	@Autowired
	private FwWxService fwService;

	/**
	 * 企业号回调过来的信息
	 */
	@Path("/qy")
	@POST
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	public String qyPush(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@Context HttpServletRequest request, String data) {
		return qyService.push(msg_signature, timestamp, nonce, data, request);
	}

	/**
	 * 验证回调URL
	 */
	@Path("/qy")
	@GET
	public String qyVerifyURL(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr) {
		return qyService.VerifyURL(msg_signature, timestamp, nonce, echostr);
	}

	/**
	 * 服务号回调过来的信息
	 */
	@Path("/fw")
	@POST
	@FormatJson(FormatJsonType.NOTSUPPORTED)
	public String fwPush(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@Context HttpServletRequest request, String xml) {
		return fwService.push(msg_signature, timestamp, nonce, xml, request);
	}

	/**
	 * 验证回调URL
	 */
	@Path("/fw")
	@GET
	public String fwVerifyURL(@QueryParam("msg_signature") String msg_signature, @QueryParam("timestamp") String timestamp, @QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echostr) {
		return fwService.VerifyURL(msg_signature, timestamp, nonce, echostr);
	}
}
