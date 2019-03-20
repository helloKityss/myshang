package com.myshang.server.roomRuning.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myshang.server.room.model.Room;
import com.myshang.server.roomRuning.model.RoomRuning;

public interface RoomRuningDao {

   /**
    * 更新房间运营状态
    * @param roomRuning
    * @return
    */
   public int updateRoomRuningPrincess(RoomRuning roomRuning);
	
   /**
    * 查询服务员所服务的房间
    * @param bindWaiter
    * @return
    */
   public List<RoomRuning> getRoomRuningListByOpenUid(int bindWaiter);
   /**
    * 查询空闲房间信息
    * @param 
    * @return
    */
   public List<RoomRuning> getRoom(int belongBusid);
   /**
    * 查询房间信息
    * @param 
    * @return
    */
   public List<RoomRuning> getAllRoom(int belongBusid);
   /**
    * 查询房间信息
    * @param 
    * @return
    */
   public List<HashMap<String,Object>> getfreeRoom(int belongBusid);
	/**
	 * 查询房间收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public List<RoomRuning> getRoomincome(@Param("roomId") int roomId,@Param("querytime") int querytime);
//	/**
//	 * 副理根据房间Id查询房间
//	 * @author zhangkang
//	 * 2018年4月24日 上午11:12:37
//	 * @param houseNo
//	 * @return
//	 */
//	public RoomRuning getByIdRoom(int roomId);
	/**
	 * 副理根据房间Id查询房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<RoomRuning> getSumRoom(int belongBusid);
	/**
	 * 修改房间状态
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public void updateRoomState(@Param("openRoomUid") int openRoomUid,@Param("roomId") int roomId,@Param("roomState") int roomState);
	/**
	 * 根据服务员id查询是否绑定房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public RoomRuning getRewardWaiter(int isWaiterBind);
	/**
	 * 根据公主id查询是否绑定房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public RoomRuning getRewardPrincess(int bindPrincess);
	/**
	 * 查询房间号
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public RoomRuning getRooming(int openRoomUid);
	/**
	 * 根据公主id查询是否绑定房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public List<HashMap<String,String>> queryRoomWaiter(int openRoomUid);
	/**
	 * 新增房间运营表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public void saveRoomRuning(RoomRuning roomRuning);
	/**
	 * 查询房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public int queryRoom(int busid);
	/**
	 * 查询房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public int queryMakeRoom(int busid);
	/**
	 * 查询超消费房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public List<HashMap<String,Object>> consumption(int busid);
	/**
	 * 查询超消费房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> getconsumptionById(int roomId);
	/**
	 * 查询超消费房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> updateCstate(int roomId);
	
}
