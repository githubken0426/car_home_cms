package com.gtercn.carhome.cms.action.shopping;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;
import com.gtercn.carhome.cms.entity.shopping.Spec;
import com.gtercn.carhome.cms.service.shopping.goodscategory.GoodsCategoryService;
import com.gtercn.carhome.cms.service.shopping.spec.SpecService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**
 * 商品
 * @author ken 2017-2-23 下午03:39:05
 */
public class SpecAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SpecService specService;
	@Autowired
	private GoodsCategoryService categoryService;
	
	private Spec entity;
	public Spec getEntity() {
		return entity;
	}
	public void setEntity(Spec entity) {
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
			String categoryId = request.getParameter("searchCategory");
			if (StringUtils.isNotBlank(categoryId) && !"-1".equals(categoryId)) {
				map.put("categoryId", categoryId);
			}
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据
			int totalCount = specService.getTotalCount(map);
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
			List<Spec> specList=specService.queryAllData(map);
			List<GoodsCategory> categoryList= categoryService.selectAllCategory();
			
			context.put("specList", specList);
			context.put("categoryList", categoryList);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			// 设置查询参数
			context.put("searchCategory", categoryId);
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
			String items[]=request.getParameterValues("items");
			entity.setId(CommonUtil.getUID());
			specService.insert(entity,items);
			writer.print("<script>alert('添加成功!');window.location.href='spec_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer .print("<script>alert('添加失败!');window.location.href='spec_list.action';</script>");
		}
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
			String items[]=request.getParameterValues("items");
			specService.update(entity,items);
			writer .print("<script>alert('修改成功!');window.location.href='spec_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='spec_list.action';</script>");
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
			specService.deleteBatch(ids);
			writer.print("<script>alert('删除成功!');window.location.href='spec_list.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='spec_list.action';</script>");
		}
	}
	
	public String selectByPrimaryKey() {
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String id = request.getParameter("id");
			Spec spec = specService.selectByPrimaryKey(id);
			JSONObject json=JSONObject.fromObject(spec);
			writer.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		} finally {
			writer.flush();
			writer.close();
		}
		return null;
	}
}
