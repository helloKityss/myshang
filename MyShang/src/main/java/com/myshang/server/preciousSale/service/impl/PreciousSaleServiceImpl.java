package com.myshang.server.preciousSale.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.preciousCategory.dao.PreciousDao;
import com.myshang.server.preciousCategory.model.Precious;
import com.myshang.server.preciousSale.dao.PreciousSaleDao;
import com.myshang.server.preciousSale.model.PreciousSale;
import com.myshang.server.preciousSale.service.PreciousSaleService;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
@Service
@Transactional
public class PreciousSaleServiceImpl implements PreciousSaleService {
	@Autowired
	private PreciousSaleDao preciousSaleDao;
	@Autowired
	private RoomRuningDao roomRuningdao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData createPreciousSale(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int preciousId=Integer.valueOf(param.get("preciousId"));
		String preciousName=param.get("preciousName");
		String preciousPrice=param.get("preciousPrice");
		int saleCount=Integer.valueOf(param.get("saleCount"));
		int saleRoomId=Integer.valueOf(param.get("saleRoomId"));
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		String preciousImage=param.get("preciousImage");
		String totalMoney=param.get("totalMoney");
		try {
			PreciousSale preciousSale = new PreciousSale();
			preciousSale.setPreciousId(preciousId);
			preciousSale.setPreciousName(preciousName);
			preciousSale.setPreciousPrice(new BigDecimal(preciousPrice,MathContext.DECIMAL32));
			preciousSale.setSaleCount(saleCount);
			preciousSale.setSaleRoomId(saleRoomId);
			preciousSale.setSaleTime(new Timestamp(System.currentTimeMillis()));
			preciousSale.setPreciousImage(preciousImage);
			preciousSale.setTotalMoney(new BigDecimal(totalMoney,MathContext.DECIMAL32));
			preciousSale.setBelongBusid(belongBusid);
			preciousSaleDao.createPreciousSale(preciousSale);
			HashMap<String, Object> consumptionlist=roomRuningdao.getconsumptionById(saleRoomId);
			String RemarkMoney=((String) consumptionlist.get("remark")).substring(1);
			if(new BigDecimal(consumptionlist.get("money").toString()).compareTo(new BigDecimal(RemarkMoney+".000"))==1){
				roomRuningdao.updateCstate(saleRoomId);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPreciousSale(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int querytime = Integer.valueOf(param.get("querytime"));
		int belongBusid = Integer.valueOf(param.get("belongBusid"));
		try {
			List<PreciousSale> PreciousSaleList=preciousSaleDao.getPreciousSale(querytime,belongBusid);
			jsonData.setData(PreciousSaleList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPreciousDaySale(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid = Integer.valueOf(param.get("belongBusid"));
		try {
			List<PreciousSale> PreciousSaleList=preciousSaleDao.getPreciousDaySale(belongBusid);
			jsonData.setData(PreciousSaleList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询大件出货价钱
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPreciousSaleMoney(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		BigDecimal moneySum = new BigDecimal(0, MathContext.DECIMAL32);
		BigDecimal moneydaySum = new BigDecimal(0, MathContext.DECIMAL32);
		int belongBusid = Integer.valueOf(param.get("belongBusid"));
		try {
			List<PreciousSale> PreciousSaleMoney=preciousSaleDao.getPreciousSaleMoney(belongBusid);
			for(int i=0;i<PreciousSaleMoney.size();i++){
				moneySum=moneySum.add(PreciousSaleMoney.get(i).getSumMoney(),MathContext.DECIMAL32);
			}
			List<PreciousSale> YesterdayMoney=preciousSaleDao.getYesterdayMoney(belongBusid);
			for(int i=0;i<YesterdayMoney.size();i++){
				moneydaySum=moneydaySum.add(YesterdayMoney.get(i).getSumMoney(),MathContext.DECIMAL32);
			}
			Map map=new HashMap();
			map.put("moneySum", moneySum);
			map.put("moneydaySum", moneydaySum);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/*
	 * 删除录入信息
	 */
	@Override
	public JsonData deletePreciousSale(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		try {
			int salid = Integer.valueOf(param.get("salid"));
			preciousSaleDao.deletePreciousSale(salid);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除录入信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
