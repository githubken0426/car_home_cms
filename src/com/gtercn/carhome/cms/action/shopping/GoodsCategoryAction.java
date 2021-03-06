package com.gtercn.carhome.cms.action.shopping;

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
import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;
import com.gtercn.carhome.cms.service.shopping.goodscategory.GoodsCategoryService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品
 * @author ken 2017-2-23 下午03:39:05
 */
public class GoodsCategoryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodsCategoryService categoryService;
	
	private GoodsCategory entity;
	public GoodsCategory getEntity() {
		return entity;
	}
	public void setEntity(GoodsCategory entity) {
		this.entity = entity;
	}

	/**
	 * 分页检索数据
	 * @return
	 */
	public String list() {
		Map<String, Object> map = new HashMap<String, Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String title = request.getParameter("title");
			if (StringUtils.isNotBlank(title)) {
				title = URLDecoder.decode(title, "UTF-8");
				map.put("title", title);
			}
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = categoryService.getTotalCount(map);
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
			List<GoodsCategory> categoryList=categoryService.queryAllData(map);
			
			context.put("categoryList", categoryList);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			// 设置查询参数
			context.put("title", title);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}


	/**
	 * 添加数据
	 * @return
	 * @throws Exception 
	 */
	public void add() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer  = response.getWriter();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);
			// 上传展示图片
			String ftpPaths[] = { ApplicationConfig.FTP_SHOPPING_PATH, ApplicationConfig.FTP_CATEGORY_PATH };
			File[] resUrlList = multipartRequest.getFiles("resUrlList");
			if(resUrlList!=null) {
				for (File file : resUrlList) {
					InputStream input = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths,portraitFileName, input);
					if (bool) {
						String logo = ApplicationConfig.HTTP_PROTOCOL_IP+ File.separator + ApplicationConfig.FTP_SHOPPING_PATH
								+ File.separator + ApplicationConfig.FTP_CATEGORY_PATH
								+ File.separator + portraitFileName;
						entity.setUrl(logo);
					}
					input.close();
				}
			}
			categoryService.insert(entity);
			writer.print("<script>alert('添加成功!');window.location.href='category_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer .print("<script>alert('添加失败!');window.location.href='category_list.action';</script>");
		}
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
			String categoryId = request.getParameter("categoryId");
			String cnName = request.getParameter("cnName");
			String id = request.getParameter("id");
			entity=categoryService.selectByPrimaryKey(id);
			context.put("entity", entity);
			
			context.put("currentIndex", currentIndex);
			context.put("categoryId", categoryId);
			context.put("cnName", cnName);
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
	public void update() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream input =null;
		PrintWriter writer = response.getWriter();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String ftpPaths[] = { ApplicationConfig.FTP_SHOPPING_PATH, ApplicationConfig.FTP_BRAND_PATH };
			// 上传展示图片
			File[] viewResUrlList = multipartRequest.getFiles("updateUrl");
			if(viewResUrlList!=null) {
				for (File file : viewResUrlList) {
					input = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(ftpPaths, portraitFileName, input);
					if (bool) {
						String logo = ApplicationConfig.HTTP_PROTOCOL_IP + File.separator
								+ ApplicationConfig.FTP_SHOPPING_PATH + File.separator 
								+ ApplicationConfig.FTP_BRAND_PATH + File.separator + portraitFileName;
						entity.setUrl(logo);
					}
				}
			}
			categoryService.update(entity);
			writer .print("<script>alert('修改成功!');window.location.href='category_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='category_list.action';</script>");
		}finally{
			if(input!=null)
				input.close();
		}
	}

	/**
	 * 删除
	 * @return
	 */
	public void deleteBatch() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("id");
			categoryService.deleteBatch(ids);
			writer.print("<script>alert('删除成功!');window.location.href='category_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='category_list.action';</script>");
		}
	}
	
}
