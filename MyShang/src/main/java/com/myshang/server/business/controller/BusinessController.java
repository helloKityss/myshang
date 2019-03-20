package com.myshang.server.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.business.service.BusinessService;
import com.myshang.server.common.JsonData;

@RestController
public class BusinessController {
	@Autowired
	private BusinessService bussinessService;
	
	/**
     * 新增商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/savebussiness")
	public JsonData savebussiness(@RequestParam Map<String, String> param) throws Exception{						
		return bussinessService.savebussiness(param);
	}
	
	/**
     * 查询商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/queryBussiness")
	public JsonData queryBussiness(@RequestParam Map<String, String> param) throws Exception{						
		return bussinessService.queryBussiness(param);
	}
	/**
     * 查询商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/getbussiness")
	public JsonData getbussiness(@RequestParam Map<String, String> param) throws Exception{						
		return bussinessService.getbussiness(param);
	}
	/**
     * 新增商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/updatebussiness")
	public JsonData updatebussiness(@RequestParam Map<String, String> param) throws Exception{						
		return bussinessService.updatebussiness(param);
	}
	/**
     * 新增商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/deletebussiness")
	public JsonData deletebussiness(@RequestParam Map<String, String> param) throws Exception{						
		return bussinessService.deletebussiness(param);
	}
	/**
     * 新增商户
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/bussiness/uploadbussiness")
	public JsonData uploadbussiness(@RequestBody MultipartFile file) throws Exception{						
		return bussinessService.uploadbussiness(file);
	}
}
