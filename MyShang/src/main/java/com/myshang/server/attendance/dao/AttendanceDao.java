package com.myshang.server.attendance.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.attendance.model.Attendance;
import com.myshang.server.user.model.User;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface AttendanceDao {
	/**
	 * 员工打卡功能
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void addAttendance(Attendance attendance);
	/**
	 * 今日打卡详情
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public List<User> getDayAttendance(int belongDepid);
	/**
	 * 今天打卡人数情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getAttendanceCount(@Param("belongDepid")int belongDepid,@Param("attendTime")String attendTime);
	/**
	 * 今天打卡人数情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public String getAttendancego(@Param("userId")int userId,@Param("attendTime")String attendTime);
	/**
	 * 今天打卡人数情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public String getAttendanceoff(@Param("userId")int userId,@Param("attendTime")String attendTime);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getPunchCount(@Param("belongBusid")int belongBusid,@Param("belongDepid")int belongDepid);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Attendance> getOwnAttendance(int uid);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public String getTwoAttendance(@Param("userId")int userId,@Param("formatTime")String formatTime);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public String getThreeAttendance(@Param("userId")int userId,@Param("formatTime")String formatTime);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deleteAttendance(int uid);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getRanking(@Param("userId")int userId);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getDepartRanking(@Param("userId")int userId,@Param("attendTime")String attendTime);
	/**
	 * 查询商户已打卡总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getDepartdayRanking(@Param("userId")int userId,@Param("beforeTime")String beforeTime,@Param("afterTime")String afterTime);
	
}
