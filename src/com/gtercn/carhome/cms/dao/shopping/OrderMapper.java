package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.shopping.Order;


public interface OrderMapper {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Order> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
    
	Order selectByPrimaryKey(String id);
	
	List<ExpertTop> queryAllExpert(String cityCode);
	
	int updateOrderLogistics(@Param("orderId")String orderId,@Param("logisticsId")String logisticsId);
}