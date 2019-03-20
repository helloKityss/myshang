package com.myshang.server.distribution.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.myshang.server.distribution.model.Distribution;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface DistributionDao {
	
	/**
	 * 查询分成比例
	 * @param income
	 * @return 执行行数
	 */
	public List<Distribution> getDistribution(int distriType);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public void saveDistribution(Distribution distribution);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public List<Map<String,Object>> getdistributions();
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public void deletedistribution(int distid);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public void updatedistribution(Distribution distribution);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public List<Distribution> getdistributionId(int distid);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public List<Distribution> getbusdistribution(int belongBusid);
	/**
	 * 分成比例
	 * @param income
	 * @return 执行行数
	 */
	public void updatbusedistribution(Distribution distribution);
}
