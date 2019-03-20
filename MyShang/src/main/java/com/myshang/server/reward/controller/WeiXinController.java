package com.myshang.server.reward.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myshang.server.common.HttpUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.WXPayUtil;
import com.myshang.server.messages.dao.MessagesDao;
import com.myshang.server.qrcode.model.Qrcode;
import com.myshang.server.qrcode.service.QrcodeService;
import com.myshang.server.reward.service.RewardService;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.common.RedisUtil;

/**
 * 微信接口控制器
 * 
 * @author HL 2018.9.13.10:53
 */
@SuppressWarnings("deprecation")
@RestController
public class WeiXinController {
	String Meiyuurl = CmsConstants.WEIXIN_URL;// "https://www.meiyumeet.com/"
	String appid = CmsConstants.WEIXIN_APPID;// "wxff2af56a454e9c05" 服务号
	String secret = CmsConstants.WEIXIN_SECRET;// "36077c0a8b03ddff3411728d354a40df" 公众号密钥
	String token = CmsConstants.WEIXIN_TOKEN;// "shangwo2018"
	String shopNumber = CmsConstants.WEIXIN_SHOPNUMBER;// "1501961771" 商户号
	String key = CmsConstants.WEIXIN_KEY;// "gaoe1985gaoe1985gaoe1985gaoe1985" 商户密钥
	public Logger log = Logger.getLogger(WeiXinController.class); // 日志

	@Autowired
	RewardService rewardService;
	@Autowired
	QrcodeService qrcodeservice;
	@Autowired
	MessagesDao messagesdao;
	@Autowired
	UserDao userDao;

	/**
	 * 获取code 静默授权
	 */
	@RequestMapping("/reward/getStaticCode")
	public ModelAndView getStaticCode(@RequestParam Map<String, String> param) {
		try {
			Qrcode qrcode = qrcodeservice.getQrcodeById(Integer.parseInt(param.get("qrcid")));
			int state = qrcode.getHasBind();
			String QrcidOrUid = state == 0 ? String.valueOf(qrcode.getQrcid()) : String.valueOf(qrcode.getBindUserId());
			// 第一步 获取用户同意
			String redirect_uri = Meiyuurl+"MyShang/reward/getPersonalInformation?qrcidoruid="
					+ QrcidOrUid; // 目标访问地址
			redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");// 授权后重定向的回调链接地址，请使用urlencode对链接进行处理（文档要求）
			// 按照文档要求拼接访问地址
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri="
					+ redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
			ModelAndView mv = new ModelAndView("redirect:" + url);
			return mv;// 跳转到要访问的地址
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mv = new ModelAndView("授权错误！");
			return mv;
		}
	}

