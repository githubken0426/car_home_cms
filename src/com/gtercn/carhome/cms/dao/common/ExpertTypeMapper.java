package com.gtercn.carhome.cms.dao.common;

import java.util.List;

import com.gtercn.carhome.cms.entity.ExpertType;

public interface ExpertTypeMapper {
	/**
	 * 查询所有类别
	 * @return
	 * 2017-2-21 下午02:37:58
	 */
	List<ExpertType> queryAllData();
	
    int deleteByPrimaryKey(String id);
    int insert(ExpertType record);
    ExpertType selectByPrimaryKey(String id);
 
}