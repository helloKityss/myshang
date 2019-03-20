package com.myshang.server.withdrawRecord.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.account.service.AccountService;
import com.myshang.server.attendance.service.AttendanceService;
import com.myshang.server.bankcard.service.BankcardService;
import com.myshang.server.common.CertUtil;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.HttpUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.WXPayUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.role.service.RoleService;
import com.myshang.server.withdrawRecord.service.WithdrawRecordService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class WithdrawRecordController {
	@Autowired
	private WithdrawRecordService withdrawRecordService;
	String appid = CmsConstants.WEIXIN_MINI_APPID;// "wxff2af56a454e9c05" 服务号
	String shopNumber = CmsConstants.WEIXIN_SHOPNUMBER;// "1501961771" 商户号
	String key = CmsConstants.WEIXIN_KEY;// "gaoe1985gaoe1985gaoe1985gaoe1985" 商户密钥
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/addWithdrawRecord")
	public JsonData addWithdrawRecord(@RequestParam Map<String, String> param){
		return withdrawRecordService.addWithdrawRecord(param);
	}
	/**
	 * 查询提现记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/getWithdrawRecord")
	public JsonData getWithdrawRecord(@RequestParam Map<String, String> param){
		return withdrawRecordService.getWithdrawRecord(param);
	}
	/**
	 * 生成提现excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/createExcel")
	public JsonData createExcel(@RequestParam Map<String, String> param){
		return withdrawRecordService.createExcel(param);
	}
	/**
	 * 生成提现excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/transfers")
	public JsonData transfers(@RequestParam Map<String, String> param){
		return withdrawRecordService.transfers(param);
	}
	/**
	 * 生成提现excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/paybank")
	public JsonData paybank(@RequestParam Map<String, String> param) throws Exception{
		return withdrawRecordService.paybank(param);
	}
	/**
	 * 生成提现excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/WithdrawRecord/getpublickey")
	public JsonData getpublickey(@RequestParam Map<String, String> param) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		JsonData jsonData=new JsonData();
		String url="https://fraud.mch.weixin.qq.com/risk/getpublickey";
		map.put("mch_id",shopNumber);// 商戶號
		map.put("nonce_str",WXPayUtil.generateNonceStr());// 商户订单号
		map.put("sign_type","MD5");// 签名
		String sign = WeiXinUtil.getSign(map,key);
		map.put("sign",sign);// 签名
		// 轉成XML
		String xml = WeiXinUtil.map2XmlString(map);
		// 發起post請求
		String response=CertUtil.postData(url,xml,shopNumber);
		Map<String, String> resData = WeiXinUtil.readStringXmlOut(response);;
		jsonData.setData(resData);
		return jsonData;
	}
}
