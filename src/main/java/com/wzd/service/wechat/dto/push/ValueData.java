package com.lifesense.healthcenter.service.wechat.dto.push;

import java.io.Serializable;

/**
 * 
 */
@SuppressWarnings("serial")
public class ValueData implements Serializable{
	String value;
	String color;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ValueData(String value, String color){
		this.value = value;
		this.color = color;
	}
	
}
