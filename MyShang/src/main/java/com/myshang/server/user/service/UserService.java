package com.myshang.server.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.business.model.Business;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.JsonList;
import com.myshang.server.user.model.User;

import net.sf.json.JSONObject;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface UserService {
	/**
	 * 查询用户基本信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public JsonList<User> getOpenId(String openid)throws Exception;
	
	/**
	 * 保存微信用户信息
	 * @param userInfo
	 * @return 执行行数
	 * @throws Exception
	 */
	public int updateUserInfoByWeiXin(User userInfo)throws Exception;
	/**
	 * 保存从微信获取的用户电话号码
	 * @param userInfo
	 * @return 执行行数
	 * @throws Exception
	 */
	public int updateUserMobileByWeiXin(User userInfo) throws Exception;
	/*
	 * 获取用户角色
	 */
	public JsonData queryRolid(Map<String, String> param) throws Exception;
	/*
	 * 获取用户角色
	 */
	public Map<String,Object> getReward(int uid) throws Exception;
	/*
	 * 获取用户信息
	 */
	
	public JsonData findUserByUuid(Map<String, String> param)throws Exception;
	/*
	 * 获取用户信息
	 */
	public JsonData getUserinformation(Map<String, String> param)throws Exception;
	/*
	 * 获取副理绑定公主信息
	 */
	public JsonData getAllisCanBind(Map<String, String> param)throws Exception;
	/*
	 * 查询所有员工信息
	 */
	public JsonData getstaffAll(Map<String, String> param)throws Exception;
	/*
	 * 修改员工信息
	 */
	public JsonData updateUserinformation(Map<String, String> param)throws Exception;
	/*
	 * 修改员工信息
	 */
	public JsonData updateuserName(Map<String, String> param)throws Exception;
	/*
	 * 删除员工信息
	 */
	public JsonData deleteUser(Map<String, String> param)throws Exception;
	/*
	 * 删除员工信息
	 */
	public JsonData addqrcode(JSONObject jsonResp,int busid)throws Exception;
	/*
	 * 查询商户是否绑定二维码
	 */
	public String getqrcodeUrl(int busid)throws Exception;
	/*
	 * 删除员工信息
	 */
	public JsonData updateuserisOnline(Map<String, String> param)throws Exception;
	/*
	 * 删除员工信息
	 */
	public JsonData getuserisOnline(Map<String, String> param)throws Exception;
	/*
	 * 注册
	 */
	public JsonData register(Map<String, String> param)throws Exception;
	/*
	 * 完善信息
	 */
	public JsonData perfect(Map<String, String> param)throws Exception;
	/*
	 * 完善信息
	 */
	public JsonData uploadAvatarUrl(MultipartFile file)throws Exception;
	/*
	 * 完成注册
	 */
	public JsonData complete(Map<String, String> param)throws Exception;
	/*
	 * 验证码登陆
	 */
	public JsonData loginCode(Map<String, String> param)throws Exception;
	/*
	 * 密码登陆
	 */
	public JsonData loginPassword(Map<String, String> param)throws Exception;
	/*
	 * 密码登陆
	 */
	public JsonData updatePassword(Map<String, String> param)throws Exception;
	/*
	 * 密码登陆
	 */
	public JsonData saveOpenId(JSONObject jsonResp,int uid)throws Exception;
}
