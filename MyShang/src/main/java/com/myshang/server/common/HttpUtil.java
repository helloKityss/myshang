package com.myshang.server.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myshang.server.common.StringUtil;
import com.myshang.server.common.UrlUtil;

public class HttpUtil {

	public static String getRequestInputStreamStr(HttpServletRequest request) {
		String isStr = null;
		try {
			InputStream is = request.getInputStream();
			isStr = getRequestInputStreamStr(is, request.getCharacterEncoding());
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStr;
	}
	
	public static String getRequestInputStreamStr(InputStream is, String encoding) {
		String isStr = null;
		try {
			InputStreamReader isr = null;
			if (encoding != null) {
				isr = new InputStreamReader(is, encoding);
			} else {
				isr = new InputStreamReader(is);
			}
			BufferedReader bf = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String read = null;
			while ((read = bf.readLine()) != null) {
				sb.append(read + "\r\n");
			}
			bf.close();
			isr.close();

			isStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isStr;
	}

	public static String doPost(String address, String paras) throws MalformedURLException{
		String result = "网络连接错误";
		URL url = new URL(address);
		String json = paras;
		HttpURLConnection huConn = null;
		try {
			huConn = (HttpURLConnection) url.openConnection();
			huConn.setDoInput(true);
			huConn.setDoOutput(true);
			huConn.setRequestProperty("content-type", "text/xml");
			huConn.setRequestMethod("POST");
			huConn.connect();
			OutputStream os = huConn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			os.close();
			InputStream is = huConn.getInputStream();
			result = HttpUtil.getRequestInputStreamStr(is, "UTF-8");
		} catch (IOException e) {
		} finally {
			huConn.disconnect();
		}
		return result;
	}
	


	/**
	 * 设置cookie
	 * 
	 * @param response
	 *            应答
	 * @param cookieName
	 *            cookie名
	 * @param cookieValue
	 *            cookie值
	 * @param time
	 *            cookie生存时间
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int time) {
		// 设置集群域名
		//if (SystemSetting.getClusterSetting() != null) {
			//String domain = SystemSetting.getClusterSetting().getDomain();
			String domain = CmsConstants.STATIC_SERVER_DOMAIN;
			String topDomain = UrlUtil.getTopDomain("http:" + domain);
			//String topDomain ="http://" + domain;//http://192.168.1.244
			if (!StringUtil.isEmpty(topDomain)) {
				addCookie(response, "." + topDomain, "/", cookieName, cookieValue, time);
				return;
			}
		//}

		// 非集群域名
		addCookie(response, null, "/", cookieName, cookieValue, time);
	}

	public static void addCookie(HttpServletResponse response, String domain, String path, String cookieName, String cookieValue, int time) {
		try {
			if (cookieValue != null) {
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			}
		} catch (Exception ex) {
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		if (!StringUtil.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	public static void addCookie1(HttpServletResponse response, String cookieName, String cookieValue, int time) {

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, String domain, String path) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (domain.equals(cookies[i].getDomain()) && path.equals(cookies[i].getPath()) && cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 根据cookie名称取得cookie的值
	 * 
	 * @param HttpServletRequest
	 *            request request对象
	 * @param name
	 *            cookie名称
	 * @return string cookie的值 当取不到cookie的值时返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if (cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 清空cookie
	 */
	public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			System.out.println("清空Cookies发生异常！");
		}

	}

	public static void main(String[] args) {
		String value = "%40g.139-341-na-1%2C183-493-na-1%3B";
		try {
			value = URLDecoder.decode(value, "UTF-8");
			// System.out.println(value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}


}
