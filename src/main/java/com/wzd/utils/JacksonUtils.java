package com.wzd.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson 工具类
 * 
 * @author LinHaobin
 * 
 */
public class JacksonUtils {

	// private static Logger log = LoggerUtil.getLogger(JacksonUtils.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static ObjectMapper getMapper() {
		return MAPPER;
	}

	public static String toJson(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("转换json发生异常", e);
		}
	}

	/**
	 * json 转 javabean
	 * 
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T toBean(String json, Class<T> clazz)  {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException("转换json发生异常", e);
		}
	}
	
//	public static <T> List<T> toList(String json,Class<T> clazz){
//		MAPPER
//	}

}
