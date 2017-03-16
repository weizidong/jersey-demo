package com.wzd.service.wechat.redpack;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;

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
		SslConfigurator sslConfig = SslConfigurator.newInstance().trustStoreFile("/certificate/apiclient_cert.p12").trustStorePassword(redPack.getMch_id());
		SSLContext sslContext = sslConfig.createSSLContext();
		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
		client.target(url).request(MediaType.TEXT_XML).post(Entity.entity(redPack, MediaType.TEXT_XML));
	}
}
