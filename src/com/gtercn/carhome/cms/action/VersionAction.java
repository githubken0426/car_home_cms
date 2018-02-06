package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.Version;
import com.gtercn.carhome.cms.service.version.VersionService;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VersionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VersionService versionService;

	private Version entity;
	public Version getEntity() {
		return entity;
	}
	public void setEntity(Version entity) {
		this.entity = entity;
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
			int pageSize = 15;// 每页显示15条
			int totalCount = versionService.getTotalCount(map);
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
			List<Version> list = versionService.queryAllData(map);

			context.put("VersionList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}

	/**
	 * 修改数据(进入画面)
	 * 
	 * @return
	 */
	public String updateDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		try {
			entity = versionService.getDataById(id);
			context.put("entity", entity);
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
	public String updateData()throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream in=null;
		try {
			writer = response.getWriter();
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			File[] versionUrl = multipartRequest.getFiles("versionUrl");
			if(versionUrl!=null){
				String[] portraitSavePath = { ApplicationConfig.FTP_ROOTPATH,
						ApplicationConfig.FTP_VERSION_PATH };
				for (File file : versionUrl) {
					in = new FileInputStream(file);
					String versionName = multipartRequest.getFileNames("versionUrl")[0];
					boolean bool = UploadFtpFileTools.uploadFile(portraitSavePath,versionName, in);
					if (bool) {
						String dbVersionUrl = ApplicationConfig.HTTP_PROTOCOL_IP+"/"
								+ ApplicationConfig.FTP_ROOTPATH +"/"
								+ ApplicationConfig.FTP_VERSION_PATH +"/"+ versionName;
						entity.setUrl(dbVersionUrl);
					}
				}
			}
			versionService.updateData(entity);
			writer.print("<script>alert('修改成功!');window.location.href='version!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='version!listData.action';</script>");
		}finally{
			if(in!=null)
				in.close();
		}
		return null;
	}

}
