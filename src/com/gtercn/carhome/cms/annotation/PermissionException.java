package com.gtercn.carhome.cms.annotation;

public class PermissionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PermissionException() {
		super("用户操作权限失败！");
	}
}
