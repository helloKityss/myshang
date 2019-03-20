package com.myshang.server.third.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;

/**
 * 短信发送工具类
 * @author lidongdong
 * 2017年11月22日 下午4:50:59
 */
public final class JiGuangSmsUtil {
	
	private static final Logger LOGGER = Logger.getLogger(JiGuangSmsUtil.class);
	private static final String APPKEY = "5eb4bb76074e0938468b00f1";
	private static final String MASTER_SECRET = "096f7f3080611a87e152cc31";
	
	private JiGuangSmsUtil(){
		
	}
	
	
	public static JSONObject sendMessage(String phone) throws APIConnectionException, APIRequestException{
		SMSClient client = new SMSClient(MASTER_SECRET, APPKEY);
		SMSPayload payload = SMSPayload.newBuilder().setMobileNumber(phone).setTempId(1).build();
        SendSMSResult res = client.sendSMSCode(payload);
        LOGGER.info("发送短信返回的结果为:" + res.toString());
        return JSON.parseObject(res.toString());
	}
	
	public static JSONObject SendValidSMSCode(String msgId,String code) throws APIConnectionException, APIRequestException {
    	SMSClient client = new SMSClient(MASTER_SECRET, APPKEY);
		ValidSMSResult res = client.sendValidSMSCode(msgId, code);
        LOGGER.info("验证短信验证码返回的结果为:" + res.toString());
        return JSON.parseObject(res.toString());
	}
	
	public static boolean ValidSMSCode(String msgId,String code) {
    	SMSClient client = new SMSClient(MASTER_SECRET, APPKEY);
		try {
			ValidSMSResult res = client.sendValidSMSCode(msgId, code);
            LOGGER.info("验证短信验证码返回的结果为:" + res.toString());
            JSONObject jsonObject = JSON.parseObject(res.toString());
            return jsonObject.getBooleanValue("is_valid");
		} catch (APIConnectionException e) {
            LOGGER.error("验证短信验证码连接超时:" + e.getMessage());
        } catch (APIRequestException e) {
            LOGGER.error("验证短信验证码错误,Status:" + e.getStatus() + " errorCode: " + e.getErrorCode() + "Message:" + e.getErrorMessage());
        }
		return false;
	}
}
