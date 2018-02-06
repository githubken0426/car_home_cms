package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.Promotion;

@Repository
public interface PromotionMapper {
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
	int deleteByPrimaryKey(String id);
}
