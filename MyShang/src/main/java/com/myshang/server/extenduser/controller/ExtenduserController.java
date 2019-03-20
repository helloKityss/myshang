package com.myshang.server.extenduser.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.business.model.Business;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.RedisUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.extenduser.model.Extenduser;
import com.myshang.server.extenduser.service.ExtenduserService;
import com.myshang.server.role.model.Role;
import com.myshang.server.user.controller.UserController;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.UserService;

import net.sf.json.JSONObject;
@RestController
public class ExtenduserController {
	@Autowired
	private ExtenduserService extendService;
	public Logger log = Logger.getLogger(ExtenduserController.class); // 日志
	@SuppressWarnings("unused")
	@RequestMapping("/extenduser/extenduserlogin")
	public JSONObject extenduserlogin(HttpServletRequest request,@RequestParam Map<String, String> param) {
        log.error("开始调用login接口");
		JSONObject jsonUser = new JSONObject();
		try {
			String usercode = param.get("code");
			// 微信的接口
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
					+ CmsConstants.WEIXIN_MINI_APPID + "&secret=" + CmsConstants.WEIXIN_MINI_SECRET + "&js_code=" + usercode
					+ "&grant_type=authorization_code";
			// 进行网络请求,访问url接口
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String userinfoStr = EntityUtils.toString(httpEntity, "utf-8");
			JSONObject jsonResp = JSONObject.fromObject(userinfoStr);
			log.error("22222222222222++++++++="+jsonResp.toString());
			log.error("jsonresp:" + jsonResp.toString());
			// 根据返回值进行后续操作
			if (jsonResp != null && !jsonResp.equals("") && jsonResp.get("openid") != null) {
				Extenduser userinfo = extendService.getExtenduserByOpenId(jsonResp.get("openid").toString());
				if (userinfo == null) {
					// jsonUser.put("userType", "0");
					userinfo = new Extenduser();
					userinfo.setOpenId(jsonResp.getString("openid"));
					int extid = extendService.createExtendserRespId(userinfo);
					log.error(extid);
					if (extid!=0) {
				        RedisUtil.setex("sessionkey"+extid, jsonResp.getString("session_key"),1000);
				        log.error("redis连接成功11111");
				        //jedis.setex("session_key"+uid, 1000,jsonResp.getString("session_key"));
				        log.error("redis 存储的字符串为: "+ RedisUtil.get("sessionkey"+extid));
				        //jedis.expire("key", seconds)
						jsonUser.put("msg", "create user success");
						jsonUser.put("uid", extid);
						jsonUser.put("isregiste", "0");
					} else {
						jsonUser.put("msg", "create user fail");
						jsonUser.put("uid", "0");
					}
				} else {
					// jsonUser.put("userType", "1");
			        RedisUtil.setex("sessionkey"+userinfo.getExtid(), jsonResp.getString("session_key"),1000);
			        log.error("redis连接成功2222");
					jsonUser.put("uid", userinfo.getExtid());
					jsonUser.put("isregiste", userinfo.getHasRegister());
					jsonUser.put("msg", "user has been created");
				}
				jsonUser.put("status", "ok");
			} else {
				log.error("jsonResp为空");
				jsonUser.put("userType", "-1");
			}
		} catch (Exception e) {
			jsonUser.put("userType", "-1");
			log.error("登录错误");
			e.printStackTrace();
		}
		return jsonUser;
	}

