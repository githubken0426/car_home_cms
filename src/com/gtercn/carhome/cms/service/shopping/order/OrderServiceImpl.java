package com.gtercn.carhome.cms.service.shopping.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.OrderMapper;
import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.shopping.Order;

@Service(value="orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderDao;
	
	@Override
	public List<Order> queryAllData(Map<String, Object> map) {
		return orderDao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return orderDao.getTotalCount(map);
	}

	@Override
	public Order selectByPrimaryKey(String id) {
		return orderDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ExpertTop> queryAllExpert(String cityCode) {
		return orderDao.queryAllExpert(cityCode);
	}
}
