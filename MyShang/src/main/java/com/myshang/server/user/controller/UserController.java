package com.myshang.server.user.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.business.model.Business;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.RedisUtil;
import com.myshang.server.common.StringUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.consts.RedisKeyEnum;
import com.myshang.server.role.model.Role;
import com.myshang.server.role.service.RoleService;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.UserService;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	public Logger log = Logger.getLogger(UserController.class); // 日志

	private static final long serialVersionUID = 2065425065755820914L;


	//String appid = CmsConstants.WEIXIN_APPID;// "wxff2af56a454e9c05";// 服务号
	//String secret = CmsConstants.WEIXIN_SECRET;// "8e345c968c6b375a97b278126b8cc33c";//
												// 公众号密钥
	//String miniappid = CmsConstants.WEIXIN_MINI_APPID;// wx0c3bdffa78a234da
														// 小程序服务号
	//String minisecret = CmsConstants.WEIXIN_MINI_SECRET;// a2044bd4b85ea64931ba097f7fcade67
	
	/*
	 * 获取凭证校检接口
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/user/wechatlogin")
	public JSONObject login(HttpServletRequest request,@RequestParam Map<String, String> param) {
        log.error("开始调用login接口");
		JSONObject jsonUser = new JSONObject();
		try {
			String usercode = param.get("code");
			int uid = Integer.valueOf(param.get("uid"));
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
				userService.saveOpenId(jsonResp,uid);
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
	@RequestMapping("/user/getuserinfobyweixin")
	public JsonData getUserInfoByWeiXin(@RequestParam Map<String, String> param) {	
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
	        log.error("redis 存储的字符串为222222222: "+ RedisUtil.get("session_key"+id));
	        String session_key=RedisUtil.get("session_key"+id);
			if (WeiXinUtil.getSha1(rawData + session_key).equals(signature)) {
				jsonUser = WeiXinUtil.getUserInfo(encryptedDataStr,
						session_key, ivStr);
				User userinfo = new User();
				userinfo.setUid(id);
				log.error("存储信息为2222222 "+ jsonUser.getString("nickName"));
				userinfo.setAvatarUrl(jsonUser.getString("avatarUrl"));
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
	/**
    * 根据用户id查询用户信息
    * @param param
    * @return
    * @throws Exception
    */
	@RequestMapping("/user/getUserByUid")
	public JsonData getUserByUid(@RequestParam Map<String, String> param) throws Exception{						
		return userService.findUserByUuid(param);
	}
	/*
	 * 获取用户角色
	 */
	@RequestMapping("/user/queryRolid")
	public JsonData queryRolid(@RequestParam Map<String, String> param) throws Exception {
		return userService.queryRolid(param);
	}
	/*
	 * 查询用户信息
	 */
	@RequestMapping("/user/getUserinformation")
	public JsonData getUserinformation(@RequestParam Map<String, String> param) throws Exception {
		return userService.getUserinformation(param);
	}
	/*
	 * 查询副理绑定的公主
	 */
	@RequestMapping("/user/getAllisCanBind")
	public JsonData getAllisCanBind(@RequestParam Map<String, String> param) throws Exception {
		return userService.getAllisCanBind(param);
	}
	/*
	 * 查询所有员工信息
	 */
	@RequestMapping("/user/getstaffAll")
	public JsonData getstaffAll(@RequestParam Map<String, String> param) throws Exception {
		return userService.getstaffAll(param);
	}
	/*
	 * 新增用户信息
	 */
	@RequestMapping("/user/updateUserinformation")
	public JsonData updateUserinformation(@RequestParam Map<String, String> param) throws Exception {
		return userService.updateUserinformation(param);
	}
	/*
	 * 修改用户姓名
	 */
	@RequestMapping("/user/updateuserName")
	public JsonData updateuserName(@RequestParam Map<String, String> param) throws Exception {
		return userService.updateuserName(param);
	}
	/*
	 * 删除用户信息
	 */
	@RequestMapping("/user/deleteUser")
	public JsonData deleteUser(@RequestParam Map<String, String> param) throws Exception {
		return userService.deleteUser(param);
	}
	/*
	 * 生成小程序注册码
	 */
	@RequestMapping("/qrcode/addqrcode")
	public JsonData addqrcode(@RequestParam Map<String, String> param) throws Exception {
		JsonData jsonData=new JsonData();
		int busid=Integer.valueOf(param.get("busid"));
		String qrcodeUrl=userService.getqrcodeUrl(busid);
		if(!StringUtils.isEmpty(qrcodeUrl)){
			jsonData.setData(qrcodeUrl);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
			return jsonData;
		}
		// 拼接请求地址
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"+"&appid="+CmsConstants.WEIXIN_MINI_APPID+"&secret=" + CmsConstants.WEIXIN_MINI_SECRET;
		// 进行网络请求,访问url接口
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String userinfoStr = EntityUtils.toString(httpEntity, "utf-8");
		JSONObject jsonResp = JSONObject.fromObject(userinfoStr);
		jsonData=userService.addqrcode(jsonResp,busid);
		return jsonData;
	}
	/*
	 * 修改用户姓名
	 */
	@RequestMapping("/user/updateuserisOnline")
	public JsonData updateuserisOnline(@RequestParam Map<String, String> param) throws Exception {
		return userService.updateuserisOnline(param);
	}
	/*
	 * 修改用户姓名
	 */
	@RequestMapping("/user/getuserisOnline")
	public JsonData getuserisOnline(@RequestParam Map<String, String> param) throws Exception {
		return userService.getuserisOnline(param);
	}
	/*
	 * 注册用户
	 */
	@RequestMapping("/user/register")
	public JsonData register(@RequestParam Map<String, String> param) throws Exception {
		return userService.register(param);
	}
	/*
	 * 完善个人信息
	 */
	@RequestMapping("/user/perfect")
	public JsonData perfect(@RequestParam Map<String, String> param) throws Exception {
		return userService.perfect(param);
	}
	/*
	 * 完成注册
	 */
	@RequestMapping("/user/complete")
	public JsonData complete(@RequestParam Map<String, String> param) throws Exception {
		return userService.complete(param);
	}
	/**
     * 新增商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/user/uploadAvatarUrl")
	public JsonData uploadAvatarUrl(@RequestBody MultipartFile file) throws Exception{						
		return userService.uploadAvatarUrl(file);
	}
	/**
     * 验证码登陆
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/user/loginCode")
	public JsonData loginCode(@RequestParam Map<String, String> param) throws Exception{						
		return userService.loginCode(param);
	}
	/**
     * 密码登陆
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/user/loginPassword")
	public JsonData loginPassword(@RequestParam Map<String, String> param) throws Exception{						
		return userService.loginPassword(param);
	}
	/**
     * 修改密码
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/user/updatePassword")
	public JsonData updatePassword(@RequestParam Map<String, String> param) throws Exception{						
		return userService.updatePassword(param);
	}
}