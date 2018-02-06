package com.gtercn.carhome.cms.action;

import java.io.PrintWriter;
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
import com.gtercn.carhome.cms.entity.Feedback;
import com.gtercn.carhome.cms.service.feedback.FeedbackService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FeedbackService feedbackService;

	private Feedback entity;

	private JSONArray json;

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public Feedback getEntity() {
		return entity;
	}

	public void setEntity(Feedback entity) {
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
			
			String index = request.getParameter("pno");
			
			// 接收查询参数
			//真实姓名
			String realName = request.getParameter("realName");
			if (StringUtils.isNotBlank(realName) && index != null && index != "") {
				realName = new String(realName.getBytes("iso8859-1"),"UTF-8");
			}
			map.put("realName", realName);
			
			// 反馈内容
			String content = request.getParameter("content");
			if (StringUtils.isNotBlank(content) && index != null && index != "") {
				content = new String(content.getBytes("iso8859-1"),"UTF-8");
			}
			map.put("content", content);

			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示10条
			int totalCount = feedbackService.getTotalCount(map);
			int currentIndex = 0;// 当前页
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			int totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize)
					: (totalCount / pageSize + 1);
			map.put("beginResult", (currentIndex - 1) * pageSize);
			map.put("pageSize", pageSize);
			List<Feedback> list = feedbackService.queryAllData(map);

			context.put("FeedbackList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("realName", realName);
			context.put("content", content);


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
			context.put("currentIndex", currentIndex);
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
	 */
	public String addData() {
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			String uuid = CommonUtil.getUID();
			entity.setId(uuid);

			int ret = feedbackService.addData(entity);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('添加成功!');window.location.href='feedback!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='feedback!listData.action';</script>");
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
			for (String id : ids) {
				if (StringUtils.isBlank(id)) {
					continue;
				}
				feedbackService.deleteData(id);
			}
			writer.print("<script>alert('删除成功!');window.location.href='feedback!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='feedback!listData.action';</script>");
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
		String id = request.getParameter("id");
		try {
			entity = feedbackService.getDataById(id);
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
	public String updateData() {
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			System.out.println("entity = " + entity.toString());

			int ret = feedbackService.updateData(entity);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('修改成功!');window.location.href='feedback!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='feedback!listData.action';</script>");
		}
		return null;
	}

}
