package com.myshang.server.attendance.service;

import java.util.List;
import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.role.model.Role;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface AttendanceService {
	/**
	 * 员工打卡功能
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData addAttendance(Map<String, String> param);
	/**
	 * 今日员工打卡详情
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getDayAttendance(Map<String, String> param);
	/**
	 * 查询个人打卡情况
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getOwnAttendance(Map<String, String> param);
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getBusinessLtdlog(Map<String, String> param);
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getRanking(Map<String, String> param);
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getDepartRanking(Map<String, String> param);
}
