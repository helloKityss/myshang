package com.myshang.server.messages.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface MessagesService {
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData messagesList(Map<String, String> param);
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData getMessagesState(Map<String, String> param);
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData getMessagesHang(Map<String, String> param);
	/**
	 * 添加消息信息
	 * @author zhangkang
	 * @param param
	 * @return
	 */
	public JsonData createMessages(Map<String, String> param) throws Exception;
	/**
	 * 添加消息信息
	 * @author zhangkang
	 * @param param
	 * @return
	 */
	public JsonData updateMessages(Map<String, String> param) throws Exception;
	/**
	 * 添加消息信息
	 * @author zhangkang
	 * @param param
	 * @return
	 */
	public JsonData MessagesEncryption(Map<String, String> param) throws Exception;
	/**
	 * 添加消息信息
	 * @author zhangkang
	 * @param param
	 * @return
	 */
	public JsonData getTemplate(Map<String, String> param) throws Exception;
}
