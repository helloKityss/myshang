package com.myshang.server.login.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.myshang.server.business.model.Business;
import com.myshang.server.login.model.Login;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface LoginDao {
	
	public Login login(String loginName);
	
	public void createlogin(Login login);
	
	public void updatelogin(Login login);
	
	public void deletelogin(int belongBusid);

}
