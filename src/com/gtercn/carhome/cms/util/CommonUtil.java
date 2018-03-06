package com.gtercn.carhome.cms.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CommonUtil {

	private static Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static int pageSize = 12;//每页显示12条

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成随机验证码
	 * @return
	 */
	public static String getVerifyCode(int len) {
		String vcode = "";
		for (int i = 0; i < len; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

	/**
	 * md5加密
	 * @param parm
	 * @return
	 */
	public static String gernateToMD5(String parm){
		return DigestUtils.md5Hex(parm);
	}
	public static String stringCap(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 生成token
	 * 
	 * @param userName
	 * @return
	 */
	public static String generateToken(String userName) {
		return DigestUtils.md5Hex(userName + System.currentTimeMillis());
	}

	/**
	 * 生成签名
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	public static String generateSign(HttpServletRequest request, String token) {
		try {
			String url = request.getRequestURL().toString();
			String timestamp = request.getParameter("timestamp");
			String userId = request.getParameter("userid");
			String input = url + "?userid=" + userId + "&token=" + token
					+ "&timestamp=" + timestamp;
			logger.debug("url-> " + input);
			return DigestUtils.md5Hex(input.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String generateSign(String url, String userid,
			String timestamp, String token) {
		try {
			String input = url + "?userid=" + userid + "&token=" + token
					+ "&timestamp=" + timestamp;
			logger.debug("url-> " + input);
			return DigestUtils.md5Hex(input.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String generateTokenFor16(String userName) {
		return generateToken(userName).substring(8, 24);
	}
	/**
	 * 数组输出纯字符串
	 * @param t
	 * @return
	 */
	public static <T> String arrayToString(T[] t) {
		if(t==null)
			return "";
		List<T> list = Arrays.asList(t);
		Iterator<T> it = list.iterator();
		if (!it.hasNext())
			return "";
		StringBuilder sb = new StringBuilder();
		for (;;) {
			T e = it.next();
			sb.append(e);
			if (!it.hasNext())
				return sb.toString();
			sb.append(", ");
		}
	}
	
	/**
	 * 生成随机字符
	 * @param length
	 * @param id
	 * @return
	 */
	public static String randomUpperCode(int length, String id) {
		if (StringUtils.isBlank(id))
			return "";
		Random random = new Random();
		// StringBuffer类型的可以append增加字符
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			// 可生成[0,n)之间的整数，获得随机位置
			int randomNum = random.nextInt(id.length());
			// 获得随机位置对应的字符
			char result = id.charAt(randomNum);
			// 组成一个随机字符串
			sb.append(result);
		}
		String code = sb.toString().toUpperCase() ;
		return code;
	}
}