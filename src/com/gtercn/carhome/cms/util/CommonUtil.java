package com.gtercn.carhome.cms.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
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

}