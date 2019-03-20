package com.myshang.server.common;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;





/**
 * 微信工具类
 * 
 * @author Sxy
 * @time 2018年4月8日
 */
public class WeiXinUtil {
	
	/**
	 * map转成xml格式
	 */
	public static String map2XmlString(Map<String, String> map) {
        String xmlResult = "";

        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String key : map.keySet()) {
            String value = "<![CDATA[" + map.get(key) + "]]>";
            sb.append("<" + key + ">" + value + "</" + key + ">");
        }
        sb.append("</xml>");
        xmlResult = sb.toString();

        return xmlResult;
    }
	/**
	 * xml转成map格式
	 */
	public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
	
	/**
	 * 生成订单号
	 */
	public static String getOrderId(){
		 String orderId="";   
		    //获取当前时间戳         
		    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");    
		    String temp = sf.format(new Date());    
		    //获取6位随机数  
		    int random=(int) ((Math.random()+1)*100000);    
		    orderId=temp+random;    
		    return orderId;    
	}
	
	/**
	 * 生成随机数
	 */
	public static String getRandomNumber(){
		String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	    //由Random生成随机数
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //长度为几就循环几次
	        for(int i=0; i<32; ++i){
	          //产生0-61的数字
	          int number=random.nextInt(62);
	          //将产生的数字通过length次承载到sb中
	          sb.append(str.charAt(number));
	        }
	        //将承载的字符转换成字符串
	        return sb.toString();
	}
	/**
	 * 生成sign簽名
	 */
	public static String getSign(Map<String, String> map,String key){
		ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!="" && !entry.toString().equals("return_code") && !entry.toString().equals("return_msg") && !entry.toString().equals("result_code")){
            	System.out.println();
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        
        String result = sb.toString();
        result += "key="+key;
        result =MD5Util.MD5(result).toUpperCase();
        return result;
	}
	
	/**
	 * 獲取AccessToken
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String getAccessToken(String appid,String secret) throws ClientProtocolException, IOException{
		String url = "https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential&appid=" + appid + "&secret=" + secret + "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String tokens = EntityUtils.toString(httpEntity, "utf-8");
		JSONObject json = JSONObject.fromObject(tokens);
		System.out.println("獲取微信AccessToken"+json);
		return json.get("access_token").toString();
	}
	
	/**
	 * 獲取微信 jsapi_ticket
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String getTicket(String accesstoken) throws ClientProtocolException, IOException{
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token=" + accesstoken + "&type=jsapi";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String tokens = EntityUtils.toString(httpEntity, "utf-8");
		JSONObject json = JSONObject.fromObject(tokens);
		System.out.println("獲取微信jsapi_ticket"+json);
		return json.get("ticket").toString();
	}
	
	/**
	 * Sha1加密方法
	 * 
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
	
	/**
	 * 获取用户真实ip
	 * @throws UnknownHostException 
	 */
	public static String getIp(HttpServletRequest request){
		 String ip = request.getHeader("X-Forwarded-For");
		           if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
		                //多次反向代理后会有多个ip值，第一个ip才是真实ip
		                 int index = ip.indexOf(",");
		                  if(index != -1){
		                     return ip.substring(0,index);
		                 }else{
		                    return ip;
		                 }
		           }
		             ip = request.getHeader("X-Real-IP");
		             if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
		                 return ip;
		             }
		            return request.getRemoteAddr();
	}
		
	/** 
     * 元转换成分 
     * @param money 
     * @return 
     */  
    public static String getMoney(String amount) {  
        if(amount==null){  
            return "";  
        }  
        // 金额转化为分为单位  
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额    
        int index = currency.indexOf(".");    
        int length = currency.length();    
        Long amLong = 0l;    
        if(index == -1){    
            amLong = Long.valueOf(currency+"00");    
        }else if(length - index >= 3){    
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));    
        }else if(length - index == 2){    
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);    
        }else{    
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");    
        }    
        return amLong.toString();   
    }
    
    /**
     * 解密用户敏感数据获取用户信息
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.fromObject(result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
