package com.gtercn.carhome.cms.dao.shopping;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Address;
import com.gtercn.carhome.cms.entity.shopping.Logistics;
import com.gtercn.carhome.cms.entity.shopping.LogisticsDetail;


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
    
    int add(Logistics logistics);
    int addDetail(LogisticsDetail detail);
    
    Address selectAddressByPrimaryKey(String addressId);
}