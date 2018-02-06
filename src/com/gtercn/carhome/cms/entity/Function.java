package com.gtercn.carhome.cms.entity;

import java.util.List;

/**
 * 功能
 * @author Administrator
 *
 */
public class Function {
	private String id;
	private String funcName;
	private String funcDescription;
	private int funcCode;
	private String parentFuncId;
	private List<Function> childFunction;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncDescription() {
		return funcDescription;
	}

	public void setFuncDescription(String funcDescription) {
		this.funcDescription = funcDescription;
	}

	public int getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(int funcCode) {
		this.funcCode = funcCode;
	}

	public String getParentFuncId() {
		return parentFuncId;
	}

	public void setParentFuncId(String parentFuncId) {
		this.parentFuncId = parentFuncId;
	}

	public List<Function> getChildFunction() {
		return childFunction;
	}

	public void setChildFunction(List<Function> childFunction) {
		this.childFunction = childFunction;
	}
}
