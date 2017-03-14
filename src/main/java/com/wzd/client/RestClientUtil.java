package com.wzd.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.client.ClientConfig;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.wzd.client.filter.log.LogRequestFilter;
import com.wzd.client.filter.log.LogResponseFilter;

public class RestClientUtil {

	private static Client baseClient;

	static {
		buildClients();
	}

	private static void buildClients() {
		ClientConfig clientConfig = new ClientConfig();
		// 注册JacksonJsonProvider
		clientConfig.register(JacksonJsonProvider.class);
		clientConfig.register(LogRequestFilter.class);
		clientConfig.register(LogResponseFilter.class);
		baseClient = ClientBuilder.newClient(clientConfig);
	}

	public static Client getBaseClient() {
		return baseClient;
	}

	public static WebTarget buildWebTarget(String url) {
		return baseClient.target(url);
	}

	public static <T> T postForm(WebTarget target, MultivaluedMap<String, String> formParam, Class<T> beanClass) {
		Form form = new Form(formParam);
		String json = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		return JSON.parseObject(json, beanClass);
	}

	public static <T> T postJson(WebTarget target, Object param, Class<T> beanClass) {
		String json = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(param, MediaType.APPLICATION_JSON_TYPE), String.class);
		return JSON.parseObject(json, beanClass);
	}

	public static <T> T get(WebTarget target, Class<T> beanClass) {
		String json = target.request().get(String.class);
		return JSON.parseObject(json, beanClass);
	}

}
