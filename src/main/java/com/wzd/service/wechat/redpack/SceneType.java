package com.wzd.service.wechat.redpack;

/**
 * 发放红包使用场景类型
 * 
 * @author WeiZiDong
 *
 */
public enum SceneType {
	商品促销("PRODUCT_1"),
	抽奖("PRODUCT_2"),
	虚拟物品兑奖("PRODUCT_3"),
	企业内部福利("PRODUCT_4"),
	渠道分润("PRODUCT_5"),
	保险回馈("PRODUCT_6"),
	彩票派奖("PRODUCT_7"),
	税务刮奖("PRODUCT_8");
	
	private String value;

	private SceneType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static SceneType parse(String type) {
		for (SceneType item : SceneType.values()) {
			if (type != null && type.equals(item.getValue())) {
				return item;
			}
		}
		throw new RuntimeException("值[" + type + "]不是" + SceneType.class + "有效值。");
	}
}
