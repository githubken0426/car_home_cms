package com.gtercn.carhome.cms.dao.shopping;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Logistics;


@Repository
public interface LogisticsMapper {
	/**
	 * 查询订单物流
	 * @param orderId 订单id
	 * @return
	 * @date 2018年1月15日 下午7:06:31
	 */
	Logistics selectLogisticsByOrder(String orderId);
    
    Logistics selectByPrimaryKey(String id);
}