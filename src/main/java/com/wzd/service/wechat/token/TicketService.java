package com.wzd.service.wechat.token;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lifesense.healthcenter.utils.CachedClient;
import com.lifesense.healthcenter.utils.HttpClientManager;
import com.wzd.service.wechat.WechartServiceNoService;
import com.wzd.service.wechat.WeixinAPI;
import com.wzd.service.wechat.token.dto.TicketDto;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 微信服务号jsapi_ticket service 从缓存中获取微信的jsapi_ticket
 * 
 * @author lujuzhi
 * 
 */
public class TicketService {

	private static Logger log = LogManager.getLogger(TicketService.class);

	private static final String TICKET = "ticket";

	private static final String EXPIRES_IN = "expires_in";

	/**
	 * 更新jsapi_ticket
	 */
	public static String updateJSapiTicket() {

		try {

			Map<String, Object> ticketMap = getTicketByWeixin(1);

			if (ticketMap == null) {
				return null;
			}

			String cacheKey = getCachKey();

			String ticket = StringUtil.toString(ticketMap.get(TICKET));

			Long expiresIn = StringUtil.toLong(ticketMap.get(EXPIRES_IN));

			TicketDto ticketDto = new TicketDto();

			ticketDto.setJsapiTicket(ticket);
			ticketDto.setExpiresIn(expiresIn);
			ticketDto.setTimestamp(System.currentTimeMillis());

			CachedClient.put(cacheKey, ticketDto);

			return ticket;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据服务号获取jsapi_ticket
	 * 
	 * @return jsapi_ticket
	 */
	public static String getJSapiTicket() {

		String ticket = "";

		String cacheKey = getCachKey();

		Object ticketObject = CachedClient.get(cacheKey);

		if (ticketObject == null) {
			return updateJSapiTicket();
		}
		
		TicketDto ticketDto =  (TicketDto) ticketObject;

		Long timestamp = ticketDto.getTimestamp();
		
		Long diff = (System.currentTimeMillis() - timestamp)/1000;
		
		ticket = ticketDto.getJsapiTicket();
		
		if (diff > ticketDto.getExpiresIn() - 2000) {
			ticket = updateJSapiTicket();
		}
		

		return ticket;
	}

	/**
	 * 向微信获取（4-queryTimes）次jsapi_ticket
	 * @param queryTimes
	 * @return
	 */
	private static Map<String, Object> getTicketByWeixin(Integer queryTimes) {

		// 如果查询3次都失败就不查询了
		if (queryTimes > 3) {
			return null;
		}
		
		String accessToken = TokenService.getAccessToken();

		// 调用接口获取jsapi_ticket
		String body = HttpClientManager.get(MessageFormat.format(WeixinAPI.GET_JSAPI_TICKET_URL, accessToken));

		log.debug("get jsapi ticket body = " + body);

		if (StringUtil.isBlank(body)) {
			queryTimes++;
			return getTicketByWeixin(queryTimes);
		}

		JSONObject json = JSONObject.fromObject(body);
		
		// errcode判断结果代码
		int resultCode = TokenService.checkErrcode(json);

		if (0 != resultCode) { // 获取失败
			queryTimes++;
			TokenService.updateAccessToken();
			return getTicketByWeixin(queryTimes);
		}

		String ticket = json.getString(TICKET);
		
		Long expiresIn = json.getLong(EXPIRES_IN);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(TICKET, ticket);
		resultMap.put(EXPIRES_IN, expiresIn);

		return resultMap;
	}

	/**
	 * 
	 * @return
	 */
	private static String getCachKey() {
		return WechartServiceNoService.getServiceNo() + "_" + TICKET;
	}

}
