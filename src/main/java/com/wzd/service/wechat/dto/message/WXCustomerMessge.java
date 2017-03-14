//package com.wzd.service.wechat.dto.message;
//
//import java.io.Serializable;
//
//import com.lifesense.healthcenter.utils.CachedClient;
//import com.lifesense.healthcenter.utils.StringUtil;
//
///**
// * 微信客服
// * 
// * @author lyb
// * 
// */
//public class WXCustomerMessge implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 6565344088005640059L;
//	
//	private static final String prefix = "WXCustomerMessge_";
//	
//	private String serviceNo;
//	
//	private String openId;
//	
//	private String content;
//	
//	private long timestample;
//	
//	private boolean isAccepte; 	
//
//	public String getServiceNo() {
//		return serviceNo;
//	}
//
//	public void setServiceNo(String serviceNo) {
//		this.serviceNo = serviceNo;
//	}
//
//	public String getOpenId() {
//		return openId;
//	}
//
//	public void setOpenId(String openId) {
//		this.openId = openId;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public long getTimestample() {
//		return timestample;
//	}
//
//	public void setTimestample(long timestample) {
//		this.timestample = timestample;
//	}
//	
//	
//	public boolean isAccepte() {
//		return isAccepte;
//	}
//
//	public void setAccepte(boolean isAccepte) {
//		this.isAccepte = isAccepte;
//	}
//
//	/**
//	 * 
//	 * @param openId
//	 * @return
//	 */
//	public static String getMemacheKey(String openId){
//		return prefix+openId;
//	}
//
//	/**
//	 * 
//	 * @param openId
//	 * @return
//	 */
//	public static WXCustomerMessge getWXCustomerMessge(String openId) {
//		String key = WXCustomerMessge.getMemacheKey(openId);
//	    Object obj = CachedClient.get(key);
//	    
//	    WXCustomerMessge customer = obj==null?new WXCustomerMessge():(WXCustomerMessge)obj;
//	    
//	    return customer;
//	}
//
//	/**
//	 * 
//	 * @param serviceNo
//	 * @param openId
//	 * @param accepte
//	 */
//	public static void writeCache(String serviceNo, String openId, boolean accepte) {
//		String key = WXCustomerMessge.getMemacheKey(openId);
//		WXCustomerMessge customer = getWXCustomerMessge(key);
//		if(StringUtil.isBlank(customer.getOpenId())){
//			customer.setServiceNo(serviceNo);
//			customer.setOpenId(openId);
//		}
//		customer.setTimestample(System.currentTimeMillis());
//		customer.setAccepte(accepte);
//		CachedClient.put(key, customer);
//	}
//	
//	/**
//	 * 
//	 * @param openId
//	 * @return
//	 */
//	public static void removeWXCustomerMessge(String openId) {
//		String key = WXCustomerMessge.getMemacheKey(openId);
//		if(CachedClient.keyExists(key)){
//			CachedClient.remove(key);
//		}
//	}
//	
//}
