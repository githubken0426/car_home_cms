package com.gtercn.carhome.cms.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.carhome.cms.entity.DealerUser;
import com.gtercn.carhome.cms.entity.Group;
import com.gtercn.carhome.cms.entity.User;
import com.gtercn.carhome.cms.service.group.GroupService;
import com.gtercn.carhome.cms.service.user.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -6017137602949951689L;
	
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
	
	private DealerUser dealerUser;
	
	public DealerUser getDealerUser() {
		return dealerUser;
	}
	public void setDealerUser(DealerUser dealerUser) {
		this.dealerUser = dealerUser;
	}
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			User loginUser = userService.userLogin(user);
			if (loginUser != null) {
				if (loginUser.getStatus() == 1) {
					ServletActionContext.getRequest().setAttribute("loginError", "用户被锁定,请联系管理员解锁！");
					return Action.INPUT;
				}
				Group group = groupService.getGroupById(loginUser.getGroupId());
				if (group != null) {
					String[] functionIds = group.getFuncIds();
					List<String> functionList = (functionIds != null) ? Arrays.asList(functionIds) : new ArrayList<String>();
					String[] pageMenus = { "00","0001","0002","01","0101","0102","0103","0104","0105","0106","0107"};
					for (String menu : pageMenus) {
						session.put("menu" + menu, functionList.contains(menu));
					}
				}
				session.put("login_user", loginUser);
				return Action.SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("loginError","用户名或密码错误！");
				return Action.INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Action.INPUT;
		}
	}
	
	/**
	 * 经销商
	 * 
	 * @return
	 */
	public String deal() {
		return "input1";
	}

	/**
	 * 经销商用户登录
	 * 
	 * @return
	 
	public String logindeal() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			DealerUser loginUser = dealerUserService.userLogin(dealerUser);
			if (loginUser != null) {
				if (loginUser.getStatus() == 1) {
					ServletActionContext.getRequest().setAttribute("loginError", "用户被锁定,请联系管理员解锁！");
					return "input1";
				}
				session.put("dealer_user", loginUser);
				return "success1";
			} else {
				ServletActionContext.getRequest().setAttribute("loginError","用户名或密码错误！");
				return "input1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "input1";
		}
	}
	*/
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logOut() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("login_user");
		user = null;
		return Action.INPUT;
	}
	
	/**
	 * 退出登录
	 * 
	 * @return
	 
	public String logDealOut() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("dealer_user");
		user = null;
		return "input1";
	}
	*/
	public String turnBack(){
		return Action.SUCCESS;
	}
	
	/*public String turnBackDeal(){
		return "success1";
	}*/
}
