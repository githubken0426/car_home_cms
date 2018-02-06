package com.gtercn.carhome.cms.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.entity.QuestionArticle;
import com.gtercn.carhome.cms.entity.Reply;
import com.gtercn.carhome.cms.service.questionArcitlce.QuestionArticleService;
import com.gtercn.carhome.cms.service.replay.ReplyService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReplyAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private QuestionArticleService questionArticleService;
	
	private Reply entity;
	public Reply getEntity() {
		return entity;
	}
	public void setEntity(Reply entity) {
		this.entity = entity;
	}
	
	/**
	 * 问题回复详情
	 * @return
	 * 2017-3-8 下午02:02:32
	 */
	public String questionDetail(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String questionId = request.getParameter("id");
			String questionTitle = request.getParameter("content");
			String deleteFlag = request.getParameter("deleteFlag");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			map.put("typeId", "0");
			map.put("questionId", questionId);
			List<Reply> replyList= replyService.selectByQuestionId(map);
			QuestionArticle question =questionArticleService.selectQuestionByPrimaryKey(questionId);
			
			context.put("question", question);
			context.put("replyList", replyList);
			
			context.put("content", questionTitle);
			context.put("deleteFlag", deleteFlag);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "questionDetail";
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
			String userId = request.getParameter("userId");
			map.put("userId", userId);
			String typeId = request.getParameter("typeId");
			map.put("typeId", typeId);
			String referenceId = request.getParameter("referenceId");
			map.put("referenceId", referenceId);
			String deleteFlag = request.getParameter("deleteFlag");
			map.put("deleteFlag", deleteFlag);
			String insertTime = request.getParameter("insertTime");
			map.put("insertTime", insertTime);

			int pageSize = 15;// 每页显示15条
			int totalCount = replyService.getTotalCount(map);
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
			List<Reply> list = replyService.queryAllData(map);

			context.put("replyList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			context.put("userId", userId);
			context.put("typeId", typeId);
			context.put("referenceId", referenceId);
			context.put("deleteFlag", deleteFlag);
			context.put("insertTime", insertTime);
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
	 * 删除
	 * 
	 * @return
	 */
	public String updateReplyStatus() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map=new HashMap<String,Object>();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String id = request.getParameter("id");
			String deleteFlag = request.getParameter("deleteFlag");
			map.put("id", id);
			map.put("deleteFlag", deleteFlag);
			int result =replyService.updateByPrimaryKey(map);
			writer.print(String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("0");
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
			entity = replyService.getDataById(id);
			context.put("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "update";
	}

}
