package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.Information;
import com.gtercn.carhome.cms.service.information.InformationService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.GeneratorHtmlToFtp;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 资讯
 * 
 * @author ken 2017-2-23 下午03:39:05
 */
public class InformationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private InformationService informationService;

	private Information information;
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
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
		try {
			String deleteFlag = request.getParameter("deleteFlag");
			if (deleteFlag != null && !deleteFlag.equals("-1")) {
				map.put("deleteFlag", deleteFlag);
			}
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
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = informationService.getTotalCount(map);
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
			List<Information> list = informationService.queryAllData(map);

			context.put("list", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("deleteFlag", deleteFlag);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
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
			String deleteFlag = request.getParameter("deleteFlag");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			context.put("currentIndex", currentIndex);
			context.put("deleteFlag", deleteFlag);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String addData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn = null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String uuid = CommonUtil.getUID();
			String content=information.getContent();
			information.setId(uuid);
			String serverPath =  request.getSession().getServletContext().getRealPath("/")
					+ "resources" ;
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_INFORMATION_PATH, uuid };
			String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
			information.setHtmlUrl(htmlPath);
			// 上传展示图片
			File[] pictureList = multipartRequest.getFiles("pictureList");
			if (pictureList != null) {
				for (File file : pictureList) {
					viewIn = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,
							portraitFileName, viewIn);
					if (bool) {
						String dbPictureList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_INFORMATION_PATH
								+ File.separator + uuid + File.separator
								+ portraitFileName;
						information.setPictureList(dbPictureList);
					}
				}
			}
			informationService.insert(information);
			writer = response.getWriter();
			writer.print("<script>alert('添加成功!');window.location.href='information_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='information_listData.action';</script>");
		}finally{
			if(viewIn!=null)
				viewIn.close();
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
			String deleteFlag = request.getParameter("deleteFlag");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			Information information = informationService.selectByPrimaryKey(id);
			context.put("information", information);
			context.put("currentIndex", currentIndex);
			context.put("deleteFlag", deleteFlag);
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
	 */
	public String updateData() throws Exception{
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn =null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			writer = response.getWriter();
			String id=information.getId();
			String content=information.getContent();
			Information dbInformation=informationService.selectByPrimaryKey(id);			
			String dbContent=(dbInformation!=null) ? dbInformation.getContent():"";
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_INFORMATION_PATH ,id };
			if(StringUtils.isNotBlank(dbContent) && !dbContent.equals(content)){
				String serverPath =  request.getSession().getServletContext().getRealPath("/") + "resources" ;
				String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
				information.setHtmlUrl(htmlPath);
			}else{
				String dbHtmlPath=(dbInformation!=null) ? dbInformation.getHtmlUrl():"";
				information.setHtmlUrl(dbHtmlPath);
			}
			// 上传展示图片
			String pictureListFlag=request.getParameter("pictureListFlag");
			if(StringUtils.isNotBlank(pictureListFlag))
				information.setPictureList(pictureListFlag);
			File[] pictureList = multipartRequest.getFiles("pictureList");
			if (pictureList != null) {
				for (File file : pictureList) {
					viewIn = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,
							portraitFileName, viewIn);
					if (bool) {
						String dbPictureList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_INFORMATION_PATH
								+ File.separator + id + File.separator
								+ portraitFileName;
						information.setPictureList(dbPictureList);
					}
				}
			}
			informationService.updateByPrimaryKey(information);
			writer.print("<script>alert('修改成功!');window.location.href='information_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='information_listData.action';</script>");
		}finally{
			if(viewIn!=null)
				viewIn.close();
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
			informationService.deleteByPrimaryKey(ids);
			writer.print("<script>alert('删除成功!');window.location.href='information_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='information_listData.action';</script>");
		}
		return null;
	}

}
