package com.gtercn.carhome.cms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
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
import com.gtercn.carhome.cms.entity.APIUser;
import com.gtercn.carhome.cms.entity.ExpertType;
import com.gtercn.carhome.cms.service.apiuser.APIUserService;
import com.gtercn.carhome.cms.service.experttype.ExpertTypeService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.gtercn.carhome.cms.util.UploadFtpFileTools;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * api用户相关
 * 
 * @author ken
 * 2017-3-6 下午01:31:45
 */

public class APIUserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private APIUserService apiUserService;
	@Autowired
	private ExpertTypeService expertTypeService;
	
	private APIUser entity;
	public APIUser getEntity() {
		return entity;
	}
	public void setEntity(APIUser entity) {
		this.entity = entity;
	}

	/**
	 * 用户分页
	 * @return
	 * 2017-3-6 下午01:31:33
	 */
	public String listData() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String deleteFlag = request.getParameter("deleteFlag");
			if (deleteFlag != null && !deleteFlag.equals("-1"))
				map.put("deleteFlag", deleteFlag);
			String loginPhone = request.getParameter("loginPhone");
			if (loginPhone != null && !loginPhone.equals(""))
				map.put("loginPhone", loginPhone);
			String beginTime = request.getParameter("beginTime");
			if (beginTime != null && !beginTime.equals(""))
				map.put("beginTime", beginTime);
			String endTime = request.getParameter("endTime");
			if (endTime != null && !endTime.equals(""))
				map.put("endTime", endTime);
			
			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示数据条
			int totalCount = apiUserService.getTotalCount(map);
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
			List<APIUser> userList = apiUserService.queryAllData(map);
			
			context.put("userList", userList);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);
			
			context.put("deleteFlag", deleteFlag);
			context.put("loginPhone", loginPhone);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("ftpServerIp", ApplicationConfig.HTTP_PROTOCOL_IP);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	public String addDataPage() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			String deleteFlag = request.getParameter("deleteFlag");
			String loginPhone = request.getParameter("loginPhone");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			int currentIndex = 0;// 当前页
			String index = request.getParameter("backPageNo");// 返回，记录列表页数据
			if (index != null && index != "") {
				currentIndex = Integer.valueOf(index);
			} else {
				currentIndex = 1;
			}
			List<ExpertType> typeList = expertTypeService.queryAllData();
			context.put("typeList", typeList);
			
			context.put("currentIndex", currentIndex);
			context.put("deleteFlag", deleteFlag);
			context.put("loginPhone", loginPhone);
			context.put("beginTime", beginTime);
			context.put("endTime", endTime);
			context.put("ftpServerIp", ApplicationConfig.HTTP_PROTOCOL_IP);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
	}

	
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	public String addData() {
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			writer = response.getWriter();
			String userId = CommonUtil.getUID();
			entity.setUserId(userId);
			String pwdMd5=CommonUtil.gernateToMD5(entity.getPassword());
			entity.setPassword(pwdMd5);
			//用户头像上传
			File[] userPortrait = multipartRequest.getFiles("userPortrait");
			if(userPortrait!=null){
				String[] portraitSavePath = { ApplicationConfig.FTP_ROOTPATH,
						ApplicationConfig.FTP_USERS_PATH, ApplicationConfig.FTP_AVATAR_PATH,userId };
				for (File file : userPortrait) {
					InputStream portraitIn = new FileInputStream(file);
					String portraitFileName = System.currentTimeMillis() + ".jpg";
					boolean bool = UploadFtpFileTools.uploadFile(portraitSavePath,
							portraitFileName, portraitIn);
					if (bool) {
						String dbPortaitSavePath = File.separator
								+ ApplicationConfig.FTP_ROOTPATH + File.separator
								+ ApplicationConfig.FTP_USERS_PATH + File.separator
								+ ApplicationConfig.FTP_AVATAR_PATH+ File.separator
								+ userId + File.separator + portraitFileName;
						entity.setAvatarUrl(dbPortaitSavePath);
					}
				}
			}
			apiUserService.insert(entity);
			writer.print("<script>alert('添加成功!');window.location.href='apiUser_listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='apiUser_listData.action';</script>");
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
			String newPassword = request.getParameter("newPassword");
			writer.print("<script>alert('修改成功,请重新登录!');window.top.location.href='signin_logOut.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');';</script>");
		}
		return null;
	}
	
	/**
	 * 查询账号是否存在
	 * @return
	 * 2017-3-6 下午03:06:04
	 */
	public String getUserByLoginPhone(){
		Map<String, Object> map = new HashMap<String, Object>();
		ServletResponse response = ServletActionContext.getResponse();
		ServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String loginPhone = request.getParameter("loginPhone");
			String id = request.getParameter("id");
			map.put("loginPhone", loginPhone);
			map.put("id", id);
			APIUser user = apiUserService.getUserByLoginPhone(map);
			if(user!=null)
				writer.write("1");
			else
				writer.write("0");
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		} 
		return null;
	}
}
