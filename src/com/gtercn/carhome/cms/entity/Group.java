package com.gtercn.carhome.cms.entity;

/**
 * 用户组
 */
public class Group {
	private int id;
	private String groupName; // 用户组名
	private String groupContent;// 用户组备注
	private String functionId;
	private Integer groupType;
	private String[] funcIds;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupContent() {
		return groupContent;
	}

	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public String[] getFuncIds() {
		if (functionId == null)
			return null;
		funcIds = functionId.split(",");
		return funcIds;
	}

	public void setFuncIds(String[] funcIds) {
		this.funcIds = funcIds;
	}
}
