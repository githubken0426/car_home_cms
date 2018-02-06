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
import com.gtercn.carhome.cms.entity.Group;
import com.gtercn.carhome.cms.entity.User;
import com.gtercn.carhome.cms.service.group.GroupService;
import com.gtercn.carhome.cms.service.user.UserService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@PermissionAnnotation(PermissionEnum.USER_MANAGER)
	public String queryAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String groupId = request.getParameter("groupId");
			if (groupId != null && !groupId.equals("-1")) {
				map.put("groupId", groupId);
			}
			int pageSize = 15;// 每页显示15条
			int totalCount = userService.getTotalCount(map);
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
			List<User> list = userService.queryAllUser(map);
			List<Group> groupList = groupService.getAllGroup();

			context.put("groupList", groupList);
			context.put("userList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("groupId", groupId);
			context.put("currentIndex", currentIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "userList";
	}

	/**
	 * 查询用户是否存在
	 * 
	 * @return
	 */
	public String getUserByName() {
		Map<String, Object> map = new HashMap<String, Object>();
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String userName = request.getParameter("name");
			String id = request.getParameter("id");
			map.put("userName", userName);
			map.put("id", id);
			int result = userService.getUserByName(map);
			if (result >= 1) {
				writer.write("1");
			} else {
				writer.write("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		} finally {
			writer.flush();
			writer.close();
		}
		return null;
	}

	/**
	 * 添加用户页面
	 * 
	 * @return
	 */
	public String addUserPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			String groupId = request.getParameter("groupId");
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			List<Group> groupList = groupService.getAllGroup();
			context.put("groupList", groupList);
			context.put("currentIndex", currentIndex);
			context.put("groupId", groupId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "addPage";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	public String addUser() {
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String id = CommonUtil.getUID();
			user.setId(id);
			userService.addUser(user);
			writer.print("<script>alert('添加成功!');window.location.href='user_queryAllUser.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='user_queryAllUser.action';</script>");
		}
		return null;
	}

	/**
	 * 修改用户页面
	 * 
	 * @return
	 */
	public String updateUserPage() {
		ActionContext context = ActionContext.getContext();
		ServletRequest request = ServletActionContext.getRequest();
		try {
			String groupId = request.getParameter("groupId");
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			String id = request.getParameter("id");
			User u = userService.getUserById(id);
			List<Group> groupList = groupService.getAllGroup();
			context.put("groupList", groupList);
			context.put("user", u);
			context.put("currentIndex", currentIndex);
			context.put("groupId", groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "updatePage";
	}

	/**
	 * 修改用户
	 * 
	 * @return
	 */
	public String updateUser() {
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			userService.updateUser(user);
			writer.print("<script>alert('修改成功!');window.location.href='user_queryAllUser.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='user_queryAllUser.action';</script>");
		}
		return null;
	}

	/**
	 * 批量修改状态
	 * 
	 * @return
	 */
	public String updateUserStatus() {
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String[] ids = request.getParameterValues("userId");
			String status = request.getParameter("userStatus");
			userService.updateUserStatus(ids, status);
			writer.print("<script>alert('修改成功!');window.location.href='user_queryAllUser.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='user_queryAllUser.action';</script>");
		}
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String deleteUser() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String ids[] = request.getParameterValues("userId");
			userService.deleteUser(ids);
			writer.print("<script>alert('删除成功!');window.location.href='user_queryAllUser.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='user_queryAllUser.action';</script>");
		}
		return null;
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	public String changePasswordPage() {

		return "changePwdPage";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String changePassword() {
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			User login_user = (User) session.get("login_user");
			String newPassword = request.getParameter("newPassword");
			login_user.setPassword(newPassword);
			userService.updateUser(login_user);
			writer.print("<script>alert('修改成功,请重新登录!');window.top.location.href='signin_logOut.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');';</script>");
		}
		return null;
	}
}
