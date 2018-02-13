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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.shopping.Product;
import com.gtercn.carhome.cms.service.shopping.product.ProductService;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 产品
 * @author ken 2017-2-23 下午03:39:05
 */
public class ProductAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService productService;
	
	private Product entity;
	public Product getEntity() {
		return entity;
	}
	public void setEntity(Product entity) {
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
		Map<String, Object> session = context.getSession();
		try {
			DealerUser user = (DealerUser) session.get("dealer_user");
			String cityCode =ApplicationConfig.DEFAULT_CITY_CODE;
			if(null!=user) 
				cityCode = user.getCityCode();
			map.put("cityCode", cityCode);
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
			int totalCount = productService.getTotalCount(map);
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
			List<Product> list = productService.queryAllData(map);

			context.put("list", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			// 设置查询参数
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
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			context.put("currentIndex", currentIndex);
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
	 * @return
	 * @throws Exception 
	 */
	public void addData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream input=null;
		PrintWriter writer  = response.getWriter();
		try {
			
			writer.print("<script>alert('添加成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer .print("<script>alert('添加失败!');window.location.href='goods_list.action';</script>");
		}finally{
			if(input!=null)
				input.close();
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
			String title = request.getParameter("title");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String id = request.getParameter("id");
			entity=productService.selectByPrimaryKey(id);
			
			context.put("entity", entity);
			context.put("currentIndex", currentIndex);
			context.put("title", title);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
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
	public void updateData() throws Exception {
		ServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream input =null;
		PrintWriter writer = response.getWriter();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String ftpPaths[] = { ApplicationConfig.FTP_SHOPPING_PATH, ApplicationConfig.FTP_ADVER_PATH };
			// 上传展示图片
			File[] viewResUrlList = multipartRequest.getFiles("resUrlList");
			for (File file : viewResUrlList) {
				input = new FileInputStream(file);
				String portraitFileName = System.currentTimeMillis() + ".jpg";
				boolean bool = UploadFtpFileTools.uploadFile(ftpPaths, portraitFileName, input);
				if (bool) {
					String picturePath = File.separator + ApplicationConfig.FTP_SHOPPING_PATH + File.separator
							+ ApplicationConfig.FTP_ADVER_PATH + File.separator + portraitFileName;
					//entity.setPicturePath(picturePath);
				}
			}
			
			writer .print("<script>alert('修改成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='goods_list.action';</script>");
		}finally{
			if(input!=null)
				input.close();
		}
	}

	/**
	 * 删除
	 * @return
	 */
	public void deleteData() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("id");
			productService.deleteBatch(ids);
			writer.print("<script>alert('商品下架成功!');window.location.href='goods_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('商品下架失败!');window.location.href='goods_list.action';</script>");
		}
	}
}
