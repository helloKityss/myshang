package com.myshang.server.preciousCategory.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface PreciousService {
	/**
	 * 查询大件出货价钱
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param (houseNo:期号)
	 * @return
	 */
	public JsonData getPreciousinformation(Map<String, String> param);
}
