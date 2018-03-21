package com.gtercn.carhome.cms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.entity.City;
import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.service.city.CityService;
import com.gtercn.carhome.cms.service.dealeruser.DealerUserService;
import com.gtercn.carhome.cms.util.CommonUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DealerUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DealerUserService dealerUserService;
	
	@Autowired
	private CityService cityService;

	private DealerUser entity;

	private JSONArray json;

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public DealerUser getEntity() {
		return entity;
	}

	public void setEntity(DealerUser entity) {
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
			// 接收查询参数
			String userName = request.getParameter("userName");
			map.put("userName", userName);

			String password = request.getParameter("password");
			map.put("password", password);

			String realName = request.getParameter("realName");
			map.put("realName", realName);

			String cityId = request.getParameter("cityId");
			map.put("cityId", cityId);


			int pageSize = ApplicationConfig.PAGE_SIZE;// 每页显示10条
			int totalCount = dealerUserService.getTotalCount(map);
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
			List<DealerUser> list = dealerUserService.queryAllData(map);
			// 城市
			List<City> listCity = cityService.getAllInfo();
			List<City> listNewCity = new ArrayList<City>();
			City city = new City();
			city.setCityCode("");
			city.setCityName("请选择");
			listNewCity.add(city);
			for (int i = 0; i < listCity.size(); i++) {
				listNewCity.add(listCity.get(i));
			}
			
			context.put("cityList", listNewCity);
			context.put("DealerUserList", list);
			context.put("totalPages", totalPages);
			context.put("totalCount", totalCount);
			context.put("currentIndex", currentIndex);

			// 设置查询参数
			context.put("userName", userName);
			context.put("password", password);
			context.put("realName", realName);
			context.put("cityId", cityId);


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

			context.put("cityList", getCityInfo());
			context.put("currentIndex", currentIndex);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "add";
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
			int result = dealerUserService.getUserByName(map);
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
			entity.setStatus(0);

			int ret = dealerUserService.addData(entity);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('添加成功!');window.location.href='dealerUser!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('添加失败!');window.location.href='dealerUser!listData.action';</script>");
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
				dealerUserService.deleteData(id);
			}
			writer.print("<script>alert('删除成功!');window.location.href='dealerUser!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('删除失败!');window.location.href='dealerUser!listData.action';</script>");
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
			entity = dealerUserService.getDataById(id);
			context.put("cityList", getCityInfo());
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
			int ret = dealerUserService.updateData(entity);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('修改成功!');window.location.href='dealerUser!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='dealerUser!listData.action';</script>");
		}
		return null;
	}
	
	/**
	 * 修改密码(进入画面)
	 * 
	 * @return
	 */
	public String changePwdPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		try {
			entity = dealerUserService.getDataById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "updatepwd";
	}
	
	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String changePwd() {
		ServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			System.out.println("entity = " + entity.toString());
			int ret = dealerUserService.updateData(entity);
			System.out.println("ret = " + ret);
			writer = response.getWriter();

			System.out.println("entity2 = " + entity.toString());

			writer.print("<script>alert('修改成功!');window.location.href='dealerUser!listData.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');window.location.href='dealerUser!listData.action';</script>");
		}
		return null;
	}

	/**
	 * 获取城市信息
	 * @return
	 */
	private List<City> getCityInfo() {
		// 城市
		List<City> listCity = cityService.getAllInfo();
		List<City> listNewCity = new ArrayList<City>();
		City city = new City();
		city.setCityCode("");
		city.setCityName("请选择");
		listNewCity.add(city);
		for (int i = 0; i < listCity.size(); i++) {
			listNewCity.add(listCity.get(i));
		}
		return listNewCity;
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
			DealerUser login_user = (DealerUser)session.get("dealer_user");
			String newPassword = request.getParameter("newPassword");
			login_user.setPassword(newPassword);
			dealerUserService.updateUser(login_user);
			writer.print("<script>alert('修改成功,请重新登录!');window.top.location.href='signin_logDealOut.action';</script>");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("<script>alert('修改失败!');';</script>");
		}
		return null;
	}
	
	/**
	 * 转换
	 * @param loginUser
	 * @return
	 */
//	private DealerUser change(User loginUser) {
//		
//		DealerUser user = new DealerUser();
//		user.setCityCode(loginUser.getCityCode());
//		user.setGroupId(loginUser.getGroupId());
//		user.setGroupName(loginUser.getGroupName());
//		user.setId(loginUser.getId());
//		user.setLoginIp(loginUser.getLoginIp());
//		user.setLoginNum(loginUser.getLoginNum());
//		user.setLoginTime(loginUser.getLoginTime());
//		user.setPassword(loginUser.getPassword());
//		user.setRealName(loginUser.getRealName());
//		user.setStatus(loginUser.getStatus());
//		user.setUserName(loginUser.getUserName());
//		return user;
//	}
}