	/**
	 * 从微信获取用户信息
	 */
	@RequestMapping("/extenduser/getextenduserbyweixin")
	public JsonData getextenduserbyweixin(@RequestParam Map<String, String> param) {	
		JsonData jsonData = new JsonData();
		JSONObject jsonUser = new JSONObject();
		try {
			int id = Integer.valueOf(param.get("uid"));
			log.error(id);
			String encryptedDataStr = param.get("encryptedData");
			String rawData = param.get("rawData");
			String signature = param.get("signature");
			String ivStr = param.get("iv");
			 //连接本地的 Redis 服务
	        log.error("redis 存储的字符串为222222222: "+ RedisUtil.get("sessionkey"+id));
	        String session_key=RedisUtil.get("sessionkey"+id);
			if (WeiXinUtil.getSha1(rawData + session_key).equals(signature)) {
				jsonUser = WeiXinUtil.getUserInfo(encryptedDataStr,
						session_key, ivStr);
				Extenduser userinfo = new Extenduser();
				userinfo.setExtid(id);
				userinfo.setNickName(jsonUser.getString("nickName"));
				log.error("存储信息为2222222 "+ jsonUser.getString("nickName"));
				userinfo.setAvatarUrl(jsonUser.getString("avatarUrl"));
				userinfo.setCity(jsonUser.getString("city"));
				userinfo.setCountry(jsonUser.getString("country"));
				userinfo.setProvince(jsonUser.getString("province"));
				userinfo.setUnionId(jsonUser.getString("unionId"));
				if (jsonUser.getString("gender").equals("1")) {
					userinfo.setGender(1);
				} else if (jsonUser.getString("gender").equals("2")) {
					userinfo.setGender(0);
				} else {
					userinfo.setGender(2);
				}
				jsonUser.clear();
				if (extendService.updateExtenduserByWeiXin(userinfo) > 0) {
					jsonData.setCodeEnum(CodeEnum.SUCCESS);
				} else {
					jsonData.setCodeEnum(CodeEnum.ERROR);
				}
				jsonData.setData(jsonUser);
			} else {
				jsonData.setCodeEnum(CodeEnum.ERROR_AUTOGRAPH);
				jsonData.setData(jsonUser);
			}
		} catch (Exception e) {
			jsonData.setCodeEnum(CodeEnum.ERROR);
			e.printStackTrace();
		}
		return jsonData;
	}

	/*
	 * 获取微信用户手机号码
	 */
	@RequestMapping("/user/getextenduserphone")
	public JSONObject getextenduserphone(@RequestParam Map<String, String> param) {
		JsonData jsonData = new JsonData();
		JSONObject jsonMsg=new JSONObject();
		try{
			int uid = Integer.valueOf(param.get("uid"));
			String encryptedDataStr = param.get("encryptedData");
			String ivStr =  param.get("iv");
	        log.error("redis 存储的字符串为333333333333333: "+ RedisUtil.get("sessionkey"+uid));
	        String session_key=RedisUtil.get("sessionkey"+uid);
			JSONObject jsonPhoneNum = WeiXinUtil.getUserInfo(encryptedDataStr,
					session_key, ivStr);		
	        log.error("加密验证成功");
			if(jsonPhoneNum.getString("purePhoneNumber")!=null&&jsonPhoneNum.getString("purePhoneNumber").toString()!=""){
		        log.error("手机号不为空");
				Extenduser userInfo=new Extenduser();
				userInfo.setExtid(uid);
				userInfo.setPhoneNumber(jsonPhoneNum.getString("purePhoneNumber").toString());
				if(extendService.updateExtendMobileByWeiXin(userInfo)>0){
					jsonMsg.put("msg", "User phoneNum save success");
					jsonMsg.put("status", "ok");				
				}else{
					jsonMsg.put("msg", "User phoneNum save fail");
					jsonMsg.put("status", "fail");				
				}
				jsonData.setData(jsonMsg);
			}else{
				jsonMsg.put("msg", "User phoneNum obtain fail");
				jsonMsg.put("status", "fail");
				jsonData.setCodeEnum(CodeEnum.ERROR);
				jsonData.setData(jsonMsg);
			}
		}catch(Exception e){
			jsonMsg.put("msg", "error");
			jsonMsg.put("status", "fail");
			jsonData.setCodeEnum(CodeEnum.ERROR);
			jsonData.setData(jsonMsg);
		}
		return jsonMsg;
	}
	/*
	 * 获取微信用户手机号码
	 */
	@RequestMapping("/user/createExtendUser")
	public JsonData createUser(@RequestParam Map<String, String> param) throws Exception {
		return extendService.createExtendUser(param);
	}
}
