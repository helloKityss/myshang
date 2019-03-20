package com.myshang.server.business.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.common.JsonData;
import com.myshang.server.reward.model.Reward;

public interface BusinessService {
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData savebussiness(Map<String, String> param) throws Exception;
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData getbussiness(Map<String, String> param) throws Exception;
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData updatebussiness(Map<String, String> param) throws Exception;
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData deletebussiness(Map<String, String> param) throws Exception;
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData uploadbussiness(MultipartFile multipartFile) throws Exception;
	/**
	 * 查询商户列表
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData queryBussiness(Map<String, String> param) throws Exception;
}
