package com.lauguobin.www.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Judge
{
	 /**
	  * 注册时判断用户名是否合法
	  * @param str
	  * @return
	  */
	public static boolean isUserName(String str)
	 {
		 return str.matches("(^[\u4E00-\u9FA5]{2,8}$)|(^[A-Za-z0-9]{6,16}$)");
	 }
	
	public static boolean isChinese(String str)
	{
		 return str.matches("^[\u4E00-\u9FA5]{2,8}$");
	}
	 /**
	  * 判断一个字符串是不是名称
	  * @param str
	  * @return
	  */
	 public static boolean isName(String str)
	 {
		 return str.matches( "^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$");
	 }
	 
	  /**
	   * 判断是不是都是整数
	   * @param str  文本域数据
	   * @return
	   */
	 public static boolean isInteger(String str) 
	 {    
		 if(str.length()<9&&str.matches("(\\d*)"))
			return true;
		 return false;
	 }
	 
	 /**
	  * 判断两个日期是否超过一天
	  * @param date1 起始日
	  * @param date2 终止日
	  * @return
	  */
	 public static boolean isOverdue(String date1,String date2)
	 {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try
		 {
		     Date d1 = df.parse(date1);
		     Date d2 = df.parse(date2);
		     long days = d2.getTime() - d1.getTime();
			 if(days > (24* 3600000))
				 return true;
		 }
		 catch(Exception e){	}
		return false;
	 }
}
