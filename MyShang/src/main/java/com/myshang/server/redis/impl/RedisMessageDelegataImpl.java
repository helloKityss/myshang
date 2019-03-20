package com.myshang.server.redis.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.myshang.server.redis.RedisMessageDelegate;


/**
 * @author lidongdong
 * 2018年3月2日 上午11:07:57
 */
public class RedisMessageDelegataImpl implements RedisMessageDelegate {
	
	private static final Logger LOGGER = Logger.getLogger(RedisMessageDelegataImpl.class);
	
	public void handleMessage(String message) {
	}

	public void handleMessage(Map message) {
	}

	public void handleMessage(byte[] message) {
	}

	public void handleMessage(Serializable message) {
		
	}

	public void handleMessage(Serializable message, String channel) {
	}

}
