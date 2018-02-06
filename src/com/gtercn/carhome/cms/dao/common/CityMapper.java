package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.City;

public interface CityMapper {
    int deleteByPrimaryKey(String id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    /**
	 * 
	 * @return
	 */
	public List<City> queryAllData(Map<String,Object> map);
	
	/**
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String,Object> map);
	
	/**
	 * 获取所有信息
	 * @return
	 */
	public List<City> getAllInfo(Map<String,Object> map);
	
	/**
	 * 通过城市编号获得数据
	 * @param cityCode
	 * @return
	 */
	City getDataByCityCode(String cityCode);
	
	/**
	 * 通过城市名称获得数据
	 * @param cityName
	 * @return
	 */
	City getDataByCityName(String cityName);
}