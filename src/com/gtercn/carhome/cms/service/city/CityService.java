package com.gtercn.carhome.cms.service.city;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.City;

public interface CityService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<City> queryAllData(Map<String, Object> map);

	/**
	 * 查询所有数据条数
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String, Object> map);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public City getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(City o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(City o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<City> getAllInfo();
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public City getDataByCityCode(String id) throws Exception;
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public City getDataByCityName(String name) throws Exception;
}
