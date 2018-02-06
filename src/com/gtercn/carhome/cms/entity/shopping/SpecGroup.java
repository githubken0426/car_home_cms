package com.gtercn.carhome.cms.entity.shopping;

public class SpecGroup {
    private String id;
    private String groupName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}