package com.myshang.server.redis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

/**
 * redis键过期监听接口
 * @author lidongdong
 * 2018年3月2日 上午11:06:25
 */
public interface RedisMessageDelegate {
	
	void handleMessage(String message);

    void handleMessage(Map message);

    void handleMessage(byte[] message);

    void handleMessage(Serializable message);

    void handleMessage(Serializable message, String channel);
}
