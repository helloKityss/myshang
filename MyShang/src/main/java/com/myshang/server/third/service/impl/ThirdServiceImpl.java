package com.myshang.server.third.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.third.service.ThirdService;
import com.myshang.server.third.util.JiGuangSmsUtil;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
@Service
@Transactional
public class ThirdServiceImpl implements ThirdService {
	private static final Logger LOGGER = Logger.getLogger(ThirdServiceImpl.class);
	@Override
	public JsonData sendSms(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		try {
			String phone = param.get("phone");
			LOGGER.error("发送短信验证码手机号为"+ phone);
			JSONObject jsonObject = JiGuangSmsUtil.sendMessage(phone);
			Map map=new HashMap();
			map.put("msgId",jsonObject.getString("msg_id"));
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (APIConnectionException e) {
			LOGGER.error("发送短信验证码失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
			jsonData.setMsg(e.getMessage());
		} catch (APIRequestException e) {
			LOGGER.error("发送短信验证码失败", e);
			jsonData.setCode(e.getErrorCode());
			jsonData.setMsg(e.getErrorMessage());
		} catch (Exception e) {
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	@Override
	public JsonData verifyCode(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		try {
			String code = param.get("code");
			String msgId = param.get("msgId");
			JSONObject jsonObject = JiGuangSmsUtil.SendValidSMSCode(msgId, code);
			jsonData.setData(jsonObject.getBooleanValue("is_valid"));
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (APIConnectionException e) {
			LOGGER.error("验证短信出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
			jsonData.setMsg("验证失败");
		} catch (APIRequestException e) {
			LOGGER.error("验证短信出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
			jsonData.setCode(e.getErrorCode());
			jsonData.setMsg("验证失败");
		}
		return jsonData;
	}
}
