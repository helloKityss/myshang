package com.myshang.server.extenduser.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.myshang.server.distribution.model.Distribution;
import com.myshang.server.extenduser.model.Extenduser;
import com.myshang.server.user.model.User;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface ExtenduserDao {
	/**
	 * 查询注册信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public List<Extenduser> getExtendlisMaps(String openid);
	/**
	 * 添加一条收入记录
	 * @param income
	 * @return 执行行数
	 */
	public Extenduser getExtenduser(int distriType);
	/**
	 * 保存
	 * @author zhangkang
	 * 2018年9月12日 下午4:41:01
	 * @param house
	 */
	public void saveExtenduser(Extenduser user);
	/**
	 * 更改个人信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public int updateExtenduserByWeiXin(Extenduser user);
	/**
	 * 更改个人信息
	 * @author zhangkang
	 * 2018年3月8日 下午5:44:04
	 */
	public int updateExtendMobileByWeiXin(Extenduser user);
	/**
	 * 新增用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int createExtendUser(Extenduser user)throws Exception;
}
