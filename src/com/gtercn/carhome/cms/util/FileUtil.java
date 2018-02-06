package com.gtercn.carhome.cms.util;

import java.net.URL;
import java.security.CodeSource;

import org.apache.log4j.Logger;

public class FileUtil {

	/** 日志记录器 */
	private static Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 查询某一实例对象，其class所在的路径，如在jar包内，则返回jar包路径(有/结尾的格式，如E:/eclipse/workspace/API/
	 * classes/)，默认为空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getRealPath(Object obj) {
		String realPath = "";
		// linux为1,window为0，暂时没有用到
		int systemType = 0;
		try {
			CodeSource cs = obj.getClass().getProtectionDomain()
					.getCodeSource();
			URL result = cs.getLocation();
			if (result != null) {
				realPath = result.toExternalForm();
				// System.out.println("realPath="+realPath);
				// 去除文件路径前的file:标识
				realPath = realPath.substring(6, realPath.lastIndexOf("/") + 1);
				systemType = realPath.matches("[a-zA-Z][:].*") ? 0 : 1;
				// System.out.println("OS System:" + systemType);
			}
			// 转码
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
			// linux
			if (systemType == 1) {
				realPath = "/" + realPath;
			}
			logger.debug("realPath=" + realPath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			realPath = "";
		}
		return realPath;
	}

	/**
	 * 获取某实例所在文件的父目录，主要用于统一放置conf目录用
	 * 
	 * @param obj
	 * @return
	 */
	public static String getParentPath(Object obj) {
		String parentPath = "";
		String realPath = getRealPath(obj);
		parentPath = realPath.substring(0, realPath.substring(0,
				realPath.length() - 1).lastIndexOf("/"))+ "/";
		return parentPath;
	}

	public static String getClassesPath(Object obj) {
		String parentPath = "";
		String realPath = getRealPath(obj);
		parentPath = realPath.substring(0, realPath.substring(0,
				realPath.length() - 1).lastIndexOf("/classes"))
				+ "/classes/";
		return parentPath;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String locateJarFile(Object obj) {
		String jarFile = "";
		// linux为1,window为0，暂时没有用到
//		int systemType = 0;
		try {
			CodeSource cs = obj.getClass().getProtectionDomain()
					.getCodeSource();
			URL result = cs.getLocation();
			if (result != null) {
				jarFile = result.toExternalForm();
			}
			// 转码
			jarFile = java.net.URLDecoder.decode(jarFile, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return jarFile;
	}

}