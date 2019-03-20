package com.myshang.server.reward.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.myshang.server.reward.model.Reward;

/**
 * 
 * @author HL 2018.9.13.15:24
 */
@Repository
public interface RewardDao {
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public int addRewardRecord(Reward reward);

	/**
	 * 添加打赏金额
	 * 
	 * @param reward
	 * @return
	 */
	public int addRewardMoney(Reward reward);

	/**
	 * 根据打赏记录id更新打赏数据
	 * 
	 * @param reward
	 * @return
	 */
	public int UpdateRewardRecordById(Reward reward);
	/**
	 * 根据打赏记录id更新打赏数据
	 * 
	 * @param reward
	 * @return
	 */
	public int getrewardCount(int rewardId);
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal queryRewardMoney(int busid);
	/**
	 * 查询今日打赏次数
	 * 
	 * @param reward
	 * @return
	 */
	public int queryrewardCount(int busid);
}
