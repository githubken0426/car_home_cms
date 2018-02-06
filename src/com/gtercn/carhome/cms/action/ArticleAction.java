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
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.QuestionArticle;
import com.gtercn.carhome.cms.entity.write.WriteQuestionArticle;
import com.gtercn.carhome.cms.service.experttop.ExpertTopService;
import com.gtercn.carhome.cms.service.questionArcitlce.QuestionArticleService;
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
public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private QuestionArticleService questionArticleService;
	@Autowired
	private ExpertTopService expertTopService;

	private WriteQuestionArticle entity;

	public WriteQuestionArticle getEntity() {
		return entity;
	}
	public void setEntity(WriteQuestionArticle entity) {
		this.entity = entity;
	}

	/**
	 * 检索数据
	 * 
	 * @return
	 */
	public String listData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 3);// 1:问题墙 2:车友会 3:文章
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = context.getSession();
		try {
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode
					: ApplicationConfig.DEFAULT_CITY_CODE;
			map.put("cityCode", cityCode);

			String expertName = request.getParameter("expertName");
			if (expertName != null && !expertName.equals("")) {
				expertName = URLDecoder.decode(expertName, "UTF-8");
				map.put("expertName", expertName);
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
			
			// 是否禁用
			String deleteFlag = request.getParameter("deleteFlag");
			if (StringUtils.isBlank(deleteFlag)) {
				deleteFlag = null;
			}
			map.put("deleteFlag", deleteFlag);
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
					.queryAllData(map);

			context.put("QuestionArticleList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("expertName", expertName);
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
		Map<String, Object> session = context.getSession();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String expertName = request.getParameter("expertName");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode
					: ApplicationConfig.DEFAULT_CITY_CODE;
			List<ExpertTop> expertList = expertTopService
					.queryAllExpert(cityCode);

			context.put("expertList", expertList);
			context.put("currentIndex", currentIndex);
			context.put("expertName", expertName);
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
	 * @throws Exception 
	 */
	public String addData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn=null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String userId = entity.getUserId();
			String content = entity.getContent();
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			entity.setType(3);
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode : ApplicationConfig.DEFAULT_CITY_CODE;
			entity.setCityCode(cityCode);

			String serverPath = request.getSession().getServletContext() .getRealPath("/") + "resources";
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
					ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_ARTICLE_PATH, userId };
			String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(serverPath,ftpPaths, content);
			entity.setHtmlUrl(htmlPath);
			// 上传展示图片
			File[] resUrlList = multipartRequest.getFiles("resUrlList");
			if (resUrlList != null) {
				for (File file : resUrlList) {
					viewIn = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,
							portraitFileName, viewIn);
					if (bool) {
						String dbResUrlList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_ARTICLE_PATH
								+ File.separator + userId + File.separator
								+ portraitFileName;
						entity.setResUrlList(dbResUrlList);
					}
				}
			}
			questionArticleService.insert(entity);
			writer = response.getWriter();
			writer.print("<script>alert('添加成功!');window.location.href='article_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer .print("<script>alert('添加失败!');window.location.href='article_listData.action';</script>");
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
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String id = request.getParameter("id");
			String expertName = request.getParameter("expertName");
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode = user.getCityCode();
			cityCode = StringUtils.isNotBlank(cityCode) ? cityCode
					: ApplicationConfig.DEFAULT_CITY_CODE;
			List<ExpertTop> expertList = expertTopService
					.queryAllExpert(cityCode);
			QuestionArticle article = questionArticleService
					.selectByPrimaryKey(id);
			context.put("entity", article);
			context.put("expertList", expertList);

			context.put("currentIndex", currentIndex);
			context.put("expertName", expertName);
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
	 * @throws Exception 
	 */
	public String updateData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		InputStream viewIn =null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			writer = response.getWriter();
			String id = entity.getId();
			String userId = entity.getUserId();
			String content = entity.getContent();
			entity.setType(3);
			QuestionArticle article = questionArticleService
					.selectByPrimaryKey(id);
			String dbContent = (article != null) ? article.getContent() : "";
			String ftpPaths[] = { ApplicationConfig.FTP_ROOTPATH,
					ApplicationConfig.FTP_HTML_PATH,
					ApplicationConfig.FTP_ARTICLE_PATH, userId };
			if (StringUtils.isNotBlank(dbContent) && !dbContent.equals(content)) {
				String serverPath = request.getSession().getServletContext()
						.getRealPath("/") + "resources";
				String htmlPath = GeneratorHtmlToFtp.uploadHtmlToFtp(
						serverPath, ftpPaths, content);
				entity.setHtmlUrl(htmlPath);
			} else {
				String dbHtmlPath = (article != null) ? article.getHtmlUrl()
						: "";entity.setHtmlUrl(dbHtmlPath);
			}
			String resUrlListFlag=request.getParameter("resUrlListFlag");
			if(StringUtils.isNotBlank(resUrlListFlag))
				entity.setResUrlList(resUrlListFlag);
			// 上传展示图片
			File[] viewResUrlList = multipartRequest.getFiles("resUrlList");
			if (viewResUrlList != null) {
				for (File file : viewResUrlList) {
					viewIn = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis()+ ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,
							portraitFileName, viewIn);
					if (bool) {
						String dbResUrlList = File.separator + ApplicationConfig.FTP_ROOTPATH
								+ File.separator + ApplicationConfig.FTP_HTML_PATH
								+ File.separator + ApplicationConfig.FTP_ARTICLE_PATH
								+ File.separator + userId + File.separator + portraitFileName;
						entity.setResUrlList(dbResUrlList);
					}
				}
			}
			questionArticleService.updateByPrimaryKey(entity);
			writer .print("<script>alert('修改成功!');window.location.href='article_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='article_listData.action';</script>");
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
			questionArticleService.deleteData(ids);
			writer
					.print("<script>alert('删除成功!');window.location.href='article_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer
					.print("<script>alert('删除失败!');window.location.href='article_listData.action';</script>");
		}
		return null;
	}

}
