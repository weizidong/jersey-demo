package com.wzd.service.wechat.redpack;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;

import com.wzd.service.wechat.utils.SignUtil;
import com.wzd.service.wechat.utils.WeChatXmlUtil;

/**
 * 微信红包操作类
 * 
 * @author WeiZiDong
 *
 */
public class RedPackService {
	/**
	 * 发送红包
	 * 
	 * @param redPack
	 */
	public static void sendRedPack(String url, RedPack redPack) {
		SslConfigurator sslConfig = SslConfigurator.newInstance().trustStoreFile("src/main/resources/certificate/apiclient_cert.p12").trustStorePassword(redPack.getMch_id());
		SSLContext sslContext = sslConfig.createSSLContext();
		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
		redPack.setSign(SignUtil.signRedPack(redPack));
		System.out.println(redPack);
		client.target(url).request(MediaType.TEXT_XML).post(Entity.entity(WeChatXmlUtil.beanToXML(redPack), MediaType.TEXT_XML));
	}

//	@Test
//	public void test() {
//		RedPack redPack = new RedPack("红包name", "oPDWLv0ogagbw1PocuklciM2Ea0M", 1, "红包wishing", "红包act_name", "红包remark", "红包risk_info");
//		sendRedPack(FwAPI.SEND_REDPACK, redPack);
//	}
}
