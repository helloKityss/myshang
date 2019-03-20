package com.myshang.server.vicePrincess.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

public interface VicePrincessService {
    
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData createviceprin(Map<String, String> param);	
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData getviceprin(Map<String, String> param);
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData deleteviceprin(Map<String, String> param);
}
