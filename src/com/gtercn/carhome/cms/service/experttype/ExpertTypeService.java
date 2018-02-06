package com.gtercn.carhome.cms.service.experttype;

import java.util.List;

import com.gtercn.carhome.cms.entity.ExpertType;

public interface ExpertTypeService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<ExpertType> queryAllData();


	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public ExpertType getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int insert(ExpertType o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
}
