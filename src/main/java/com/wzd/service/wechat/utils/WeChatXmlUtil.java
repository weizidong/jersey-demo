package com.wzd.service.wechat.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wzd.web.dto.exception.WebException;
import com.wzd.web.dto.response.ResponseCode;
import com.wzd.web.param.wechat.WechatMsg;

/**
 * 微信Xml转换工具类
 * 
 * @author WeiZiDong
 *
 */
public class WeChatXmlUtil {
	/**
	 * 将实体类转换成符合微信接口API要求的xml格式
	 */
	public static String beanToXML(Object obj) {
		StringBuffer sb = new StringBuffer("<xml>\n");
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// 父类Class
		Class<?> superClass = clazz.getSuperclass();
		Field[] superFields = superClass.getDeclaredFields();
		// 拼装自身的字段和字段值
		String fieldName = null;
		Object mapObj = null;
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				fieldName = field.getName();// 获得字段名
				mapObj = field.get(obj);// 获得字段值
				// 如果字段是Map类型，形如ImageRspMsg类中Map字段
				if (mapObj instanceof Map) {
					// 迭代map集合
					StringBuffer mapFieldValue = new StringBuffer("");
					String key = "";
					Map<?, ?> castMap = (Map<?, ?>) mapObj;
					Iterator<?> iterator = castMap.keySet().iterator();
					while (iterator.hasNext()) {
						// 迭代
						key = (String) iterator.next();
						mapFieldValue.append("<").append(key).append(">");
						// 调用value的toString方法
						mapFieldValue.append("<![CDATA[").append(castMap.get(key).toString()).append("]]>");
						mapFieldValue.append("</").append(key).append(">\n");
					}
					sb.append("<").append(fieldName).append(">\n");
					sb.append(mapFieldValue); // map集合内的迭代结果，勿加CDATA
					sb.append("</").append(fieldName).append(">\n");
				}
				// 字段非Map类型，则按照String类型处理（获得value时直接调用toString方法）
				else {
					sb.append("<").append(fieldName).append(">");
					sb.append("<![CDATA[").append(mapObj.toString()).append("]]>");
					sb.append("</").append(fieldName).append(">\n");
				}
			}
			// 拼装父类的字段和字段值
			String superFieldName = "";
			for (Field field : superFields) {
				field.setAccessible(true);
				superFieldName = field.getName();
				sb.append("<").append(superFieldName).append(">");
				sb.append("<![CDATA[").append(field.get(obj).toString()).append("]]>");
				sb.append("</").append(superFieldName).append(">\n");

			}
		} catch (Exception e) {
			throw new WebException(ResponseCode.资源不存在, "实体[" + clazz + "]转换xml错误!", e);
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 将xml转换成实体类
	 */
	@SuppressWarnings("deprecation")
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				Field f = clazz.getDeclaredField(e.getName());
				f.setAccessible(true);
				String type = f.getType().toString();
				if (type.endsWith("Date")) {
					f.set(bean, Date.parse(e.getText()));
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					f.set(bean, Integer.parseInt(e.getText()));
				} else if (type.endsWith("long") || type.endsWith("Long")) {
					f.set(bean, Long.parseLong(e.getText()));
				} else if (type.endsWith("double") || type.endsWith("Double")) {
					f.set(bean, Double.parseDouble(e.getText()));
				} else {
					f.set(bean, e.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebException(ResponseCode.资源不存在, "解析xml成实体[" + clazz + "]错误!");
		}
		return bean;
	}

	public static void main(String[] args) {
		WechatMsg msg = new WechatMsg();
		msg.setContent("asdasdasdasdasd");
		System.out.println(beanToXML(msg));
	}
}
