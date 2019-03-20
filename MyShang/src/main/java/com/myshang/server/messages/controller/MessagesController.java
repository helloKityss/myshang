package com.myshang.server.messages.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.messages.service.MessagesService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class MessagesController {
	@Autowired
	private MessagesService messagesService;
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@RequestMapping("/messages/messagesList")
	public JsonData messagesList(@RequestParam Map<String, String> param){
		return messagesService.messagesList(param);
	}
	/**
	 * 查询投诉或任务信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@RequestMapping("/messages/getMessagesComplaint")
	public JsonData getMessagesState(@RequestParam Map<String, String> param){
		return messagesService.getMessagesState(param);
	}
	/**
	 * 查询任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@RequestMapping("/messages/getMessagestask")
	public JsonData getMessagesHang(@RequestParam Map<String, String> param){
		return messagesService.getMessagesHang(param);
	}
	/*
	 * 新增消息
	 */
	@RequestMapping("/messages/createMessages")
	public JsonData createMessages(@RequestParam Map<String, String> param) throws Exception {
		return messagesService.createMessages(param);
	}
	/*
	 * 修改BOOS任务状态
	 */
	@RequestMapping("/messages/updateMessages")
	public JsonData updateMessages(@RequestParam Map<String, String> param) throws Exception {
		return messagesService.updateMessages(param);
	}
	/*
	 * 修改BOOS任务状态
	 */
	@RequestMapping("/messages/MessagesEncryption")
	public JsonData MessagesEncryption(@RequestParam Map<String, String> param) throws Exception {
		return messagesService.MessagesEncryption(param);
	}
	/*
	 * 修改BOOS任务状态
	 */
	@RequestMapping("/messages/getTemplate")
	public JsonData getTemplate(@RequestParam Map<String, String> param) throws Exception {
		return messagesService.getTemplate(param);
	}
}
