package com.myshang.server.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.JsonArray;
import com.myshang.server.reward.model.Reward;
import com.myshang.server.user.model.User;

import io.netty.util.internal.MathUtil;
import net.sf.json.JSONObject;

public class GoogleMapsUtil {
	
	//private static double EARTH_RADIUS = 6378.137;
		private static double EARTH_RADIUS = 6371.393;
		private static double rad(double d)
		{
		   return d * Math.PI / 180.0;
		}
	 
		/**
		 * 计算两个经纬度之间的距离
		 * @param lat1
		 * @param lng1
		 * @param lat2
		 * @param lng2
		 * @return
		 */
		public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
		{
		   double radLat1 = rad(lat1);
		   double radLat2 = rad(lat2);
		   double a = radLat1 - radLat2;	
		   double b = rad(lng1) - rad(lng2);
		   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
		    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		   s = s * EARTH_RADIUS;
		   s = Math.round(s * 1000);
		   return s;
		}
		private static Boolean test1(String name){
			return false;
			
		}
		
		public static void main(String[] args) {
			String str1="java",str2=new String("java");
			BigDecimal s1=new BigDecimal(300.00);
			BigDecimal s2=new BigDecimal(100.00);
			System.out.println(s1.compareTo(s2));
			System.out.println(str1.equals(str2));
		}
}
class A{
	int x=100;
}
class B extends A{
	int x=200;
	void prt(){
		System.out.println("SubClass:"+x);
		System.out.println("SuperClass:"+super.x);
	}
	public static void main(String[] args) {
		int numbers[]=new int[10];
		try{
			for(int i=1;i<=10;i++)
			numbers[i]=i;
			
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("下标越界");
		}finally{
			System.out.println("退出函数。 ");
		}
		System.out.println("退出 main()");
	}
}