package com.myshang.server.vicePrincess.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myshang.server.vicePrincess.model.VicePrincess;

public interface VicePrincessDao {
	
	
	/**
	 * 添加副理和小姐的绑定关系
	 * @return 执行行数
	 */
	public int addVicePrincess(VicePrincess vicePrincess);
	
	/**
	 * 添加副理和小姐的绑定关系
	 * @return 执行行数
	 */
	public void createviceprin(VicePrincess vicePrincess);
	
	/**
	 * 根据副理id和公主id查询绑定关系
	 * @param vicePrincess
	 * @return
	 */
    public  List<VicePrincess> getVPByVidAndPid(@Param("viceManagerId") int viceManagerId,@Param("bindPrincessId") int bindPrincessId);
	/**
	 * 根据公主id判断是否绑定副理
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getVicePrincess(int bindPrincessId);
	/**
	 * 根据公主id判断是否绑定副理
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Map<String,Object>> getviceprin(int belongBusid);
	/**
	 * 根据公主id判断是否绑定副理
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deleteviceprin(int binid);
	/**
	 * 根据公主id判断是否绑定副理
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deleteviceprinUser(int binid);
	
	
}
