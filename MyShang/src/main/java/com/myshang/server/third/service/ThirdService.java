package com.myshang.server.third.service;

import java.util.Map;

import com.myshang.server.common.JsonData;


public interface ThirdService {
	/**
	 * 发送短信验证码
	 * @author lidongdong
	 * 2018年4月16日 下午5:07:33
	 * @param param
	 * @return
	 */
	public JsonData sendSms(Map<String, String> param);

	/**
	 * 验证码验证
	 * @author Administrator
	 * 2018年4月16日下午4:17:06
	 * @param
	 * @return
	 */
	public JsonData verifyCode(Map<String, String> param);
}
