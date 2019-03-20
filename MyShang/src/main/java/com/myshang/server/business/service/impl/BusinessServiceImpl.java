package com.myshang.server.business.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.business.model.Business;
import com.myshang.server.business.service.BusinessService;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.UUIDGeneratorUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.distribution.dao.DistributionDao;
import com.myshang.server.distribution.model.Distribution;
import com.myshang.server.login.dao.LoginDao;
import com.myshang.server.login.model.Login;
import com.myshang.server.reward.dao.RewardDao;
import com.myshang.server.reward.model.Reward;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.impl.UserServiceImpl;
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService{
	
	private static final Logger LOGGER = Logger.getLogger(BusinessServiceImpl.class);
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private DistributionDao distributionDao;
	@Autowired
	private LoginDao loginDao;
	@Override
	public JsonData savebussiness(Map<String, String> param) throws Exception {
		
		JsonData jsonData=new JsonData();
		String businessName = param.get("businessName");
		String businessType = param.get("businessType");
		int extendUserId = Integer.valueOf(param.get("extendUserId"));
		Double latitude = Double.valueOf(param.get("latitude"));
		Double longitude = Double.valueOf(param.get("longitude"));
		String address = param.get("address");
		String businessBoss = param.get("businessBoss");
		String businessPhone = param.get("businessPhone");
		String loginName = param.get("loginName");
		String businessLogo = param.get("businessLogo");
		String password = param.get("password");
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String path =request.getSession().getServletContext().getRealPath("/BusinessLogo");
		try {
			Login login1=loginDao.login(loginName);
			if(login1 != null){
            	jsonData.setCodeEnum(CodeEnum.ERROR_LOGIN_USER);
                return jsonData;//重命名文件不存在
			}
			Business business=new Business();
			business.setBusinessName(businessName);
			business.setExtendUserId(extendUserId);
			business.setBusinessBoss(businessBoss);
			business.setBusinessPhone(businessPhone);
			business.setBusinessType(businessType);
			business.setLatitude(latitude);
			business.setLongitude(longitude);
			business.setAddress(address);
			businessDao.savebussiness(business);
			File oldfile=new File(path+"/"+businessLogo);
            if(!oldfile.exists()){
            	jsonData.setCodeEnum(CodeEnum.ERROR_IMAGES);
                return jsonData;//重命名文件不存在
            }
			File newfile=new File(path+"/"+business.getBusid()+".png");
			if(newfile.exists()){//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
				jsonData.setCodeEnum(CodeEnum.ERROR_IMAGESTO);
                return jsonData;
			}else{ 
                oldfile.renameTo(newfile); 
            } 
			String Logo="BusinessLogo"+"/"+business.getBusid()+".png";
			businessDao.updatebussinessLogo(Logo, business.getBusid());
			Login login=new Login();
			login.setLoginName(loginName);
			login.setPassword(password);
			login.setLoginType(2);
			login.setBelongBusid(business.getBusid());
			loginDao.createlogin(login);
			Distribution distribution=new Distribution();
			distribution.setBelongBusid(business.getBusid());
			if(extendUserId != 0){
				distribution.setExtend(50);
				distribution.setPlatform(150);
			}else{
				distribution.setExtend(0);
				distribution.setPlatform(200);
			}
			distribution.setInternal(50);
			distribution.setMami(50);
			distribution.setPrincess(500);
			distribution.setWeixin(6);
			distribution.setBusiness(200);
			distributionDao.saveDistribution(distribution);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			jsonData.setCodeEnum(CodeEnum.ERROR);
			LOGGER.error("新增用户接口失败", e);
		}
		return jsonData;
	}

	/*
	 * 查询所有商户
	 */
	@Override
	public JsonData getbussiness(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			List<Map<String,Object>> list=businessDao.getbussiness();
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 查询所有商户
	 */
	@Override
	public JsonData queryBussiness(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			List<Map<String,Object>> list=businessDao.getbusiness();
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	@Override
	public JsonData updatebussiness(Map<String, String> param) throws Exception {
		
		JsonData jsonData=new JsonData();
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String path =request.getSession().getServletContext().getRealPath("/BusinessLogo");
		int busid = Integer.valueOf(param.get("busid"));
		String businessName = param.get("businessName");
		String businessType = param.get("businessType");
		int extendUserId = Integer.valueOf(param.get("extendUserId"));
		Double latitude = Double.valueOf(param.get("latitude"));
		Double longitude = Double.valueOf(param.get("longitude"));
		String address = param.get("address");
		String businessBoss = param.get("businessBoss");
		String businessPhone = param.get("businessPhone");
		String loginName = param.get("loginName");
		String businessLogo = param.get("businessLogo");
		String password = param.get("password");
		try {
			Business business=new Business();
			File oldfile=new File(path+"/"+businessLogo);
            if(!oldfile.exists()){
            	jsonData.setCodeEnum(CodeEnum.ERROR_IMAGES);
                return jsonData;//重命名文件不存在
            }
			File newfile=new File(path+"/"+busid+".png");
            oldfile.renameTo(newfile); 
			String Logo="BusinessLogo"+"/"+busid+".png";
			business.setBusinessLogo(Logo);
			business.setBusid(busid);
			business.setBusinessName(businessName);
			business.setExtendUserId(extendUserId);
			business.setBusinessBoss(businessBoss);
			business.setBusinessPhone(businessPhone);
			business.setBusinessType(businessType);
			business.setLatitude(latitude);
			business.setLongitude(longitude);
			business.setAddress(address);
			businessDao.updatebussiness(business);
			Login login=new Login();
			login.setLoginName(loginName);
			login.setPassword(password);
			login.setLoginType(2);
			login.setBelongBusid(business.getBusid());
			loginDao.updatelogin(login);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			jsonData.setCodeEnum(CodeEnum.ERROR);
			LOGGER.error("新增用户接口失败", e);
		}
		return jsonData;
	}
	/*
	 * 批量删除商户
	 */
	@Override
	public JsonData deletebussiness(Map<String, String> param) throws Exception {
		String busid=param.get("busid");
		JsonData jsonData = new JsonData();
		try {
			String substring = busid.substring(0, busid.length());//截取最后一个
			LOGGER.error("截取投诉标签字符串后"+substring);
			String[] split = substring.split(",");//以逗号分割
			for(int i = 0; i<split.length;i++){
				int businessid=Integer.valueOf(split[i]);
				businessDao.deletebussiness(businessid);
				loginDao.deletelogin(businessid);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 查询所有商户
	 */
	@Override
	public JsonData uploadbussiness(MultipartFile multipartFile) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String path = BusinessServiceImpl.class.getResource("/").getPath();
			String picPath = "BusinessLogo/";
			String imagePath = path.substring(0, path.indexOf("WEB-INF")) + picPath;
			if (!multipartFile.isEmpty()) {
				String originalFilename = multipartFile.getOriginalFilename();
				String filename = UUIDGeneratorUtil.generate32Random()
						+ originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
				File file = new File(imagePath, filename);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				multipartFile.transferTo(new File(imagePath + filename));
				LOGGER.info("用户:" + "1111111111" + "的头像保存地址为:" + imagePath + filename);
				LOGGER.info("更新用户：" + "1111111111" + "头像为:" + picPath + filename);
				jsonData.setData(filename);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
