package com.myshang.server.roomRuning.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

public interface RoomRuningService {
	/**
	 * 查询房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	public JsonData getRoom(Map<String, String> param);
	/**
	 * 查询房间收入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getRoomincome(Map<String, String> param);
	/**
	 * 副理根据房间Id查询房间
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getRoomList(Map<String, String> param);
	/**
	 * 根据服务员id查询服务房间
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData queryRoomWaiter(Map<String, String> param);
	/**
	 * 修改房间状态
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData updateRoomState(Map<String, String> param);
	/**
	 * 查询房间信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData queryRoom(Map<String, String> param);
}
