package com.gtercn.carhome.cms.annotation;

/**
 * 权限枚举
 * @author Administrator
 * 2015-12-15 上午09:26:32
 *
 */
public enum PermissionEnum {
	//系统管理
	SYS_MANAGER("00"),
	//用户组管理
	GROUP_MANAGER("0001"),
	//用户管理
	USER_MANAGER("0002"),
	//综合业务管理
	INTSERV_MANAGER("01"),
	//达人管理
	EXPERT_MANAGER("02"),
	EXPERT_ADD("0201")
	;
	
	private String functionId;
	private PermissionEnum(String functionId){
		this.functionId=functionId;
	}
	public String getFunctionId() {
		return functionId;
	}
}
