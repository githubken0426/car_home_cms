package com.gtercn.carhome.cms;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gtercn.carhome.cms.util.FilePathBean;
import com.gtercn.carhome.cms.util.FileUtil;
import com.gtercn.carhome.cms.util.PropertiesManager;

/**
 * 系统参数设置
 * 
 * @author ken 2017-2-21 下午04:16:52
 */
public final class ApplicationConfig {
	/**
	 * 项目配置文件管理器，支持热加载
	 */
	public static final PropertiesManager CAR_HOME = new PropertiesManager(
			FileUtil.getClassesPath(new FilePathBean())
					+ "applicationConfig.properties");

	public static final String UEDITOR_FILTER_PATH=CAR_HOME.getValue("ueditor_filter_path");
	// 分页每页显示数据条数
	public final static int PAGE_SIZE = 10;

	// 使用http协议访问ftp资源路径
	public final static String HTTP_PROTOCOL_IP = CAR_HOME
			.getValue("http_protocol_ip");
	
	// ftp服务器信息
	public final static String FTP_IP = CAR_HOME.getValue("ftp_ip");
	public final static int FTP_PORT = Integer.parseInt(CAR_HOME
			.getValue("ftp_port"));
	public final static String FTP_USERNAME = CAR_HOME.getValue("ftp_username");
	public final static String FTP_PASSWORD = CAR_HOME.getValue("ftp_password");

	/**
	 * 默认城市编码(阜新)
	 */
	public static final String DEFAULT_CITY_CODE="210900";
	// ftp存储根路径
	public final static String FTP_ROOTPATH = "carhome";
	// ftp app版本路径
	public final static String FTP_VERSION_PATH = "version";
	// 达人图片存储路径
	public final static String FTP_EXPERT_PATH = "expert";
	//达人文章路径
	public final static String FTP_ARTICLE_PATH = "article";
	//问题墙图片路径
	public final static String FTP_QUESTION_PATH = "question";
	//达人文章生成html路径
	public final static String FTP_HTML_PATH = "htmls";
	//用户模块
	public static final String FTP_USERS_PATH="users";
	//头像文件夹
	public static final String FTP_AVATAR_PATH="avatar";
	//首页轮播路径
	public final static String FTP_HOME_CAROUSEL_PATH = "homecarousel";
	//资讯路径
	public final static String FTP_INFORMATION_PATH = "information";
	//促销
	public final static String FTP_PROMOTION_PATH = "promotion";
	
	public final static String FTP_SHOPPING_PATH = "shopping";
	
	/**
	 * 电商相关图片路径
	 */
	public final static String FTP_ADVER_PATH = "advertisement";
	public final static String FTP_BRAND_PATH = "brandlogo";
	public final static String FTP_GOODS_PATH = "goods";
	public final static String FTP_SMALL_PATH = "small";
	public final static String FTP_BIG_PATH = "big";
	public final static String FTP_DETAIL_PATH = "detail";
	/**
	 * 获取并追加ftp服务器路径 
	 * 可在实体类set方法中可调用此方法
	 * 追加ftp_ip
	 * @param paths
	 *            参数前必须有斜杠(/)
	 * @return 2016-12-28 下午05:05:23
	 */
	public static String appendFtpIpToURL(String paths) {
		if (paths == null || paths.equals(""))
			return null;
		StringBuffer sb = new StringBuffer();
		List<String> list = Arrays.asList(paths.split(","));
		Iterator<String> it = list.iterator();
		boolean hasNext = it.hasNext();
		while (hasNext) {
			String val = it.next();
			sb.append(HTTP_PROTOCOL_IP + val);
			hasNext = it.hasNext();
			if (hasNext)
				sb.append(",");
		}
		return sb.toString();
	}
	
	//洗车服务
	public final static int CLEAN_SERVICE=4100;
	//修车服务
	public final static int REPAIR_SERVICE=5100;
	//保养服务
	public final static int MAINTAIN_SERVICE=6100;
	//轮胎服务
	public final static int TYRE_SERVICE=7100;
}
