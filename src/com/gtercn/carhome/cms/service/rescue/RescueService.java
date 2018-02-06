package com.gtercn.carhome.cms.service.rescue;

import java.util.List;

import com.gtercn.carhome.cms.entity.Rescue;

public interface RescueService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	//public List<Rescue> queryAllData(Map<String, Object> map);

	/**
	 * 查询所有数据条数
	 * 
	 * @return
	 */
	//public int getTotalCount(Map<String, Object> map);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public Rescue getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(Rescue o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Rescue o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 根据shopId获取
	 * 
	 * @param id
	 * @return
	 */
	public List<Rescue> getDataByShopId(String shopId) throws Exception;
}
