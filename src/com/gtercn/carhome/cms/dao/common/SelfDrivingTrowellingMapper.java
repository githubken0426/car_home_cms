package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.SelfDrivingExtend;
import com.gtercn.carhome.cms.entity.SelfDrivingTrowelling;

public interface SelfDrivingTrowellingMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDrivingTrowelling record);

    int insertSelective(SelfDrivingTrowelling record);

    SelfDrivingTrowelling selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDrivingTrowelling record);
    
    int update(SelfDrivingExtend record);
    
    List<SelfDrivingExtend> selectByTrowellingId(String id);

    int updateByPrimaryKey(SelfDrivingTrowelling record);
    
    /**
	 * 
	 * @return
	 */
	public List<SelfDrivingTrowelling> queryAllData(Map<String,Object> map);
	
	/**
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String,Object> map);
}