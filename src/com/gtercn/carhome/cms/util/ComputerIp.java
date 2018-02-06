package com.gtercn.carhome.cms.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本机ip
 * @author Administrator
 * 2015-12-17 下午08:18:03
 *
 */
public class ComputerIp {
	public static String getComputerIp() throws UnknownHostException{
		 InetAddress addr=InetAddress.getLocalHost();  
	     String ip=addr.getHostAddress().toString();//获得本机IP
	     return ip;
	}
}
