package com.gtercn.carhome.cms.service.service;

import java.util.List;

import com.gtercn.carhome.cms.entity.ServiceEn;

public interface ServiceService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	//public List<Service> queryAllData(Map<String, Object> map);

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
	public ServiceEn getDataById(String id) throws Exception;
	
	/**
	 * 根据shopId获取
	 * 
	 * @param id
	 * @return
	 */
	public List<ServiceEn> getDataByShopId(String shopId) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(ServiceEn o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(ServiceEn o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 新增数据
	 * 
	 * @param shopId,repairService,cleanService,maintainService,mtyreService
	 * @return
	 */
	public void addSomeData(String shopId, String repairService, String cleanService, String maintainService, String tyreService) throws Exception;
	
}
