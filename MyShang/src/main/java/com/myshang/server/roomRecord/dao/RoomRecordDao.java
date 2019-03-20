package com.myshang.server.roomRecord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.role.model.Role;
import com.myshang.server.roomRecord.model.RoomRecord;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.user.model.User;


/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface RoomRecordDao {
	/**
	 * 添加开房信息
	 * @author zhangkang
	 * 2018年5月4日 上午11:02:23
	 * @param phone
	 * @param pwd
	 * @return
	 */
	public void saveOpenRoom(RoomRecord roomRecord);
	/**
	 * 查询开房次数
	 * @author zhangkang
	 * 2018年5月4日 上午11:02:23
	 * @param phone
	 * @param pwd
	 * @return
	 */
	public List<HashMap<String, Object>> queryRoomRecord(int belongBusid);
}
