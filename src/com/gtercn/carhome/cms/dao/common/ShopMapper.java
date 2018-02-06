package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(String id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    
    /**
	 * 
	 * @return
	 */
	public List<Shop> queryAllData(Map<String,Object> map);
	
	/**
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String,Object> map);
	
	//删除所有信息
	public void deleteAllInfo();
	
	/**
	 * 查询所有店铺信息
	 * @param id
	 * @return
	 */
	List<Shop> selectAllInfo();
	/**
	 * 根据名称查询店铺
	 * @param map
	 * @return
	 * 2017-3-15 上午11:31:37
	 */
	Shop queryShopByName(Map<String,Object> map);
}