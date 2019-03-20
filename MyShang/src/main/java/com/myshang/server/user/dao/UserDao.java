package com.myshang.server.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.role.model.Role;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.user.model.User;


/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface UserDao {
	/**
	 * 统计同一房间玩家的投注份数
	 * @author lidongdong
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<User> queryfloat();
	/**
	 * 保存
	 * @author zhangkang
	 * 2018年9月12日 下午4:41:01
	 * @param house
	 */
	public void save(User user);
	
	/**
	 * 查询注册信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public List<User> getlisMaps(String openid);
	
	
	/**
	 * 更改个人信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public int updateUserInfoByWeiXin(User user);
	/**
	 * 更改个人信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public int updateUserMobileByWeiXin(User user);
	/**
	 * 查询用户基本信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserOpenId(String unionId)throws Exception;
	/**
	 * 新增用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int createUser(User user)throws Exception;
	/**
	 * 查询房间内服务员信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public HashMap<String, Object> getByWaiterRoom(int uid);
	/**
	 * 查询用户角色id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public User queryRolid(int uid);
	/**
	 * 查询用户商户id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public User queryBusid(int uid);
	/**
	 * 查询用户部门id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int queryDepid(int uid);
	/**
	 * 根据用户id查询是否绑定服务员
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getWaiterBind(int uid);
	/**
	 * 查询本商户总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getUserCount(int belongBusid);
	/**
	 * 查询是否有总经理角色id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public User getmanagerid(@Param("belongBusid") int belongBusid,@Param("belongRolid") int belongRolid);
	/**
	 * 通过部门id查询部门人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getdepartCount(int belongDepid);
	/**
	 * 查询商户总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getstaffCount(@Param("belongBusid") int belongBusid,@Param("belongDepid") int belongDepid);
	/**
	 * 根据uid查询用户信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public User findUserByUuid(int issueUserId);
	/**
	 * 根据uid查询用户信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public Map<String,Object> getUserinformation(int issueUserId);
	/**
	 * 获取所有公主信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public List<User> getAllisCanBind(@Param("belongBusid") int belongBusid,@Param("belongRolid") int belongRolid);
	/**
	 * 根据角色id查询用户id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getuserId(int belongRolid);
	/**
	 * 根据副理和商户id查询其绑定的公主id
	 * @param belongBusid
	 * @param viceManagerId
	 * @return
	 */
	public List<User> getUserinfoByViceprincess(@Param("belongBusid") int belongBusid,@Param("viceManagerId") int viceManagerId);
	/**
	 * 获取所有公主信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public List<User> getstaffAll(@Param("belongBusid") int belongBusid);
	/**
	 * 获取所有公主信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void updateUserinformation(User user);
	/**
	 * 获取所有公主信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void updateuserName(User user);
	/**
	 * 获取所有公主信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void deleteUser(int uid);
	/**
	 * 查询是否为副理
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getIsCanBind(int uid);
	/**
	 * 查询是否为总经理或老板
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public Role getIsManager(int uid);
	/**
	 * 查询商户内推人
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public int getcanInternalRun(int belongBusid);
	/**
	 * 查询房间内服务员信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public User getuseropen(int uid);
	/**
	 * 查询房间内服务员信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void updateuserisOnline(int uid);
	/**
	 * 查询房间内服务员信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getuserisOnline(@Param("belongBusid") int belongBusid,@Param("belongDepid") int belongDepid);
	/**
	 * 查询房间内服务员信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<User> getuseList(@Param("belongBusid") int belongBusid,@Param("belongDepid") int belongDepid);
	/**
	 * 注册
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Integer register(User user);
	/**
	 * 完善员工信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void perfect(User user);
	/**
	 * 完善员工信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void complete(User user);
	/**
	 * 根据手机号查找用户
	 * @author zhangkang
	 * 2018年2月7日 下午3:56:00
	 * @param phone
	 * @return
	 */
	public User findUserByPhone(String phoneNumber);
	/**
	 * 登录
	 * @author lidongdong
	 * 2018年5月4日 上午11:02:23
	 * @param phone
	 * @param pwd
	 * @return
	 */
	public int checkPassword(@Param("phoneNumber") String phoneNumber, @Param("passWord") String passWord);
	/**
	 * 完善员工信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void updatePassword(User user);
	/**
	 * 完善员工信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void saveOpenId(User user);
}
