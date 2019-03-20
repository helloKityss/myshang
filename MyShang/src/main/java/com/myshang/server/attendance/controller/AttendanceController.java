package com.myshang.server.attendance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.account.service.AccountService;
import com.myshang.server.attendance.service.AttendanceService;
import com.myshang.server.common.JsonData;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	/**
	 * 员工打卡功能
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/addAttendance")
	public JsonData addAttendance(@RequestParam Map<String, String> param){
		return attendanceService.addAttendance(param);
	}
	/**
	 * 查询各部门打卡情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/getDayAttendance")
	public JsonData getDayAttendance(@RequestParam Map<String, String> param){
		return attendanceService.getDayAttendance(param);
	}
	/**
	 * 查询个人打卡情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/getOwnAttendance")
	public JsonData getOwnAttendance(@RequestParam Map<String, String> param){
		return attendanceService.getOwnAttendance(param);
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/getBusinessLtdlog")
	public JsonData getBusinessLtdlog(@RequestParam Map<String, String> param){
		return attendanceService.getBusinessLtdlog(param);
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/getRanking")
	public JsonData getRanking(@RequestParam Map<String, String> param){
		return attendanceService.getRanking(param);
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/attendance/getDepartRanking")
	public JsonData getDepartRanking(@RequestParam Map<String, String> param){
		return attendanceService.getDepartRanking(param);
	}
}
