package com.gtercn.carhome.cms.service.information;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Information;

public interface InformationService {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-3-9 下午02:45:20
	 */
	public List<Information> queryAllData(Map<String,Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-3-9 下午02:45:47
	 */
	public Integer getTotalCount(Map<String,Object> map);
	
	int insert(Information information);
	int updateByPrimaryKey(Information information);
	Information selectByPrimaryKey(String id);
	void deleteByPrimaryKey(String []ids);
	
}
