package com.myshang.server.department.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface DepartmentService {
	/**
	 * 查询已开奖的房间详情
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getDepartment(Map<String, String> param);
	/**
	 * 打卡查询所有部门
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getDepartmentAll(Map<String, String> param);
	/**
	 * 打卡查询所有部门
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData saveDepartment(Map<String, String> param);
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData deleteDepartment(Map<String, String> param);
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData updateDepartment(Map<String, String> param);
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getInformation(Map<String, String> param);
	
}
