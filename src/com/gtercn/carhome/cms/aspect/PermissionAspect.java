package com.gtercn.carhome.cms.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gtercn.carhome.cms.annotation.PermissionAnnotation;
import com.gtercn.carhome.cms.annotation.PermissionException;
import com.gtercn.carhome.cms.entity.Group;
import com.gtercn.carhome.cms.entity.User;
import com.gtercn.carhome.cms.service.group.GroupService;
import com.opensymphony.xwork2.ActionContext;
import common.Logger;

@Aspect
@Component
public class PermissionAspect {
	private Logger logger = Logger.getLogger(PermissionAspect.class);

	@Autowired
	private GroupService groupService;

	@Before("@annotation(auth)")
	public void doCheck(PermissionAnnotation auth) throws PermissionException {
		logger.debug("PermissionAspect.doCheck()");
		// Object[] objs = jp.getArgs();
		// HttpServletRequest request = (HttpServletRequest) objs[0];
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User) session.get("login_user");
		int groupId = loginUser != null ? loginUser.getGroupId() : 0;
		Group group = groupService.getGroupById(groupId);
		String[] funcIds = group.getFuncIds();
		List<String> funcList = (funcIds != null) ? Arrays.asList(funcIds)
				: new ArrayList<String>();
		if (!funcList.contains(auth.value().getFunctionId())) {
			request.setAttribute("msg", "对不起，您没有此功能的操作权限！");
			throw new PermissionException();
		}
	}

}