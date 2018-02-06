package com.gtercn.carhome.cms.service.shopping.advertisement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gtercn.carhome.cms.entity.shopping.Advertisement;

public interface AdvertisementService {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Advertisement> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	
	int deleteBatch(@Param("ids")String []ids);

    int insert(Advertisement record);
    /**
     * 修改
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int update(Advertisement record);
    
    Advertisement selectByPrimaryKey(String id);
}