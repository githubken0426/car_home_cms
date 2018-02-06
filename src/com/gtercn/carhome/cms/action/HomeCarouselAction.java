package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.HomeCarousel;
import com.gtercn.carhome.cms.service.homeCarousel.HomeCarouselService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.GeneratorHtmlToFtp;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 达人圈--文章
 * 
 * @author ken 2017-2-23 下午03:39:05
 */
public class HomeCarouselAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private HomeCarouselService homeCarouselService;

	private HomeCarousel entity;
	
	private JSONArray json;
	
	private File myFile;
	// myFileFileName属性用来封装上传文件的文件名  
    private String myFileFileName;

	public HomeCarousel getEntity() {
		return entity;
	}

	public void setEntity(HomeCarousel entity) {
		this.entity = entity;
	}
	
	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}
	
	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	/**
	 * 检索数据
	 * 
	 * @return
	 */
	public String listData() {
		Map<String, Object> map = new HashMap<String, Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			String title = request.getParameter("title");
			if (title != null && !title.equals("")) {
				title = URLDecoder.decode(title, "UTF-8");
				map.put("title", title);
			}
			String beginTime = request.getParameter("beginTime");
			if (beginTime != null && !beginTime.equals(""))
				map.put("beginTime", beginTime);
			String endTime = request.getParameter("endTime");
			if (endTime != null && !endTime.equals(""))
				map.put("endTime", endTime);
			
			DealerUser user = (DealerUser)session.get("dealer_user");
			map.put("cityCode", user.getCityCode());
			
			// 是否禁用
			String deleteFlag = request.getParameter("deleteFlag");
			if (StringUtils.isBlank(deleteFlag)) {
				deleteFlag = null;
			}
			map.put("deleteFlag", deleteFlag);
			
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = homeCarouselService.getTotalCount(map);
			int currentIndex = 0;// 当前页
			String index = request.getParameter("pno");
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			int totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			map.put("beginResult", (currentIndex - 1) * pageSize);
			map.put("pageSize", pageSize);
			List<HomeCarousel> list = homeCarouselService
					.queryAllData(map);

			context.put("QuestionArticleList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("deleteFlag", deleteFlag);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}

	/**
	 * 添加数据(进入画面)
	 * 
	 * @return
	 */
	public String addDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String addData() throws IOException {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream fileis = null;
		try {
			String content=entity.getContent();
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			
			DealerUser user = (DealerUser)session.get("dealer_user");
			entity.setCityCode(user.getCityCode());
			
			// html生成
			String serverPath =  request.getSession().getServletContext().getRealPath("/")
					+ "resources" ;
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
					ApplicationConfig.FTP_HTML_PATH, ApplicationConfig.FTP_HOME_CAROUSEL_PATH, entity.getId() };
			String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
			entity.setHtmlUrl(htmlPath);
			
			// 轮播图片
			String[] savePath = {ApplicationConfig.FTP_ROOTPATH, ApplicationConfig.FTP_HTML_PATH, ApplicationConfig.FTP_HOME_CAROUSEL_PATH, entity.getId()};
			if (myFile != null) {
				fileis = new FileInputStream(myFile);
				int s = myFileFileName.lastIndexOf(".");
		        String myFileType = myFileFileName.substring(s + 1, myFileFileName.length());
		        String filename = System.currentTimeMillis() + "." + myFileType;
				
				UploadFtpFileTools.uploadFile(savePath, filename, fileis);
				entity.setPictureUrl(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + ApplicationConfig.FTP_HTML_PATH 
						+ File.separator + ApplicationConfig.FTP_HOME_CAROUSEL_PATH
						+ File.separator + entity.getId() + File.separator + filename);
				
			}
			
			homeCarouselService.insert(entity);
			writer = response.getWriter();
			writer.print("<script>alert('添加成功!');window.location.href='homeCarousel_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='homeCarousel_listData.action';</script>");
		} finally {
			if (fileis != null) {
				fileis.close();
			}
		}
		return null;
	}

	
	/**
	 * 修改数据(进入画面)
	 * 
	 * @return
	 */
	public String updateDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			HomeCarousel homeCarousel = homeCarouselService.selectByPrimaryKey(id);
			
//			if (homeCarousel != null && StringUtils.isNotBlank(homeCarousel.getPictureUrl())) {
//				homeCarousel.setPictureUrl(ApplicationConfig.appendFtpIpToURL(homeCarousel.getPictureUrl()));
//			}
			context.put("entity", homeCarousel);
			
			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("ftpServerIp", ApplicationConfig.HTTP_PROTOCOL_IP);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "update";
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String updateData() throws IOException {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream file = null;
		try {
			writer = response.getWriter();
			String picFlag = request.getParameter("picFlag");
			String id = entity.getId();
			String content = entity.getContent();
			HomeCarousel homeCarousel = homeCarouselService.selectByPrimaryKey(id);
			String dbContent = (homeCarousel != null) ? homeCarousel.getContent() : "";
			
			// 内容
			if((StringUtils.isBlank(dbContent) && StringUtils.isNotBlank(content)) 
					|| !StringUtils.equals(dbContent, content)){
				String serverPath =  request.getSession().getServletContext().getRealPath("/")
				+ "resources" ;
				String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
						ApplicationConfig.FTP_HTML_PATH,ApplicationConfig.FTP_HOME_CAROUSEL_PATH, entity.getId()};
				String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
				entity.setHtmlUrl(htmlPath);
			}else{
				String dbHtmlPath = (homeCarousel != null) ? homeCarousel.getHtmlUrl() : "";
				entity.setHtmlUrl(dbHtmlPath);
			}
			
			// 公司头像图片没有改变
			String[] savePath = {ApplicationConfig.FTP_ROOTPATH, ApplicationConfig.FTP_HTML_PATH, ApplicationConfig.FTP_HOME_CAROUSEL_PATH, entity.getId()};
			if (StringUtils.isNotBlank(picFlag) && StringUtils.isBlank(myFileFileName)) {
				entity.setPictureUrl(picFlag);
			// 图片改变
			} else if (picFlag.indexOf(myFileFileName) == -1) {
				
				String picturePath = homeCarousel.getPictureUrl();
				// 删除ftp上的图片
				if (picturePath != null && picturePath.length() > 1) {
					UploadFtpFileTools.deleteFile(picturePath.substring(1));
				}
				
				if (StringUtils.isNotBlank(myFileFileName)) {
					// 上传ftp新的图片
					file = new FileInputStream(myFile);
					//String filename = myFileFileName;
					int s = myFileFileName.lastIndexOf(".");
			        String myFileType = myFileFileName.substring(s + 1, myFileFileName.length());
			        String filename = System.currentTimeMillis() + "." + myFileType;
					
					UploadFtpFileTools.uploadFile(savePath, filename, file);
					entity.setPictureUrl(File.separator + ApplicationConfig.FTP_ROOTPATH + File.separator + ApplicationConfig.FTP_HTML_PATH 
							+ File.separator + ApplicationConfig.FTP_HOME_CAROUSEL_PATH
							+ File.separator + entity.getId() + File.separator + filename);
					file.close();
				}
			} 
			
			homeCarouselService.updateByPrimaryKey(entity);
			writer.print("<script>alert('修改成功!');window.location.href='homeCarousel_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='homeCarousel_listData.action';</script>");
		} finally {
			if (file != null) {
				file.close();
			}
		}
		return null;
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteData() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("id");
			homeCarouselService.deleteData(ids);
			writer.print("<script>alert('删除成功!');window.location.href='homeCarousel_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='homeCarousel_listData.action';</script>");
		}
		return null;
	}

}
