package com.gtercn.carhome.cms.service.shopping.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.LogisticsMapper;
import com.gtercn.carhome.cms.entity.shopping.Logistics;

@Service(value="logisticsService")
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	private LogisticsMapper dao;
	
	@Override
	public Logistics selectLogisticsByOrder(String orderId) {
		return dao.selectLogisticsByOrder(orderId);
	}

	@Override
	public Logistics selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

}
