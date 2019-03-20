package com.myshang.server.roomRuning.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.model.Department;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.preciousSale.dao.PreciousSaleDao;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.room.dao.RoomDao;
import com.myshang.server.room.model.Room;
import com.myshang.server.roomRecord.dao.RoomRecordDao;
import com.myshang.server.roomRecord.model.RoomRecord;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.roomRuning.service.RoomRuningService;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;

@Service
@Transactional
public class RoomRuningServiceImpl implements RoomRuningService {
	
	@Autowired
	private RoomRuningDao roomRuningdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private RoomRecordDao roomRecordDao;
	@Autowired
	private PreciousSaleDao preciousSaleDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	@Override
	public JsonData getRoom(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int uid=Integer.valueOf(param.get("uid"));
		try {
			User userEney=userdao.queryBusid(uid);
//			List<Room> RoomAllList=roomdao.getRoomList(userEney.getBelongBusid());
			//int housenumber=roomRuningdao.getAllRoomNumber();
			List<RoomRuning> RoomList=roomRuningdao.getRoom(userEney.getBelongBusid());
			List<HashMap<String,Object>> freeRoomList=roomRuningdao.getfreeRoom(userEney.getBelongBusid());
			jsonData.setData(freeRoomList);
			jsonData.setList(RoomList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询房间收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getRoomincome(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int roomId=Integer.valueOf(param.get("roomId"));
		int querytime = 7;
		try {
			List<RoomRuning> RoomRuningList=roomRuningdao.getRoomincome(roomId,querytime);
			jsonData.setData(RoomRuningList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
//	/**
//	 * 副理根据房间Id查询房间
//	 * @author zhangkang
//	 * 2018年4月24日 上午11:12:37
//	 * @param param
//	 * @return
//	 */
//	@Override
//	public JsonData getByIdRoom(Map<String, String> param) {
//		JsonData jsonData = new JsonData();
//		int roomId=Integer.valueOf(param.get("roomId"));
//		List<Object> PrincessList=new ArrayList<Object>();
//		try {
//			RoomRuning RoomRuning=roomRuningdao.getByIdRoom(roomId);
//			if(RoomRuning == null){
//				jsonData.setCodeEnum(CodeEnum.ERROR_ROOM);
//				return jsonData;
//			}
//			HashMap<String, Object> Waiter=userdao.getByWaiterRoom(RoomRuning.getOpenRoomUid());
//			if(RoomRuning.getBindPrincesses()!=null){
//				String substring = RoomRuning.getBindPrincesses().substring(0, RoomRuning.getBindPrincesses().length());//截取最后一个
//				LOGGER.error("截取公主字符串后"+substring);
//				String[] split = substring.split(",");//以逗号分割
//				for(int i=0;i<split.length;i++){
//					if(StringUtils.isEmpty(split[i])){
//						continue;
//					}
//					HashMap<String, Object> userPrincess=userdao.getByWaiterRoom(Integer.valueOf(split[i]));
//					if(userPrincess!=null){
//						PrincessList.add(userPrincess);
//					}
//				}
//			}
//			Map map=new HashMap();
//			map.put("RoomRuning", RoomRuning);
//			if(Waiter!=null){
//				map.put("Waiterdata", Waiter);
//			}else{
//				map.put("Waiterdata", "");
//			}
//			if(PrincessList.size()>0){
//				map.put("PrincessList", PrincessList);
//			}else{
//				map.put("PrincessList", "");
//			}
//			jsonData.setCodeEnum(CodeEnum.SUCCESS);
//		} catch (Exception e) {
//			LOGGER.error("查询房间收入信息出错",e);
//			jsonData.setCodeEnum(CodeEnum.ERROR);
//		}
//		return jsonData;
//	}
	/**
	 * 副理根据房间Id查询房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getRoomList(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int busid=Integer.valueOf(param.get("busid"));
		try {
			List<RoomRuning> RoomRuningList=roomRuningdao.getSumRoom(busid);
			List<RoomRuning> RoomUseList=new ArrayList<RoomRuning>();
			List<RoomRuning> RoomOpenList=new ArrayList<RoomRuning>();
			List<RoomRuning> RoomfreeList=new ArrayList<RoomRuning>();
			List<RoomRuning> RoomReserveList=new ArrayList<RoomRuning>();
			List<RoomRuning> RoomconsumptionList=new ArrayList<RoomRuning>();
			for(int i=0 ; i < RoomRuningList.size(); i++ ){
				RoomRuningList.get(i).setMoney(RoomRuningList.get(i).getMoney().substring(1));
				RoomRuningList.get(i).setSumMoney(preciousSaleDao.getRoomSale(RoomRuningList.get(i).getRoomId()));
				RoomRuningList.get(i).setWaiter(userdao.getByWaiterRoom(RoomRuningList.get(i).getBindWaiter()));
				if(RoomRuningList.get(i).getBindPrincesses()!=null){
					String substring = RoomRuningList.get(i).getBindPrincesses().substring(0, RoomRuningList.get(i).getBindPrincesses().length());//截取最后一个
					LOGGER.error("截取公主字符串后"+substring);
					String[] split = substring.split(",");//以逗号分割
					for(int j=0;j<split.length;j++){
						if(StringUtils.isEmpty(split[j])){
							continue;
						}
						HashMap<String, Object> userPrincess=userdao.getByWaiterRoom(Integer.valueOf(split[j]));
						if(userPrincess!=null){
							RoomRuningList.get(i).getPrincessess().add(userPrincess);
						}
					}
				}
				if(RoomRuningList.get(i).getRoomState()!=0){
					RoomUseList.add(RoomRuningList.get(i));
				}else if(RoomRuningList.get(i).getRoomState()==1){
					RoomOpenList.add(RoomRuningList.get(i));
				}else if(RoomRuningList.get(i).getRoomState()==0){
					RoomfreeList.add(RoomRuningList.get(i));
				}else if(RoomRuningList.get(i).getRoomState()==2){
					RoomReserveList.add(RoomRuningList.get(i));
				}else if(RoomRuningList.get(i).getRoomState()==3){
					RoomconsumptionList.add(RoomRuningList.get(i));
				}
			}
			Map map=new HashMap();
			map.put("RoomRuningList", RoomRuningList);
			map.put("RoomUseSize", RoomUseList.size());
			map.put("RoomOpenSize", RoomOpenList.size());
			map.put("RoomfreeSize", RoomfreeList.size());
			map.put("RoomReserveSize", RoomReserveList.size());
			map.put("RoomconsumptionSize", RoomconsumptionList.size());
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 修改房间状态
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData updateRoomState(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int openRoomUid=Integer.valueOf(param.get("uid"));
		int roomId=Integer.valueOf(param.get("roomId"));
		int roomState=Integer.valueOf(param.get("roomState"));
		try {
			if(roomState == 1){
				roomRuningdao.updateRoomState(openRoomUid,roomId,roomState);
				RoomRecord roomRecord=new RoomRecord();
				roomRecord.setOpenRoomUid(openRoomUid);
				roomRecord.setOpentime(new Timestamp(System.currentTimeMillis()));
				roomRecordDao.saveOpenRoom(roomRecord);
			}else{
				int openRoomUids=0;
				roomRuningdao.updateRoomState(openRoomUids,roomId,roomState);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询服务员绑定的房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData queryRoomWaiter(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int uid=Integer.valueOf(param.get("uid"));
		try {
			List<HashMap<String,String>> RoomId=roomRuningdao.queryRoomWaiter(uid);
			Map map=new HashMap();
			map.put("RoomId", RoomId);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询服务员绑定的房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData queryRoom(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int busid=Integer.valueOf(param.get("busid"));
		try {
			int SunCount=roomRuningdao.queryRoom(busid);
			int makeCount=roomRuningdao.queryMakeRoom(busid);
			List<HashMap<String, Object>> list=roomRecordDao.queryRoomRecord(busid);
			List<HashMap<String, Object>> consumptionlist=roomRuningdao.consumption(busid);
			List<HashMap<String, Object>> roomSmallList= new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> roomInList= new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> roomLargeList= new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> roomLuxuryist= new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> ModuleNameMap= new HashMap<String, Object>();
			for (int i=0 ; i<consumptionlist.size() ; i++){
					if(consumptionlist.get(i).get("roomType").toString().equals("1")){
						roomSmallList.add(consumptionlist.get(i));
					}else if(consumptionlist.get(i).get("roomType").toString().equals("2")){
						roomInList.add(consumptionlist.get(i));
					}else if(consumptionlist.get(i).get("roomType").toString().equals("3")){
						roomLargeList.add(consumptionlist.get(i));
					}else if(consumptionlist.get(i).get("roomType").toString().equals("4")){
						roomLuxuryist.add(consumptionlist.get(i));
					}
				}
			ModuleNameMap.put("RoomManagement","房间管理");
			ModuleNameMap.put("UpdateTime","更新时间");
			ModuleNameMap.put("RoomDetails","房间详情");
			ModuleNameMap.put("openRooms","当前开房数");
			ModuleNameMap.put("RoomsNumber","房间总数");
			ModuleNameMap.put("RoomsNumber","房间总数");
			ModuleNameMap.put("ReservationRanking","订房人开房排名");
			ModuleNameMap.put("OverConsumption", "超消费房间");
			ModuleNameMap.put("more", "更多");
			Map map=new HashMap();
			map.put("SunCount", SunCount);
			map.put("makeCount", makeCount);
			map.put("list", list);
			map.put("consumptionlist", consumptionlist);
			map.put("roomSmallList", roomSmallList);
			map.put("roomInList", roomInList);
			map.put("roomLargeList", roomLargeList);
			map.put("roomLuxuryist", roomLuxuryist);
			map.put("ModuleNameMap", ModuleNameMap);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
