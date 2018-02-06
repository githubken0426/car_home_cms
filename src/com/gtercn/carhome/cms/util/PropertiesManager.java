package com.gtercn.carhome.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class PropertiesManager extends Thread {
	/** properties配置文件完整路径(带文件名) */
	private String filePath = null;
	/** 配置文件 */
	private File file = null;
	/** properties对象 */
	private Properties prop;
	/** 配置文件最后修改时间，用来控制自动加载 */
	private long lastModifyTime;
	/** 日志记录器 */
	private static Logger logger = Logger.getLogger(PropertiesManager.class);

	/**
	 * 构造器
	 * 
	 * @param _filePath
	 *            文件完整路径
	 */
	public PropertiesManager(String _filePath) {
		this.filePath = _filePath;
		this.start();
	}

	/**
	 * 线程定时运行，不停止
	 */
	public void run() {
		logger.debug("PropertiesManager.run()");
		while (true) {// 每10分钟检测并尝试自动加载
			init();
			try {
				Thread.sleep(10 * 60);// 休息10分钟
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据key值获取对应的value
	 * 
	 * @param key
	 * @return
	 */
	public synchronized String getValue(String key) {
		if (prop == null) {
			init();
		}
		return prop.getProperty(key);
	}

	/**
	 * 根据key值获取对应的value（中文）
	 * 
	 * @param key
	 * @return
	 */
	public synchronized String getValue4GBK(String key) {
		String value = null;
		if (prop == null) {
			init();
		}
		value = prop.getProperty(key);
		if (value != null) {
			try {
				value = new String(value.getBytes("iso-8859-1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 获取配置文件中所有key值
	 * 
	 * @return
	 */
	public synchronized Set<Object> getKeys() {
		return prop.keySet();
	}

	/**
	 * 初始化，
	 * 
	 */
	private synchronized void init() {
		file = new File(filePath);
		// 第一次加载
		if (prop == null && checkFile()) {
			logger.debug("prop is null, reload it now!");
			loadProperties();
		}
		// 已修改
		if (checkModifyTime() && checkFile()) {
			logger.debug("check modify & format over, reload now!");
			loadProperties();
		}
	}

	/**
	 * 检查配置文件的最后修改时间并与记录作对比
	 * 
	 * @return true=修改过,false=未修改
	 */
	private synchronized boolean checkModifyTime() {
		boolean result = false;
		// 文件未实例化或已被修改过
		if (file == null || file.lastModified() > lastModifyTime) {
			logger.debug(filePath + " has been changed!");
			result = true;
		}
		return result;
	}

	/**
	 * 检查配置文件格式
	 * 
	 * @return true=格式正确,false=格式错误
	 */
	private synchronized boolean checkFile() {
		boolean result = false;
		if (true) {
			logger.debug(filePath + " format is OK!");
			result = true;
		}
		return result;
	}

	/**
	 * 读取配置文件
	 * 
	 */
	private synchronized void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
			fis.close();
			this.lastModifyTime = file.lastModified();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}