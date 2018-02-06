package com.gtercn.carhome.cms.service.promotion;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Promotion;

public interface PromotionService {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-3-9 下午02:45:20
	 */
	public List<Promotion> queryAllData(Map<String,Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-3-9 下午02:45:47
	 */
	public Integer getTotalCount(Map<String,Object> map);
	
	int insert(Promotion promotion);
	int updateByPrimaryKey(Promotion promotion);
	Promotion selectByPrimaryKey(String id);
	
	void deleteBatch(String []ids);
}
