package com.myshang.server.withdrawRecord.service.impl;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.attendance.model.Attendance;
import com.myshang.server.bankcard.dao.BankcardDao;
import com.myshang.server.bankcard.model.Bankcard;
import com.myshang.server.bankcard.service.BankcardService;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.ExcelOperate;
import com.myshang.server.common.HttpUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.RSAUtils;
import com.myshang.server.common.RedisUtil;
import com.myshang.server.common.CertUtil;
import com.myshang.server.common.WXPayUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.common.WxBase64;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.room.model.Room;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
import com.myshang.server.withdrawRecord.dao.WithdrawRecordDao;
import com.myshang.server.withdrawRecord.model.WithdrawRecord;
import com.myshang.server.withdrawRecord.service.WithdrawRecordService;
@Service
@Transactional
public class WithdrawRecordServiceImpl implements WithdrawRecordService {
	String Meiyuurl = CmsConstants.WEIXIN_URL;// "https://www.meiyumeet.com/"
	String appid = CmsConstants.WEIXIN_MINI_APPID;// "wxff2af56a454e9c05" 服务号
	String secret = CmsConstants.WEIXIN_MINI_SECRET;// "36077c0a8b03ddff3411728d354a40df" 公众号密钥
	String token = CmsConstants.WEIXIN_TOKEN;// "shangwo2018"
	String shopNumber = CmsConstants.WEIXIN_SHOPNUMBER;// "1501961771" 商户号
	String key = CmsConstants.WEIXIN_KEY;// "gaoe1985gaoe1985gaoe1985gaoe1985" 商户密钥
	@Autowired
	private WithdrawRecordDao withdrawRecordDao;
	@Autowired
	private AccountDao accountDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData addWithdrawRecord(Map<String, String> param) {
		int uid=Integer.valueOf(param.get("uid"));
		int bankcardId=Integer.valueOf(param.get("bankcardId"));
		String withdrawMoney=param.get("withdrawMoney");
		JsonData jsonData = new JsonData();
		try {
			BigDecimal account=accountDao.getAccount(uid);
			WithdrawRecord withdrawRecord=new WithdrawRecord();
			withdrawRecord.setUserId(uid);
			withdrawRecord.setWithdrawMoney(new BigDecimal(withdrawMoney, MathContext.DECIMAL32));
			withdrawRecord.setBankcardId(bankcardId);
			withdrawRecord.setWithdrawTime(new Timestamp(System.currentTimeMillis()));
			withdrawRecordDao.addWithdrawRecord(withdrawRecord);
			accountDao.UpdateAccount(uid, account.subtract(new BigDecimal(withdrawMoney, MathContext.DECIMAL32)));
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("绑定卡失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 生成excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData createExcel(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		ExcelOperate excelOperate=new ExcelOperate();
		try {
			List<HashMap<String,Object>> list=withdrawRecordDao.queryExcel();
        	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String path =request.getSession().getServletContext().getRealPath("/");
            excelOperate.createExcel(list, path);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("导入excel失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 生成excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData transfers(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		String openid = param.get("openid");// 客户openid
		String money = param.get("money");// 金钱
		String explain = param.get("explain");// 说明
		int uid = Integer.valueOf(param.get("uid"));// 说明
		// String shopid = param.get("shopid");// 商户id
		String outtradeno = WeiXinUtil.getOrderId();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			// String shopid = clientService.getshopid(userid);//shopid
			String url="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
			Map<String, String> map = new HashMap<String, String>();
			map.put("mch_appid", appid);// 商户账号appid
			map.put("mchid", shopNumber);// 商戶號
			map.put("device_info", "WEB");// 设备号
			map.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
			map.put("desc", explain.getBytes("UTF-8").toString());// 订单描述
			map.put("partner_trade_no", outtradeno);// 商户订单号
			map.put("amount", money);// 标价金额（分为单位）
			map.put("check_name", "NO_CHECK");// 标价金额（分为单位）
			map.put("spbill_create_ip", WeiXinUtil.getIp(request));// 客户端ip
			// System.out.println("客户端ip:"+WeiXinUtil.getIp(this.getRequest()));
			map.put("openid", openid);// 扫码人的openid(公众号支付必传)
			String sign = WeiXinUtil.getSign(map, key);
			map.put("sign", sign);// 签名
			// 轉成XML
			String xml = WeiXinUtil.map2XmlString(map);
			String response=CertUtil.postData(url,xml,shopNumber);
			// 發起post請求
			// xml 转 map
			Map<String, String> resData = WeiXinUtil.readStringXmlOut(response);
			if(resData.get("result_code").equals("FAIL") || resData.get("return_code").equals("FAIL")){
				String response1=CertUtil.postData(url,xml,shopNumber);
				Map<String, String> resData1 = WeiXinUtil.readStringXmlOut(response1);
				if(resData1.get("result_code").equals("FAIL") || resData1.get("return_code").equals("FAIL")){
					String response2=CertUtil.postData(url,xml,shopNumber);
					Map<String, String> resData2 = WeiXinUtil.readStringXmlOut(response2);
					if(resData2.get("result_code").equals("FAIL") || resData2.get("return_code").equals("FAIL")){
						String response3=CertUtil.postData(url,xml,shopNumber);
						Map<String, String> resData3 = WeiXinUtil.readStringXmlOut(response3);
						if(resData3.get("result_code").equals("FAIL") || resData3.get("return_code").equals("FAIL")){
							jsonData.setCodeEnum(CodeEnum.ERROR_TRANSFER);
							jsonData.setData(resData3);
							return jsonData;
						}
					}
				}
			}
			BigDecimal account=accountDao.getAccount(uid);
			WithdrawRecord withdrawRecord=new WithdrawRecord();
			withdrawRecord.setUserId(uid);
			withdrawRecord.setWithdrawMoney(new BigDecimal(money).divide(new BigDecimal(100)));
			withdrawRecord.setBankcardId(0);
			withdrawRecord.setWithdrawTime(new Timestamp(System.currentTimeMillis()));
			withdrawRecordDao.addWithdrawRecord(withdrawRecord);
			accountDao.UpdateAccount(uid, account.subtract(new BigDecimal(money, MathContext.DECIMAL32).divide(new BigDecimal(100))));
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
			jsonData.setData(resData);
		} catch (Exception e) {
			LOGGER.error("付款失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询提现记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getWithdrawRecord(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int userId=Integer.valueOf(param.get("userId"));
		String querytime = param.get("querytime");
		String startTime="";
		String endTime="";
		List<WithdrawRecord> withdrawRecordList=new ArrayList<WithdrawRecord>();
		try {
			if(querytime.equals("三个月内")){
				withdrawRecordList=withdrawRecordDao.getMorthWithdraw(userId,3);
			}else if(querytime.equals("半年内")){
				withdrawRecordList=withdrawRecordDao.getMorthWithdraw(userId,6);
			}else if(querytime.equals("null")){
				withdrawRecordList=withdrawRecordDao.getMorthWithdraw(userId,0);
			}else{
				String[] split = querytime.split("~");//以逗号分割
				for(int i=0;i<split.length;i++){
					if(StringUtils.isEmpty(split[i])){
						continue;
					}
				    startTime=split[0];
				    endTime=split[1];
				}
				withdrawRecordList=withdrawRecordDao.getWithdraw(userId,startTime,endTime);
			}
			Map map=new HashMap();
			map.put("withdrawRecordList", withdrawRecordList);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 生成excel表
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public JsonData paybank(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		String money = param.get("money");// 金钱
		String explain = param.get("explain");// 说明
		String enc_true_name = param.get("enc_true_name");// 用户姓名
		String bank_code = param.get("bank_code");// 收款方开户行
		String enc_bank_no = param.get("enc_bank_no");// 收款方开户行
//		int uid = Integer.valueOf(param.get("uid"));// 说明
		String outtradeno = WeiXinUtil.getOrderId();
		String url="https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";
		String rsa ="RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String keyfile = request.getSession().getServletContext().getRealPath("/pkcs8_pubkey.pem"); //读取PKCS8密钥文件
		PublicKey pub=RSAUtils.getPubKey(keyfile,"RSA");
		byte[] estr=RSAUtils.encrypt(enc_bank_no.getBytes(),pub,2048, 11,rsa);   //对银行账号进行加密
		String rscardNo =WxBase64.encode(estr);//并转为base64格式
	    byte[] estr2=RSAUtils.encrypt(enc_true_name.getBytes(),pub,2048, 11,rsa);   //对银行账号进行加密
	    String rsaccountName=WxBase64.encode(estr2);//并转为base64格式
		try {
			// String shopid = clientService.getshopid(userid);//shopid
			Map<String, String> map = new HashMap<String, String>();
			map.put("mch_id", shopNumber);// 商戶號
			map.put("partner_trade_no", outtradeno);// 商户订单号
			map.put("enc_bank_no", rscardNo);// 银行卡号
			map.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
			map.put("desc", explain.getBytes("UTF-8").toString());// 订单描述
			map.put("enc_true_name", rsaccountName);//收款方用户名
			map.put("bank_code", bank_code);//收款方开户行
			map.put("amount", money);// 标价金额（分为单位）
			String sign = WeiXinUtil.getSign(map, key);
			map.put("sign", sign);// 签名
			// System.out.println("客户端ip:"+WeiXinUtil.getIp(this.getRequest()));
			// 轉成XML
			String xml = WeiXinUtil.map2XmlString(map);
			String response=CertUtil.postData(url,xml,shopNumber);
			// 發起post請求
			// xml 转 map
			Map<String, String> resData = WeiXinUtil.readStringXmlOut(response);
//			BigDecimal account=accountDao.getAccount(uid);
//			WithdrawRecord withdrawRecord=new WithdrawRecord();
//			withdrawRecord.setUserId(uid);
//			withdrawRecord.setWithdrawMoney(new BigDecimal(money).divide(new BigDecimal(100)));
//			withdrawRecord.setBankcardId(Integer.valueOf(enc_bank_no));
//			withdrawRecord.setWithdrawTime(new Timestamp(System.currentTimeMillis()));
//			withdrawRecordDao.addWithdrawRecord(withdrawRecord);
//			accountDao.UpdateAccount(uid, account.subtract(new BigDecimal(money, MathContext.DECIMAL32).divide(new BigDecimal(100))));
			// xml 转 map
			System.out.println("2---" + resData);
			jsonData.setData(resData);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("付款失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
