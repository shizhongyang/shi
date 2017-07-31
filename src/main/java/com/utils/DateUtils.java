/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/elink">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import org.apache.commons.lang3.time.DateFormatUtils;



/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author 刘灵星
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy-MM-dd/HH:mm:ss" };
	
	 private final static SimpleDateFormat longSdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
	 private final static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	 

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
		
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	

	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if(date == null) return formatDate;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}
	
	/**
	 * 得到当前月份字符串 格式（M）
	 */
	public static String getMonthM() {
		return formatDate(new Date(), "M");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 当前日期+时分秒转化为日期格式
	 * @param hms 时分秒
	 * @return
	 * @author 周贤舟 2015年12月8日
	 */
	public static java.sql.Date parseDateHms(String hms){
		if(hms  == null){
			return null;
		}
		try {
			String date = formatDate(new Date()) + " " + hms;
			long time =  parseDate(date).getTime();
			return  new java.sql.Date(time);
		} catch (Exception e) {
			return null;
		}
	}
	

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/***
	 * 根据未来的日期获取剩余天数
	 * @param date 未来的日期
	 * @return
	 */
	public static long getSusurplusDays(Date date){
		if(date == null)
			return 0;
		long t = date.getTime() - new Date().getTime();
		return t/(24*60*60*1000);
	}
	
    /***
     * 获取日期开始时间
     * @param date
     * @return
     */
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
    /***
     * 获取日期结束时间
     * @param date
     * @return
     */
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/***
	 * 与当前日期比较,是否大于当前日期
	 * @param date1
	 * @return
	 */
	public static boolean compareCurrDate(Date date1){
		if(date1 == null)
			return false;
		Date date = new Date();		
		return date.compareTo(date1)==-1?true:false;
	}
	
	/***
	 * 比较当前时间是否在两个时间字符串的范围
	 * @param begTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String begTime,String endTime){
		boolean result = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTimeStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String currentDate = DateUtils.getDate();
		try {
			Date dt1 = df.parse(currentDate + " " + begTime + ":00");
			Date dt2 = df.parse(currentDate + " " + endTime + ":00");
			Date currentTime = df.parse(currentTimeStr);
			if (currentTime.getTime() >= dt1.getTime() && currentTime.getTime()<= dt2.getTime()) {
				result = true;
			} 
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	
	public static boolean compareTime(long begTime, long endTime){
		try {
			if((begTime - endTime) >= 1800000)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/***
	 * 判断当前时间是否超过传递参数的时间（当天）,如未超过，返回true,超过，返回false
	 * @param time
	 * @return
	 */
	public static boolean compareTime(String time){
		boolean result = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTimeStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String currentDate = DateUtils.getDate();
		try {
			Date dt1 = df.parse(currentDate + " " + time + ":00");
			Date currentTime = df.parse(currentTimeStr);
			if (currentTime.getTime() <= dt1.getTime()) {
				result = true;
			} 
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	
	/***
	 * 判断传递的日期是否大于当前日期，如果是大于，则返回true,反之，返回false
	 * @param date
	 * @return
	 */
	public static boolean compareDate(String date){
		boolean result = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		String currentDate = DateUtils.getDate();
		try {
			Date dt1 = df.parse(date);
			Date currentTime = df.parse(currentDate);
			if (currentTime.getTime() <= dt1.getTime()) {
				result = true;
			} 
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	
	/***
	 * 判断传递的日期是否为当前日期，如果是，则返回true,反之，返回false
	 * @param date
	 * @return
	 */
	public static boolean getDateCompare(Date date){
		Date currentDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		if(format.format(currentDate).compareTo(format.format(date)) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据年龄获取年份
	 * @param age
	 * @return
	 * @author 周贤舟 2015年5月6日
	 */
	public static String getYearByAge(String age){
		if(StringUtils.isNumeric(age)){
			int curYear = Integer.parseInt(getYear());
			int year = curYear - Integer.parseInt(age);
			return year + "";
		}
		return null;
		
	}
	
	/****
	 * 根据出生日期获取年龄
	 * @return
	 */
	public static int getYearByBrithDate(Date brithDate){
		int age = 0;
		int curYear = Integer.parseInt(getYear());
		String brithYear = formatDate(brithDate, "yyyy");
		if(StringUtils.isBlank(brithYear)){
			age = 0;
		}else if(NumberUtils.isInteger(brithYear)){
			int brithYearInt = Integer.parseInt(brithYear);
			age = curYear - brithYearInt;
		}		
		return age;
	}
	
	
	/***
	 * 获取到期日期
	 * @param createDate
	 * @param type
	 * @return
	 */
	public static Date getSurplusDate(String type){	
		Date date = new Date();
		Date pastDate = new Date();
		if("1".equals(type)){//月
			pastDate = DateUtils.addMonths(date,1);
		}else if("2".equals(type)){//季
			pastDate = DateUtils.addMonths(date,3);
		}else if("3".equals(type)){//半年
			pastDate = DateUtils.addMonths(date,6);
		}else if("4".equals(type)){//年
			pastDate = DateUtils.addMonths(date,12);
		}
		
		return pastDate;
	}
	
	/***
	 * 获取到期日期
	 * @param createDate
	 * @param type
	 * @return
	 */
	public static Date getSurplusDate(String type,Date date){	
		Date pastDate = new Date();
		if(type.equals(Consts.TYPE_MONTH)){//月
			pastDate = DateUtils.addMonths(date,1);
		}else if(type.equals(Consts.TYPE_SEASON)){//季
			pastDate = DateUtils.addMonths(date,3);
		}else if(type.equals(Consts.TYPE_HALF)){//半年
			pastDate = DateUtils.addMonths(date,6);
		}else if(type.equals(Consts.TYPE_YEAR)){//年
			pastDate = DateUtils.addMonths(date,12);
		}
		return pastDate;
	}
	
	
	/***
	 * 获取剩余天数
	 * @param createDate
	 * @param type
	 * @return
	 */
	public static long getSurplusDayNew(String createDate ,String surplusDate){
		Date date =  new Date();// DateUtils.parseDate(createDate);
	
		Date pastDate = DateUtils.parseDate(surplusDate);
		if(pastDate == null)
			return 0;
			
	
		long l = (24*60*60*1000);
		long t = pastDate.getTime() - date.getTime();
		long resut =  t/l;
		if(resut <= 0)
			resut = 0;
		return resut;
	}
	
	/***
	 * 获取剩余天数
	 * @param createDate
	 * @param type
	 * @return
	 */
	public static long getSurplusDayRemove(String createDate ,String type){
		Date date = DateUtils.parseDate(createDate);
		if(date == null)
			return 0;
		Date pastDate = new Date();
		if("月".equals(type)){//月
			pastDate = DateUtils.addMonths(date,1);
		}else if("季".equals(type)){//季
			pastDate = DateUtils.addMonths(date,3);
		}else if("半年".equals(type)){//半年
			pastDate = DateUtils.addMonths(date,6);
		}else if("年".equals(type)){//年
			pastDate = DateUtils.addMonths(date,12);
		}
		long l = (24*60*60*1000);
		long t = pastDate.getTime() - date.getTime();
		long resut =  t/l;
		return resut;
	}
	
	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException("生日 在当前日期之后，不是一个有效的生日！");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}
	
	

	public static Date parseDate(Object str,String partten){
		if (str == null){
			return null;
		}
		try {
			 SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmss");
			 return sdf.parse(str.toString() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /** 
     * 获得月的开始时间
     * @return 
     */ 
    public static Date getMonthStartTime(int year , int month) { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try { 
        	c.set(Calendar.YEAR, year);
        	c.set(Calendar.MONTH, month-1); 
            c.set(Calendar.DATE, 1); 
            now = shortSdf.parse(shortSdf.format(c.getTime())); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    }
    
    /** 
     * 当前月的结束时间
     * @return 
     */ 
    public  static Date getMonthEndTime(int year, int month) { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try { 
        	c.set(Calendar.YEAR, year);
            c.set(Calendar.DATE, 1); 
            c.set(Calendar.MONTH, month-1); 
            c.add(Calendar.MONTH, 1); 
            c.add(Calendar.DATE, -1); 
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    } 
	
	/**
	 * 获取季度的开始时间
	 * @param quarter 1:一季度 2:二季度 3:三季度 4:四季度
	 * @return
	 * @author 周贤舟 2015年10月27日
	 */
	public static Date getQuarterStartTime(int year, int quarter){
		Calendar c = Calendar.getInstance();
		Date  now = null;
		try {
			c.set(Calendar.YEAR, year);
			if( quarter == 1 ){
				c.set(Calendar.MONTH, 0);
			}else if( quarter == 2 ){
				c.set(Calendar.MONTH, 3);
			}else if( quarter == 3){
				c.set(Calendar.MONTH, 6);
			}else if( quarter == 4){
				c.set(Calendar.MONTH, 9);
			}
			c.set(Calendar.DATE, 1); 
			now = longSdf.parse(shortSdf.format(c.getTime())+" 00:00:00");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return now;
	}
	
	/**
	 * 获取季度的结束时间
	 * @param quarter 1:一季度 2:二季度 3:三季度 4:四季度
	 * @return
	 * @author 周贤舟 2015年10月27日
	 */
	public static Date getQuarterEndTime(int year, int quarter){
		Calendar c = Calendar.getInstance(); 
		Date now = null; 
		try {
			c.set(Calendar.YEAR, year);
			if( quarter == 1 ){
				c.set(Calendar.MONTH, 2); 
				c.set(Calendar.DATE, 31); 
			}else if( quarter == 2 ){
				c.set(Calendar.MONTH, 5); 
                c.set(Calendar.DATE, 30);
			}else if( quarter == 3){
				c.set(Calendar.MONTH,8); 
                c.set(Calendar.DATE, 30); 
			}else if( quarter == 4){
				c.set(Calendar.MONTH, 11); 
                c.set(Calendar.DATE, 31); 
			}
			 now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now; 
	}
	
	/**
	 * 根据月份获取所在季度
	 * @author 周贤舟 2016年2月16日
	 */
	public static String getSeason(Date date){
		String month = formatDate(date, "MM");
		if(StringUtils.isNotBlank(month)){
			int m = Integer.parseInt(month);
			if(m >= 1 && m <= 3){
				return "第一季度";
			}else if(m >= 4 && m <= 6){
				return "第二季度";
			}if(m >= 7 && m <= 9){
				return "第三季度";
			}if(m >= 10 && m <= 12){
				return "第四季度";
			}
		}
		return "";
	}
	
	/**
	 * 根据月份获取所在季度
	 * @author 周贤舟 2016年2月16日
	 */
	public static String getSeasonI(){
		String month = getMonth();
		if(StringUtils.isNotBlank(month)){
			int m = Integer.parseInt(month);
			if(m >= 1 && m <= 3){
				return "1";
			}else if(m >= 4 && m <= 6){
				return "2";
			}if(m >= 7 && m <= 9){
				return "3";
			}if(m >= 10 && m <= 12){
				return "4";
			}
		}
		return "";
	}
	
	/** 
     * 获取年份的开始时间
     * @param year 年份
     * @return 
     */ 
    public static Date getYearStartTime(int year) { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try {
        	c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 0); 
            c.set(Calendar.DATE, 1); 
            now = shortSdf.parse(shortSdf.format(c.getTime())); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    } 
	  /** 
     * 获取年份的结束时间
     * @param year 年份
     * @return 
     */ 
    public static  Date getYearEndTime(int year) { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try { 
        	c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 11); 
            c.set(Calendar.DATE, 31); 
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    }
	
    /**
     *  获取两个时间的相差小时
     * @author 周贤舟 2016年1月21日
     * @throws ParseException 
     */
    public static long getSurplusHours(String adate, String edate){ 
    	long hour = 0; 
    	long min = 0;    
        long nh = 1000 * 60 * 60;// 一小时的毫秒数    
        long nm = 1000 * 60;// 一分钟的毫秒数
    	try {
    		long diff  = dateSdf.parse(edate).getTime() -dateSdf.parse(adate).getTime(); 
    		hour = diff / nh;// 计算差多少小时
    		min = (diff % nh) / nm; //剩余多少分钟
    		if(min > 30){
    			hour ++ ;
    		}
    		return hour;
		} catch (Exception e) {
			return 0;
		}
    }
    /**
     * String转date
     */
	public static Date getDateByString(String dataStr){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			return format.parse(dataStr);
		} catch (ParseException e) {
			return null;
		}
	}
	 /***
	  * String转dateTime 
	  * @param dataStr yyyy-MM-dd HH:mm:ss
	  * @return
	  */
	public static Date getDateTimeByString(String dataStr){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try {
			return format.parse(dataStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	 /***
	  * long转String 
	  * @param dataStr yyyy-MM-dd hh:mm:ss
	  * @return
	  */
	public static String getDateTimeByString(long dateStr,String partten){
		DateFormat format = new SimpleDateFormat(partten);  
		Date date = new Date(dateStr);
		return format.format(date);
	}
	
	/**
	 * 秒转换成时分秒
	 * @param time
	 * @return
	 */
	 public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }  
	  
    public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
    
    /**
	 * 获取当前月的前几个月
	 * @param date
	 * @return
	 */
	public static String getLastDate(Integer month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -month);
		return sdf.format(cal.getTime());
	}
	
	/***
	 * 根据参数获取过去的日期
	 * @param statType (1:最近一周，2:最近一月，3:最近一季, 4: 最近一年,4:自定义)
	 * @return
	 */
	public static String getPastDate(String statType){
		String pastDate = "";
		if(Consts.STATE_TYPE_WEEK.equals(statType)){						
			pastDate = DateUtils.formatDate(DateUtils.addDays(new Date(), -7));				
		}else if(Consts.STATE_TYPE_MONTH.equals(statType)){				
			pastDate = DateUtils.formatDate(DateUtils.addMonths(new Date(),-1));
		}else if(Consts.STATE_TYPE_SEASON.equals(statType)){					
			pastDate = DateUtils.formatDate(DateUtils.addMonths(new Date(),-3));	
		}else if(Consts.STATE_TYPE_YEAR.equals(statType)){					
			pastDate = DateUtils.formatDate(DateUtils.addYears(new Date(),-1));	
		}else {
			pastDate = DateUtils.formatDate(DateUtils.addDays(new Date(), -7));		
		}
		return pastDate;
	}
	
	/***
	 * 获取时间距离当天结束的分钟数
	 * @param date
	 * @return
	 */
    public static long getLastMinutes(Date date){ 
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
    	Date edate;
    	long min = 0;    
        long nm = 1000 * 60;// 一分钟的毫秒数
		try {
			edate = format.parse(formatDate(date, "yyyy-MM-dd")+" 24:00:00");
			long diff  = edate.getTime() -date.getTime(); 
			min = diff / nm;// 计算差多少分钟
    		return min;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
    }
    /***
	 * 时间添加分钟数
	 * @param dateTime 时间
	 * @param minutes 要添加的分钟数
	 * @return
	 */
	public static Date addMinutesToDateTime(Date dateTime,int minutes){
		Calendar nowTime = Calendar.getInstance();
		nowTime.get(Calendar.HOUR_OF_DAY);
		nowTime.setTime(dateTime);
		nowTime.add(Calendar.MINUTE, minutes);
		return nowTime.getTime();
	}
	 /***
	 * 时间添加天数
	 * @param dateTime 时间
	 * @param minutes 要添加的分钟数
	 * @return
	 */
	public static Date addDaysToDateTime(Date dateTime,int days){
		Calendar nowTime = Calendar.getInstance();
		nowTime.get(Calendar.HOUR_OF_DAY);
		nowTime.setTime(dateTime);
		nowTime.add(Calendar.DAY_OF_MONTH, days);
		return nowTime.getTime();
	}
	/***
	 * 判断时间是否在两个时间之间
	 * @param startDate
	 * @param endDate
	 * @param compareDate
	 * @return
	 */
	public static boolean compareDate(Date startDate,Date endDate,Date compareDate){
		if(startDate.getTime()<=compareDate.getTime()&&compareDate.getTime()<=endDate.getTime()){
			return true;
		}
		return false;
	}
}
