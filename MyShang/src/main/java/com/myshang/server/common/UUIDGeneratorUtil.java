package com.myshang.server.common;

import java.util.Random;
import java.util.UUID;
import org.apache.log4j.Logger;

/**
 * @author lidongdong
 * 2017年11月17日 上午11:39:51
 */
public final class UUIDGeneratorUtil {
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",   
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };
	/**
	 * 私有构造函数,不允许实例化
	 */
	private UUIDGeneratorUtil(){}

	/**
	 * 产生一个32位的UUID
	 * @author lidongdong
	 * 2017年11月17日 上午11:41:10
	 * @return
	 */
	public static synchronized String generate32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	  *  
	  * @Title: generateShortUuid 
	  * @Description: 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，
	 				每4个为一组，然后通过模62操作，结果作为索引取出字符，
	 				这样重复率大大降低。
	  * @param @return
	  * @return String    返回类型 
	  * @throws
	  */
	 public static String generate32Random() {
	     StringBuffer shortBuffer = new StringBuffer();
	     Random random = new Random();
	     for (int i = 0; i < 32; i++) {  
	         shortBuffer.append(chars[random.nextInt(52)]);
	     }
	     return shortBuffer.toString();  
	 }
	 /**
	   *  
	  * @Title: generateShortUuid 
	  * @Description: 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，
	 					每4个为一组，然后通过模62操作，结果作为索引取出字符，
	 				这样重复率大大降低。
	  * @param @return
	  * @return String    返回类型 
	  * @throws
	   */
	 public static String generateShortUuid() {  
	     StringBuffer shortBuffer = new StringBuffer();  
	     String uuid = UUID.randomUUID().toString().replace("-", "");  
	     for (int i = 0; i < 8; i++) {  
	         String str = uuid.substring(i * 4, i * 4 + 4);  
	         int x = Integer.parseInt(str, 16);  
	         shortBuffer.append(chars[x % 0x3E]);  
	     }  
	     return shortBuffer.toString();  
	 } 
}
