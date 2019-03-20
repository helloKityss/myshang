package com.myshang.server.roomRuning.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.role.service.RoleService;
import com.myshang.server.roomRuning.service.RoomRuningService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class RoomRuningController {
	@Autowired
	private RoomRuningService roomRuningService;
	/**
	 * 查询房间信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/getRoom")
	public JsonData getRoom(@RequestParam Map<String, String> param){
		return roomRuningService.getRoom(param);
	}
	/**
	 * 查询房间收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/getRoomincome")
	public JsonData getRoomincome(@RequestParam Map<String, String> param){
		return roomRuningService.getRoomincome(param);
	}
	/**
	 * 查询房间详情
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/getRoomList")
	public JsonData getRoomList(@RequestParam Map<String, String> param){
		return roomRuningService.getRoomList(param);
	}
	/**
	 * 根据房间Id修改房间状态
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/updateRoomState")
	public JsonData updateRoomState(@RequestParam Map<String, String> param){
		return roomRuningService.updateRoomState(param);
	}
	/**
	 * 根据服务员id查询服务房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/room/queryRoomWaiter")
	public JsonData queryRoomPrincess(@RequestParam Map<String, String> param){
		return roomRuningService.queryRoomWaiter(param);
	}
//	/**
//	 * 根据服务员id查询服务房间
//	 * @author zhangkang
//	 * 2018年4月24日 上午11:12:37
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping("/room/queryRoom")
//	public JsonData queryRoom(@RequestParam Map<String, String> param){
//		return roomRuningService.queryRoom(param);
//	}
}
