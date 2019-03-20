package com.myshang.server.preciousSale.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface PreciousSaleService {
	/**
	 * 查询已开奖的房间详情
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData createPreciousSale(Map<String, String> param);
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData getPreciousSale(Map<String, String> param);
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData getPreciousDaySale(Map<String, String> param);
	/**
	 * 查询大件出货价钱
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData getPreciousSaleMoney(Map<String, String> param);
	/**
	 * 删除录入大件
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData deletePreciousSale(Map<String, String> param);
	
}
