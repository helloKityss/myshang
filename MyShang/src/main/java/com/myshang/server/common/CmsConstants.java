package com.myshang.server.common;

import java.math.BigDecimal;

public final class CmsConstants {
	/** 生成验证码，session中存放值得key */
	public final static String Code_Key = "validateCode";
	public final static String user_key = "sessionUser";
	public final static String STATIC_SERVER_DOMAIN = "192.168.1.244";

//	/** nginx路径 **/
//	public final static String FILE_ROOT = PropsFileManager.getInstance().getSMMSFile("FILE_ROOT");
//	public final static String FILE_DOWNLOAD_URL = PropsFileManager.getInstance().getSMMSFile("FILE_DOWNLOAD_URL");
//	public final static String LOGIN_SESSION_USER_ID = PropsFileManager.getInstance().getSMMSFile("LOGIN_SESSION_USER_ID");
//	public final static String FILE_USER_UPLOAD=PropsFileManager.getInstance().getSMMSFile("FILE_USER_UPLOAD");
//	public final static String FILE_PCUSER_UPLOAD=PropsFileManager.getInstance().getSMMSFile("FILE_PCUSER_UPLOAD");
//	public final static String FILE_ROOM_UPLOAD=PropsFileManager.getInstance().getSMMSFile("FILE_ROOM_UPLOAD");
//	public final static String FILE_SHOP_UPLOAD=PropsFileManager.getInstance().getSMMSFile("FILE_SHOP_UPLOAD");
//	
//	public final static String MOBILE_ACCOUNT = PropsFileManager.getInstance().getSMMSFile("MOBILE_ACCOUNT");
//	public final static String MOBILE_PASS = PropsFileManager.getInstance().getSMMSFile("MOBILE_PASS");
//	public final static String MOBILE_SMSURL = PropsFileManager.getInstance().getSMMSFile("MOBILE_SMSURL");
//	public final static String MOBILE_PATH = PropsFileManager.getInstance().getSMMSFile("MOBILE_PATH");
//	
//	/** 消息推送参数 **/
//	public final static String APPID = PropsFileManager.getInstance().getSMMSFile("APPID");
//	public final static String AppKey = PropsFileManager.getInstance().getSMMSFile("AppKey");
//	public final static String MasterSecret = PropsFileManager.getInstance().getSMMSFile("MasterSecret");
//	public final static String DEFAULE_IMG = PropsFileManager.getInstance().getSMMSFile("DEFAULE_IMG");
	
	/*** 微信相关*/
	public final static String WEIXIN_URL = PropsFileManager.getInstance().getSMMSFile("WEIXIN_URL");
	public final static String WEIXIN_APPID = PropsFileManager.getInstance().getSMMSFile("WEIXIN_APPID");
	public final static String WEIXIN_SECRET = PropsFileManager.getInstance().getSMMSFile("WEIXIN_SECRET");
	public final static String WEIXIN_TOKEN = PropsFileManager.getInstance().getSMMSFile("WEIXIN_TOKEN");
	public final static String WEIXIN_SHOPNUMBER = PropsFileManager.getInstance().getSMMSFile("WEIXIN_SHOPNUMBER");
	public final static String WEIXIN_KEY = PropsFileManager.getInstance().getSMMSFile("WEIXIN_KEY");
	public final static String WEIXIN_MINI_APPID = PropsFileManager.getInstance().getSMMSFile("WEIXIN_MINI_APPID");
	public final static String WEIXIN_MINI_SECRET = PropsFileManager.getInstance().getSMMSFile("WEIXIN_MINI_SECRET");
	
	public final static String WEIXINTAX = PropsFileManager.getInstance().getSMMSFile("WEIXINTAX");
	
	public final static String PUBLIC_ACTIVE_CODE = PropsFileManager.getInstance().getSMMSFile("PUBLIC_ACTIVE_CODE");
}
