package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Version;

public interface VersionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
    
    /**
	 * 
	 * @return
	 */
	public List<Version> queryAllData(Map<String,Object> map);
	
	/**
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String,Object> map);
}