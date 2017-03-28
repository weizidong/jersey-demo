package com.wzd.model.entity;

import java.io.Serializable;

import javax.persistence.Id;

@SuppressWarnings("serial")
public class Setting implements Serializable {
	@Id
	private String id;
	private String logo; // logo
	private String name; // name
	private String sub; // 关注信息
	
}
