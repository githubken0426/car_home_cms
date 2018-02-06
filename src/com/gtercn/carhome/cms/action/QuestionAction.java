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
import com.gtercn.carhome.cms.entity.QuestionArticle;
import com.gtercn.carhome.cms.entity.write.WriteQuestionArticle;
import com.gtercn.carhome.cms.service.questionArcitlce.QuestionArticleService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.GeneratorHtmlToFtp;
import com.gtercn.carhome.cms.util.MyArrayList;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 达人圈--问题墙
 * 
 * @author ken 2017-2-23 下午03:39:05
 */
public class QuestionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private QuestionArticleService questionArticleService;

	private QuestionArticle entity;
	public QuestionArticle getEntity() {
		return entity;
	}
	public void setEntity(QuestionArticle entity) {
		this.entity = entity;
	}
	private WriteQuestionArticle writeEntity;
	public WriteQuestionArticle getWriteEntity() {
		return writeEntity;
	}
	public void setWriteEntity(WriteQuestionArticle writeEntity) {
		this.writeEntity = writeEntity;
	}
	/**
	 * 检索数据
	 * 
	 * @return
	 */
	public String listData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 1);// 1:问题墙 2:车友会 3:文章
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String questionTitle = request.getParameter("content");
			if (questionTitle != null && !questionTitle.equals("")) {
				questionTitle = URLDecoder.decode(questionTitle, "UTF-8");
				map.put("questionTitle", questionTitle);
			}
			String deleteFlag = request.getParameter("deleteFlag");
			if (deleteFlag != null && !deleteFlag.equals("-1")) {
				map.put("deleteFlag", deleteFlag);
			}
			String beginTime = request.getParameter("beginTime");
			if (beginTime != null && !beginTime.equals(""))
				map.put("beginTime", beginTime);
			String endTime = request.getParameter("endTime");
			if (endTime != null && !endTime.equals(""))
				map.put("endTime", endTime);
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = questionArticleService.getTotalCount(map);
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
			List<QuestionArticle> list = questionArticleService
					.queryAllQuestionData(map);

			context.put("QuestionArticleList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("content", questionTitle);
			context.put("deleteFlag", deleteFlag);
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
	public @Deprecated String addDataPage() {
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
			String questionTitle = request.getParameter("questionTitle");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			context.put("currentIndex", currentIndex);
			context.put("questionTitle", questionTitle);
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
	 */
	public @Deprecated String addData() {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			String userId=entity.getUserId();
			String content=entity.getContent();
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			entity.setType(3);
			String serverPath =  request.getSession().getServletContext().getRealPath("/")
					+ "resources" ;
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
					ApplicationConfig.FTP_ARTICLE_PATH,
					ApplicationConfig.FTP_HTML_PATH, userId };
			String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
			entity.setHtmlUrl(htmlPath);
//			questionArticleService.insert(entity);
			writer = response.getWriter();
			writer.print("<script>alert('添加成功!');window.location.href='question_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='question_listData.action';</script>");
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
			String questionTitle = request.getParameter("content");
			String deleteFlag = request.getParameter("deleteFlag");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			QuestionArticle article = questionArticleService.selectQuestionByPrimaryKey(id);
			context.put("entity", article);
			
			context.put("currentIndex", currentIndex);
			context.put("questionTitle", questionTitle);
			context.put("deleteFlag", deleteFlag);
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
	 * @throws Exception 
	 */
	public String updateData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		List<String> displayList = new MyArrayList<String>();
		InputStream in =null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			writer = response.getWriter();
			writeEntity.setType(1);
			String id=writeEntity.getId();
			String viewDisplay1Flag=request.getParameter("viewDisplay1Flag");
			if(StringUtils.isNotBlank(viewDisplay1Flag))
				displayList.add(viewDisplay1Flag);
			String viewDisplay2Flag=request.getParameter("viewDisplay2Flag");
			if(StringUtils.isNotBlank(viewDisplay2Flag))
				displayList.add(viewDisplay2Flag);
			String viewDisplay3Flag=request.getParameter("viewDisplay3Flag");
			if(StringUtils.isNotBlank(viewDisplay3Flag))
				displayList.add(viewDisplay3Flag);
			String viewDisplay4Flag=request.getParameter("viewDisplay4Flag");
			if(StringUtils.isNotBlank(viewDisplay4Flag))
				displayList.add(viewDisplay4Flag);
			String viewDisplay5Flag=request.getParameter("viewDisplay5Flag");
			if(StringUtils.isNotBlank(viewDisplay5Flag))
				displayList.add(viewDisplay5Flag);
			String viewDisplay6Flag=request.getParameter("viewDisplay6Flag");
			if(StringUtils.isNotBlank(viewDisplay6Flag))
				displayList.add(viewDisplay6Flag);
			File[] displayPicture = multipartRequest.getFiles("displayPicture");
			if(displayPicture!=null){
				String[] displaySavePath = { ApplicationConfig.FTP_ROOTPATH,
						ApplicationConfig.FTP_QUESTION_PATH,id };
				for (File file : displayPicture) {
					in = new FileInputStream(file);
					String fileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(displaySavePath,
							fileName, in);
					if (bool) {
						String dbSavePath = File.separator
								+ ApplicationConfig.FTP_ROOTPATH + File.separator
								+ ApplicationConfig.FTP_QUESTION_PATH
								+ File.separator + id + File.separator
								+ fileName;
						displayList.add(dbSavePath);
					}
				}
			}
			writeEntity.setResUrlList(displayList.toString());
			questionArticleService.updateQuestionById(writeEntity);
			writer.print("<script>alert('修改成功!');window.location.href='question_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='question_listData.action';</script>");
		}finally{
			if(in!=null)
				in.close();
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
			questionArticleService.deleteData(ids);
			writer.print("<script>alert('删除成功!');window.location.href='question_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='question_listData.action';</script>");
		}
		return null;
	}

}
