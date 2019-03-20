package com.myshang.server.consts;

/**
 * redis键的枚举类
 * @author lidongdong
 * 2017年11月24日 上午11:30:23
 */
public enum RedisKeyEnum {
	
	/**
	 * 用于redis存储的键名
	 */
	USER_ID("session_key:id:"); //用户token表的键名

	
	private String key;
	
	private RedisKeyEnum(String key){
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
