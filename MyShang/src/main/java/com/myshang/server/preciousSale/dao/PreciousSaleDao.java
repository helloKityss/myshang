package com.myshang.server.preciousSale.dao;


import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.preciousSale.model.PreciousSale;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface PreciousSaleDao {
	/**
	 * 统计同一房间玩家的投注份数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void createPreciousSale(PreciousSale preciousSale);
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<PreciousSale> getPreciousSale(@Param("querytime") int querytime ,@Param("belongBusid") int belongBusid);
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<PreciousSale> getPreciousDaySale(int belongBusid);
	/**
	 * 查询大件出货价钱
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<PreciousSale> getPreciousSaleMoney(int belongBusid);
	/**
	 * 查询昨天大件出货价钱
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<PreciousSale> getYesterdayMoney(int belongBusid);
	/**
	 * 查询昨天大件出货价钱
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deletePreciousSale(int salid);
	/**
	 * 根据房间id查询收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getRoomSale(int roomId);
}
