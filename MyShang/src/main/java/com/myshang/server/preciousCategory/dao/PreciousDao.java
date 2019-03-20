package com.myshang.server.preciousCategory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.preciousCategory.model.Precious;
import com.myshang.server.preciousSale.model.PreciousSale;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface PreciousDao {
	/**
	 * 查询酒品信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Precious> getPreciousAlcohol(int belongBusid);
	/**
	 * 查询食品信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Precious> getPreciousFood(int belongBusid);
	/**
	 * 查询食品信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Precious getPreciousinformation(@Param("preid") int preid,@Param("belongBusid") int belongBusid );
}
