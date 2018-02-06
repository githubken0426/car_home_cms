package com.gtercn.carhome.cms.util;


public final class ToStringUtil {
	/**
	 * 讲数组转换成字符串(带逗号)
	 * @param str
	 * @return
	 */
	public static String arraysToString(String []str){
		String result="";
		for (int i = 0;i < str.length; i++) {
			result += str[i];
			if (i < (str.length - 1))
				result += ",";
		} 
		return result;
	}
	
}
