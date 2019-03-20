package com.myshang.server.room.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.room.dao.RoomDao;
import com.myshang.server.room.model.Room;
import com.myshang.server.room.service.RoomService;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
@Service
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RoomRuningDao roomRuningDao;
	private static final Logger LOGGER = Logger.getLogger(RoomServiceImpl.class);
	
	/**
	 * 查询房间列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getRoomListing(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		try {
			List<Room> RoomList=roomDao.getRoomList(belongBusid);
			jsonData.setData(RoomList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData saveRoom(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		String roomName=param.get("roomName");
		int roomType=Integer.valueOf(param.get("roomType"));
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		String remark=param.get("remark");
		try {
			Room room=new Room();
			room.setRoomName(roomName);
			room.setRoomType(roomType);
			room.setBelongBusid(belongBusid);
			room.setRemark(remark);
			RoomRuning roomRuning=new RoomRuning();
			roomDao.saveRoom(room);
			roomRuning.setRoomId(room.getRooid());
			roomRuning.setBelongBusid(belongBusid);
			roomRuningDao.saveRoomRuning(roomRuning);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData updateRoom(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int rooid=Integer.valueOf(param.get("rooid"));
		String roomName=param.get("roomName");
		int roomType=Integer.valueOf(param.get("roomType"));
		String remark=param.get("remark");
		try {
			Room room=new Room();
			room.setRooid(rooid);
			room.setRoomName(roomName);
			room.setRoomType(roomType);
			room.setRemark(remark);
			roomDao.updateRoom(room);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改房间信息失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData deleteRoom(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int rooid=Integer.valueOf(param.get("rooid"));
		try {
			roomDao.deleteRoom(rooid);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改房间信息失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
