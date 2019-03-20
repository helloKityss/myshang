package com.myshang.server.room.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.room.service.RoomService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class RoomController {
	@Autowired
	private RoomService roomService;
	/**
	 * 查询房间列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/getRoomListing")
	public JsonData getRoomListing(@RequestParam Map<String, String> param){
		return roomService.getRoomListing(param);
	}
	/**
	 * 新增房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/saveRoom")
	public JsonData saveRoom(@RequestParam Map<String, String> param){
		return roomService.saveRoom(param);
	}
	/**
	 * 修改房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/updateRoom")
	public JsonData updateRoom(@RequestParam Map<String, String> param){
		return roomService.updateRoom(param);
	}
	/**
	 * 删除房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/deleteRoom")
	public JsonData deleteRoom(@RequestParam Map<String, String> param){
		return roomService.deleteRoom(param);
	}
}
