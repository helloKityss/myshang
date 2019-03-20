package com.myshang.server.extenduser.service;

import java.util.List;
import java.util.Map;

import com.myshang.server.business.model.Business;
import com.myshang.server.common.JsonData;
import com.myshang.server.extenduser.model.Extenduser;
import com.myshang.server.user.model.User;

public interface ExtenduserService {
	/**
	 * 查询用户基本信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public Extenduser getExtenduserByOpenId(String openid)throws Exception;
	/**
	 * 添加用户信息
	 * @author zhangkang
	 * @param param
	 * @return
	 */
	public JsonData createExtendUser(Map<String, String> param) throws Exception;
	
	public int createExtendserRespId(Extenduser userInfo) throws Exception;
	
	/**
	 * 保存微信用户信息
	 * @param userInfo
	 * @return 执行行数
	 * @throws Exception
	 */
	public int updateExtenduserByWeiXin(Extenduser userInfo)throws Exception;
	/**
	 * 保存从微信获取的用户电话号码
	 * @param userInfo
	 * @return 执行行数
	 * @throws Exception
	 */
	public int updateExtendMobileByWeiXin(Extenduser userInfo) throws Exception;
}
