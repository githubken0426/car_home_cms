package com.gtercn.carhome.cms.entity.shopping;

import java.util.List;

public class Spec {
    private String id;
    private String categoryId;
    private String groupId; 
    private String filter;
    private String name;
    private Integer sort;
    private List<SpecItem> items;//规格选项
    
    private String itme;
    
    public String getItme() {
		return itme;
	}

	public void setItme(String itme) {
		this.itme = itme;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<SpecItem> getItems() {
		return items;
	}

	public void setItems(List<SpecItem> items) {
		this.items = items;
	}
}