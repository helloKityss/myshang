package com.myshang.server.reward.service;

import net.sf.json.JSONObject;

import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.reward.model.Reward;

/**
 * 
 * @author HL 2018.9.13.10:43
 */
public interface RewardService {

	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public int addRewardRecord(Reward reward) throws Exception;

	/**
	 * 支付回调处理方法
	 * 
	 * @param notityXml
	 * @param key(商户秘钥)
	 * @return
	 * @throws Exception
	 */
	public String payCallBackHandle(String notityXml, String key) throws Exception;

	/**
	 * 分成方法
	 * 
	 * @param callbackJson
	 * @return
	 */
	public JsonData distributionHandle(JSONObject callbackJson);
	/**
	 * 查询打赏金额
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData queryRewardMoney(Map<String, String> param);
}
