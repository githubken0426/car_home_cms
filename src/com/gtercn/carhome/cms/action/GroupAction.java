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

import com.gtercn.carhome.cms.annotation.PermissionAnnotation;
import com.gtercn.carhome.cms.annotation.PermissionEnum;
import com.gtercn.carhome.cms.entity.Function;
import com.gtercn.carhome.cms.entity.Group;
import com.gtercn.carhome.cms.service.function.FunctionService;
import com.gtercn.carhome.cms.service.group.GroupService;
import com.gtercn.carhome.cms.util.ToStringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private FunctionService functionService;
	
	private Group group;
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 用户组分页
	 * @return
	 */
	@PermissionAnnotation(PermissionEnum.GROUP_MANAGER)
	public String queryAllGroup(){
		Map<String,Object> map =new HashMap<String,Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			int pageSize=15;//每页显示15条
			int totalCount=groupService.getTotalCount();
			int currentIndex=0;//当前页
			String index=request.getParameter("pno");
			if(index!=null&& index!=""){
				currentIndex=Integer.valueOf(index);
			}else{
				currentIndex=1;
			}
			int totalPages=(totalCount % pageSize==0)?(totalCount / pageSize):(totalCount / pageSize+1);
			map.put("beginResult", (currentIndex-1)*pageSize);
			map.put("pageSize", pageSize);
			List<Group> list =groupService.queryAllGroup(map);
			context.put("groupList",list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "groupList";
	}
	
	/**
	 * 查询用户是否存在
	 * @return
	 */
	public String getByName(){
		Map<String,Object> map=new HashMap<String,Object>();
		ServletResponse response=ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");       
	    response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer=null;
	    try {
			writer=response.getWriter();
			String groupName=request.getParameter("name");
			String id=request.getParameter("id");
			map.put("groupName",groupName);
			map.put("id",id);
			int result =groupService.getByName(map);
			if (result>=1) {
				writer.write("1");
			} else {
				writer.write("0");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
	    return null;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	public String addPage(){
		ActionContext context = ActionContext.getContext();
		try {
			List<Function> functionList = functionService.getAllFunction();
			context.put("functionList", functionList);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "addPage";
	}
	
	/**
	 * 添加用户组
	 * @return
	 */
	public String addGroup(){
		ServletResponse response=ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");       
	    response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer=null;
		try {
			writer=response.getWriter();
			String [] funcid=request.getParameterValues("funcid");
			if(funcid!=null && funcid.length>0){
				group.setFunctionId(ToStringUtil.arraysToString(funcid));
			}
			groupService.insertGroup(group);
			writer.print("<script>alert('添加成功!');window.location.href='group_queryAllGroup.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='group_queryAllGroup.action';</script>");
		}
		return null;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	public String updatePage(){
		ServletRequest request=ServletActionContext.getRequest();
		ActionContext context = ActionContext.getContext();
		try {
			String id=request.getParameter("id");
			int groupId=0;
			if(id!=null && id!=""){
				groupId=Integer.parseInt(id);
			}
			Group group=groupService.getGroupById(groupId);
			List<Function> functionList = functionService.getAllFunction();
			context.put("functionList", functionList);
			context.put("group", group);
			context.put("groupFuncIds", group.getFuncIds());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "updatePage";
	}
	
	/**
	 * 修改用户组
	 * @return
	 */
	public String updateGroup(){
		ServletResponse response=ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");       
	    response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer=null;
		try {
			writer=response.getWriter();
			String [] funcid=request.getParameterValues("funcid");
			if(funcid!=null && funcid.length>0){
				group.setFunctionId(ToStringUtil.arraysToString(funcid));
			}
			groupService.updateGroup(group);
			writer.print("<script>alert('修改成功!');window.location.href='group_queryAllGroup.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='group_queryAllGroup.action';</script>");
		}
		return null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteGroup(){
		ServletResponse response=ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");       
	    response.setContentType("text/html; charset=utf-8");
	    PrintWriter writer=null;
		try {
			writer=response.getWriter();
			String id=request.getParameter("id");
			groupService.deleteGroup(id);
			writer.print("<script>alert('删除成功!');window.location.href='group_queryAllGroup.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='group_queryAllGroup.action';</script>");
		}
		return null;
	}
}
