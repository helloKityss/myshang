package com.myshang.server.qrcode.service;

import net.sf.json.JSONObject;

import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.qrcode.model.Qrcode;

/**
 * 二维码Service接口
 * @author HL
 *
 */
public interface QrcodeService {
	/**
	 * 根据二维码id查询相关数据
	 * @param qrcid
	 * @return 
	 */
	public Qrcode getQrcodeById(int qrcid) throws Exception;	
	
	/**
	 * 判断扫码人与被扫人关系
	 * @param state
	 * @return
	 */
	public JSONObject RelationshipJudgment(String state,int qrcidoruid,String openid) throws Exception;
	
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData getqrcode(Map<String, String> param);
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData getqrcodeAll(Map<String, String> param);
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public JsonData saveQRcode(Qrcode qrcode);
	/**
	 * 查询最大的二维码id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public int getqrcodeId();

}
