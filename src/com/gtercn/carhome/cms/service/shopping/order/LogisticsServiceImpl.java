package com.gtercn.carhome.cms.service.shopping.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.dao.shopping.LogisticsMapper;
import com.gtercn.carhome.cms.dao.shopping.OrderMapper;
import com.gtercn.carhome.cms.entity.shopping.Address;
import com.gtercn.carhome.cms.entity.shopping.Logistics;
import com.gtercn.carhome.cms.entity.shopping.LogisticsDetail;
import com.gtercn.carhome.cms.util.CommonUtil;

@Service(value="logisticsService")
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	private LogisticsMapper dao;
	@Autowired
	private OrderMapper orderDao;
	
	@Override
	public Logistics selectLogisticsByOrder(String orderId) {
		return dao.selectLogisticsByOrder(orderId);
	}

	@Override
	public Logistics selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int delivery(Logistics logistics,String addressId) {
		Address address=dao.selectAddressByPrimaryKey(addressId);
		if(address!=null) {
			logistics.setRealname(address.getName());
			logistics.setTelphone(address.getPhone());
			logistics.setPostalCode(address.getPostalCode());
			String detail = address.getProvince() + address.getCity() + address.getAddress();
			logistics.setAddress(detail);
		}
		String logisticsId=CommonUtil.getUID();
		logistics.setId(logisticsId);
		//更新订单
		orderDao.updateOrderLogistics(logistics.getOrderId(), logistics.getId());
		//插入物流信息
		dao.add(logistics);
		LogisticsDetail detail=new LogisticsDetail();
		detail.setId(CommonUtil.getUID());
		detail.setLogisticsId(logisticsId);
		detail.setDescription(ApplicationConfig.LOGISTICS_INFO);
		dao.addDetail(detail);
		return 1;
	}

}
