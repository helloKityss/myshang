package com.myshang.server.messages.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.messages.model.Messages;
import com.myshang.server.user.model.User;


/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface MessagesDao {
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public List<Messages> messagesList(@Param("businessId") int businessId,@Param("querytime") int querytime);
	/**
	 * 查询消息列表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	public List<Messages> getMessagesState(@Param("businessId") int businessId,@Param("querytime") int querytime);
	/**
	 * 查询未完成的任务数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @returns
	 */
	public List<Messages> getMessagesHang(@Param("businessId") int businessId,@Param("querytime") int querytime);
	/**
	 * 新增用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public void createMessagesInfo(Messages messages)throws Exception;
	/**
	 * 新增用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public void updateMessages(Messages messages)throws Exception;
	/**
	 * 新增用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public void deleteMessages(int uid)throws Exception;
}
