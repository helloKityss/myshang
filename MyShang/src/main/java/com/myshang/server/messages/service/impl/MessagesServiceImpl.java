package com.myshang.server.messages.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.gson.JsonArray;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.HttpUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.SignUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.label.dao.LabelDao;
import com.myshang.server.messages.dao.MessagesDao;
import com.myshang.server.messages.model.Messages;
import com.myshang.server.messages.service.MessagesService;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
@Transactional
public class MessagesServiceImpl implements MessagesService {
	private static final Logger LOGGER = Logger.getLogger(MessagesServiceImpl.class);
	@Autowired
	MessagesDao messagesdao;
	@Autowired
	LabelDao labelDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserDao userDao;
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData messagesList(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int businessId=Integer.valueOf(param.get("businessId"));
		int querytime = Integer.valueOf(param.get("querytime"));
		try {
			List<Messages> list = messagesdao.messagesList(businessId,querytime);
			for(int i=0;i<list.size();i++){
				User user=userDao.queryRolid(list.get(i).getIssueUserId());
				Role role=roleDao.getRoleName(user.getBelongRolid());
				list.get(i).setRoleName(role.getRoleName());
				list.get(i).setAvatarUrl(user.getAvatarUrl());
				list.get(i).setUserName(user.getUserName());
			}
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间列表出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData getMessagesState(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int businessId= Integer.valueOf(param.get("businessId"));
		int querytime = Integer.valueOf(param.get("querytime"));
		try {
			List<Messages> list = messagesdao.getMessagesState(businessId,querytime);
			for(int i=0;i<list.size();i++){
				User user=userDao.queryRolid(list.get(i).getIssueUserId());
				if(user != null){
					Role role=roleDao.getRoleName(user.getBelongRolid());
					list.get(i).setRoleName(role.getRoleName());
					list.get(i).setAvatarUrl(user.getAvatarUrl());
					list.get(i).setUserName(user.getUserName());
				}
				if(list.get(i).getMessageType() == 2){
					StringBuffer content=new StringBuffer(list.get(i).getMessageContent());
					if( list.get(i).getLabelSet() != null || !StringUtils.isEmpty(list.get(i).getLabelSet())){
						String substring = list.get(i).getLabelSet().substring(0, list.get(i).getLabelSet().length());//截取最后一个
						LOGGER.error("截取投诉标签字符串后"+substring);
						String[] split = substring.split(",");//以逗号分割
						for(int k=0;k<split.length;k++){
							if(StringUtils.isEmpty(split[k])){
								continue;
							}
							String labelName=labelDao.getLabelForId(Integer.valueOf(split[k]));
							content.append("/"+labelName);
							list.get(i).setMessageContent(content.toString());
						}
					}
					
				}
			}
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间列表出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData getMessagesHang(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int businessId=Integer.valueOf(param.get("businessId"));
		int querytime = Integer.valueOf(param.get("querytime"));
		Map map=new HashMap();
		try {
			List<Messages> list = messagesdao.getMessagesHang(businessId,querytime);
			for(int i=0;i<list.size();i++){
				User user=userDao.queryRolid(list.get(i).getIssueUserId());
				Role role=roleDao.getRoleName(user.getBelongRolid());
				list.get(i).setRoleName(role.getRoleName());
				list.get(i).setAvatarUrl(user.getAvatarUrl());
				list.get(i).setUserName(user.getUserName());
				if(list.get(i).getMessageState()==1 || list.get(i).getMessageState()==2){
					map.put("waiterdata", 1);
				}else{
					map.put("waiterdata", 0);
				}
			}
			map.put("list", list);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询任务列表出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	@Override
	public JsonData createMessages(Map<String, String> param) {
		LOGGER.error("进入createMessages方法" );	
		JsonData jsonData = new JsonData();		
		try {
			Messages messages=new Messages();
			int messageType= Integer.valueOf(param.get("messageType"));	
			String messageContent = param.get("messageContent");		
			messages.setIssueUserId(Integer.valueOf(param.get("uid")));
			messages.setRoomName(param.get("roomName"));
			messages.setComplainId(Integer.valueOf(param.get("complainId")));
			messages.setLabelSet(param.get("labelSet"));
			messages.setBusinessId(Integer.valueOf(param.get("businessId")));
			messages.setMessageContent(messageContent);
			messages.setMessageType(messageType);
			if(messageType == 1){
				messages.setMessageState(1);
			}else{
				messages.setMessageState(-1);
			}
			messages.setCreateTime(new Timestamp(System.currentTimeMillis()));
			messagesdao.createMessagesInfo(messages);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加消息信息出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	@Override
	public JsonData updateMessages(Map<String, String> param) {
			JsonData jsonData = new JsonData();
			try {
				int mesid= Integer.valueOf(param.get("mesid"));
				int messageState= Integer.valueOf(param.get("messageState"));
				Messages messages=new Messages();
				messages.setMessageState(messageState);
				messages.setMesid(mesid);
				jsonData.setCodeEnum(CodeEnum.SUCCESS);
				messagesdao.updateMessages(messages);
			} catch (Exception e) {
				LOGGER.error("添加用户信息出错", e);
				jsonData.setCodeEnum(CodeEnum.ERROR);
			}
			return jsonData;
		}
	@Override
    public JsonData MessagesEncryption(Map<String, String> param) throws Exception{  
		JsonData jsonData = new JsonData();
		HttpServletResponse response=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
        String signature = param.get("signature");  
        // 时间戳  
        String timestamp = param.get("timestamp");
        // 随机数  
        String nonce = param.get("nonce");  
        // 随机字符串  
        String echostr = param.get("echostr");
        
        
      PrintWriter out = null;  
      try {  
          out = response.getWriter();
          // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败  
          if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
              out.print(echostr);
              out.flush();   //这个地方必须画重点，消息推送配置Token令牌错误校验失败，搞了我很久，必须要刷新！！！！！！！
          } 
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
      } catch (IOException e) {  
		LOGGER.error("消息加密失败", e);
    	jsonData.setCodeEnum(CodeEnum.ERROR);
          e.printStackTrace();  
      } finally {  
          out.close();  
          out = null; 
    	  
      }
	return jsonData;  
    }
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData getTemplate(Map<String, String> param) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RestTemplate rest = new RestTemplate();
		JsonData jsonData = new JsonData();
		String touser=param.get("touser");
		String template_id=param.get("template_id");
		String page=param.get("page");
		String form_id=param.get("form_id");
		String data=param.get("data");
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"+"&appid="+CmsConstants.WEIXIN_MINI_APPID+"&secret=" + CmsConstants.WEIXIN_MINI_SECRET;
			// 进行网络请求,访问url接口
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String userinfoStr = EntityUtils.toString(httpEntity, "utf-8");
			JSONObject jsonResp = JSONObject.fromObject(userinfoStr);
			System.out.println("11111111---" + jsonResp);
			if (jsonResp != null && !jsonResp.equals("")) {
				String AccessTokenurl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+jsonResp.get("access_token");
				Map<String,Object> params = new HashMap<>();
				params.put("touser", touser);
				params.put("template_id", template_id);
				params.put("page", page);
				params.put("form_id", form_id);
				params.put("data", data);
	            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	            org.springframework.http.HttpEntity requestEntity = new org.springframework.http.HttpEntity(param, headers);
	            ResponseEntity<byte[]> entity = rest.exchange(AccessTokenurl, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
	            byte[] result = entity.getBody();
	            JSONArray jsonArry=JSONArray.fromObject(result);
	            jsonData.setData(jsonArry);
				//装填参数
				jsonData.setCodeEnum(CodeEnum.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("查询模板信息失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}finally {
			try {
				if (httpClient != null) {
					// 释放资源
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonData;
	}
}
