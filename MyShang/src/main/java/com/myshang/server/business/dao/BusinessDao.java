package com.myshang.server.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.business.model.Business;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface BusinessDao {

	public List<Map<String,Object>> getbusiness();
	
	public String getbusinessByName(int busid);
	
	public String getbusinessByUrl(int busid);

	public Map<String,Object> getLatitudeAndLongitude(int busid);
	
	public int updateBusiness(Business business);
	
	public int getExtendId(int busid);
	
	public void savebussiness(Business business);
	
	public void updatebussiness(Business business);
	
	public void deletebussiness(int busid);
	
	public List<Map<String,Object>> getbussiness();
	
	public void updatebussinessLogo(@Param("businessLogo")String businessLogo,@Param("busid")int busid);
}
