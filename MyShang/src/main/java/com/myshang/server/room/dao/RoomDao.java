package com.myshang.server.room.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.room.model.Room;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface RoomDao {
	/**
	 * 查询所有房间列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Room> getRoomList(int belongBusid);
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void saveRoom(Room room);
	/**
	 * 删除房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deleteRoom(int rooid);
	/**
	 * 修改房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void updateRoom(Room room);
}
