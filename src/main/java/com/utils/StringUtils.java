/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/elink">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import com.google.common.collect.Maps;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author 刘灵星
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	/****
	 * 如果字符串为NULL,则将字符串变为空""
	 * @param str
	 * @return
	 */
	public static String isNullToEmpty(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str;
	}
	
	/**
	 * 在规定位数内前面自动补0
	 * @param size
	 * @param number
	 * @return
	 */
	public static String leftFillZero(int size,String number){		
		if(StringUtils.isNotBlank(number)){
			if(number.length() < size){
				String result = number;
				for(int i=0;i<size - number.length();i++){
					result ="0"+result;
				}
				return result;
			}else{
				return number;
			}
		}
		return "";
	
	}
	
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	/***
	 * 拼写数据库字段的属性,将"_"的后面一个字符变成大写,然后再将"_"去除,例如:first_nm,拼写成firstNm
	 * @param field
	 * @return
	 */
	public static String spellField(String field){
		if(StringUtils.isBlank(field)) 
			return null;		
		String feildNm = field.toLowerCase();		
		String[] feildNmArr =  feildNm.split("_");
		if(feildNmArr != null && feildNmArr.length > 1){
			feildNm = "";
			for(int i = 0;i <  feildNmArr.length;i ++){
				String str = feildNmArr[i];
				if(i==0){
					feildNm += str;
				}else{
					feildNm += upperFirst(str);
				}
			}
		}		
		return feildNm;
	}
	
	public static String getStrIn(String[] strArr){
		String result = "";
		if(strArr == null || strArr.length <= 0)
			return "''";

		for(String str : strArr){
			result += ",'"+ str +"'";
		}
		if(StringUtils.isNotBlank(result))
			result =  result.substring(1);
		return result;
	}
	
	public static String getStrInFroList(List<String> list){
		String result = "";
		if(list == null || list.size() <= 0)
			return "''";

		for(String str : list){
			result += ",'"+ str +"'";
		}
		if(StringUtils.isNotBlank(result))
			result =  result.substring(1);
		return result;
	}
	
	
	/***
	 * 数据库的字段类型转换为JAVA数据类型
	 * @param sqlType
	 * @return
	 */
	public static String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "bool";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("double")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")
				|| sqlType.equalsIgnoreCase("date")) {
			return "java.util.Date";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "String";
		}
		return null;
	}
	
	public static String formatHtml(String str){
		String result = str;
		if(StringUtils.isBlank(str))
			return str;
		Map<String, String> map = getHtmlStringMap();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();		
			String val = (String)entry.getValue();
			String key = (String)entry.getKey();
			result = result.replaceAll(val,key);
		}
		return result;		
	}
	
	public static Map<String,String>getHtmlStringMap(){
		Map<String,String> map = Maps.newHashMap();
		map.put("", "&nbsp;");
		map.put("<", "&lt;");
		map.put(">", "&gt;");
		map.put("&", "&amp;");
		map.put("\"", "&quot;");
		map.put("'", "&apos;");
		map.put("￠", "&cent;");
		map.put("£", "&pound;");
		map.put("¥", "&yen;");
		map.put("€", "&euro;");
		map.put("§", "&sect;");
		map.put("©", "&copy;");
		map.put("®", "&reg;");
		map.put("™", "&trade;");
		map.put("×", "&times;");
		map.put("÷", "&divide;");
		map.put("“", "&ldquo;");
		map.put("”", "&rdquo;");
		map.put("...", "&hellip;");
		return map;
	}
	
	/***
	 * 判断Str1的字符串是否有包含Str2
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean containStr(String str1,String str2){
		if(StringUtils.isEmpty(str1))
			return false;
		if(StringUtils.isEmpty(str2))
			return false;
		
		int   i =  str1.indexOf(str2);
		return i>= 0;
	}
	
	/***
	 * 判断Str1的字符串是否有包含Str2
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean containInt(String str1,String str2){
		if(StringUtils.isEmpty(str1))
			return false;
		if(StringUtils.isEmpty(str2))
			return false;
		int   i = 0;
		String[] strs = str1.split(",");
		for(String str : strs){
			if(Integer.valueOf(str) == Integer.valueOf(str2)){
				++i;
			}
		}
		return i> 0;
	}
	
	/***
	 * 生成随机码
	 * @param g
	 * @return
	 */
	public static String createCharacter() {
		char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
				'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
			s.append(r);
		}
		return s.toString();
	}
	
	public static void main(String[] args){
	
		double height_m = 173 / 100d ;//身高（M）
		System.out.println(NumberUtils.formatDouble(99 / (height_m * height_m),2));
	}
}