	/**
	 * 获取详细个人信息
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/reward/getPersonalInformation")
	public ModelAndView getPersonalInformation(@RequestParam Map<String, String> param) {
		JSONObject jsonObject = new JSONObject();
		try {
			String state = param.get("state");
			int QrcidOrUid = Integer.parseInt(param.get("qrcidoruid"));
			// 第二步 通过code换取网页授权access_token
			String code = param.get("code");// 获取返回码
			// 同意授权
			// 拼接请求地址
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + appid + "&secret=" + secret
					+ "&code=" + code + "&grant_type=authorization_code";
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String tokens = EntityUtils.toString(httpEntity, "utf-8");
			log.error("网页授权tokens为" + tokens);
			JSONObject json = JSONObject.fromObject(tokens);
			// System.out.println("根据code获取的token" + json);
			String url3 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + json.get("access_token") + "&openid="
					+ json.get("openid") + "&lang=zh_CN";
			HttpEntity wUhttpEntity = httpClient.execute(new HttpGet(url3)).getEntity();
			String wXinUserInfo = EntityUtils.toString(wUhttpEntity, "utf-8");
			JSONObject userInfo = JSONObject.fromObject(wXinUserInfo);
			log.error("userInfo里的内容为:" + userInfo.toString());
			jsonObject = qrcodeservice.RelationshipJudgment(state, QrcidOrUid, userInfo.get("unionid").toString());
			if (jsonObject.get("type").toString().equals("ToReward")) {
				return new ModelAndView("redirect:"+Meiyuurl+"H5Pages/reward.html?uid=" + QrcidOrUid
						+ "&openid=" + json.get("openid").toString() + "&unionid=" + userInfo.get("unionid").toString()
						+ "&businessId=" + userDao.queryBusid(QrcidOrUid).getBelongBusid().toString());
			} else {
				return new ModelAndView("redirect:"+Meiyuurl+"H5Pages/notice.html?type="
						+ jsonObject.get("type").toString() + "&msg=" + jsonObject.get("msg").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("type", "erro");
			jsonObject.put("msg", e);
			return new ModelAndView(jsonObject.toString());
		}
	}

	/**
	 * 請求 统一下单 生成预付订单
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	@RequestMapping("/reward/getAdvanceOrder")
	public JSONObject getAdvanceOrder(HttpServletRequest request, @RequestParam Map<String, String> param)
			throws UnsupportedEncodingException {
		JSONObject jsonObject = new JSONObject();
		try {
			String unionid = param.get("unionid");// 客户unionid
			String openid = param.get("openid");// 客户openid
			String money = param.get("money");// 金钱
			String explain = param.get("explain");// 说明
			String userid = param.get("uid");// 用户id
			log.error("getAdvanceOrder1:" + unionid);
			log.error("getAdvanceOrder2:" + openid);
			log.error("getAdvanceOrder3:" + money);
			log.error("getAdvanceOrder4:" + explain);
			log.error("getAdvanceOrder5:" + userid);
			// String shopid = param.get("shopid");// 商户id
			String outtradeno = WeiXinUtil.getOrderId();
			// String shopid = clientService.getshopid(userid);//shopid
			Map<String, String> map = new HashMap<String, String>();
			map.put("appid", appid);// 公眾賬號id
			map.put("mch_id", shopNumber);// 商戶號
			map.put("device_info", "WEB");// 设备号
			map.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
			map.put("body", explain.getBytes("UTF-8").toString());// 订单描述
			map.put("out_trade_no", outtradeno);// 商户订单号
			map.put("total_fee", money);// 标价金额（分为单位）
			map.put("spbill_create_ip", WeiXinUtil.getIp(request));// 客户端ip
			// System.out.println("客户端ip:"+WeiXinUtil.getIp(this.getRequest()));
			map.put("notify_url", Meiyuurl + "MyShang/reward/paySuccess");// 支付成功回調地址,通知url必须为外网可访问的url,不能携带参数。
			map.put("trade_type", "JSAPI");// 交易類型
			map.put("openid", openid);// 扫码人的openid(公众号支付必传)
			String sign = WeiXinUtil.getSign(map, key);
			map.put("sign", sign);// 签名
			// 轉成XML
			String xml = WeiXinUtil.map2XmlString(map);
			// 發起post請求
			String response = HttpUtil.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
			System.out.println("1---" + response);
			// xml 转 map
			Map<String, String> resData = WeiXinUtil.readStringXmlOut(response);
			System.out.println("2---" + resData);

			if (resData.get("return_code").equals("FAIL")) {

				jsonObject.put("code", "1");
				jsonObject.put("msg", resData.get("return_msg"));
				return jsonObject;
			} else if (resData.get("result_code").equals("FAIL")) {
				jsonObject.put("code", "1");
				jsonObject.put("msg", resData.get("err_code_des"));
				return jsonObject;
			} else {
				// 存入数据库
				// 进行签名 返回给前台
				JSONObject callbackJson = new JSONObject();
				callbackJson.put("money", money);
				callbackJson.put("uid", userid);
				callbackJson.put("unionid", unionid);
				callbackJson.put("openid", openid);
				RedisUtil.setex(outtradeno, callbackJson.toString(), 1000);
				Map<String, String> returnMap = new HashMap<String, String>();
				returnMap.put("appId", appid);
				String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
				returnMap.put("timeStamp", timeStamp);
				returnMap.put("nonceStr", WeiXinUtil.getRandomNumber());
				returnMap.put("package", "prepay_id=" + resData.get("prepay_id"));
				returnMap.put("signType", "MD5");
				String returnSign = WeiXinUtil.getSign(returnMap, key);
				System.out.println(returnSign);
				returnMap.put("sign", returnSign);// 签名
				jsonObject.put("code", "0");
				jsonObject.put("data", returnMap);
				log.error("getAdvanceOrder:" + returnMap.toString());
				return jsonObject;
			}
		} catch (MalformedURLException e) {
			jsonObject.put("code", "1");
			jsonObject.put("msg", "getAdvanceOrder erro ");
			e.printStackTrace();
			return jsonObject;
		}
	}

	/**
	 * 支付成功回調
	 */
	@RequestMapping("/reward/paySuccess")
	public String paySuccess(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String inputLine;
		String notityXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
			String res = rewardService.payCallBackHandle(notityXml, key);
			return res;
		} catch (Exception e) {
			result.put("return_code", "FAIL");
			result.put("return_msg", "erro");
			e.printStackTrace();
			return result.toString();
		}
	}

	/**
	 * 开启开发者模式
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/openDeveloper")
	public String openDeveloper(@RequestParam Map<String, String> param) throws IOException {
		String timestamp = param.get("timestamp");// 时间戳
		String signature = param.get("signature");// 微信加密签名
		String nonce = param.get("nonce");// 随机数
		String echostr = param.get("echostr");
		String[] arr = new String[] { token, timestamp, nonce };
		// 排序
		Arrays.sort(arr);

		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		// sha1加密
		String temp = WeiXinUtil.getSha1(content.toString());
		if (temp.equals(signature)) {
			return echostr;
		} else {
			return "";
		}
	}

	/**
	 * 测试用接口
	 */
	@RequestMapping("/reward/test")
	public JsonData Test() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uid", "1");
		jsonObject.put("openid", "oKeO05K1HCDXjSrrrWPduzovf5Jw");
		jsonObject.put("unionid", "oBwq_0etFDPxaztl509flx9W_Kns");
		jsonObject.put("money", "1");
		return rewardService.distributionHandle(jsonObject);
	}
}
