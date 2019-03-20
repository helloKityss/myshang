package com.myshang.server.room.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface RoomService {
	/**
	 * 查询房间列表
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getRoomListing(Map<String, String> param);
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData saveRoom(Map<String, String> param);
	/**
	 * 修改房间信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData updateRoom(Map<String, String> param);
	/**
	 * 修改房间信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData deleteRoom(Map<String, String> param);
}
