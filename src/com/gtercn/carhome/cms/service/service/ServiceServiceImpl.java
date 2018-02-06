package com.gtercn.carhome.cms.service.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.ApplicationConfig;
import com.gtercn.carhome.cms.dao.common.ServiceMapper;
import com.gtercn.carhome.cms.entity.ServiceEn;
import com.gtercn.carhome.cms.util.CommonUtil;


@Service(value = "serviceService")
public class ServiceServiceImpl implements ServiceService {
	@Autowired
	private ServiceMapper dao;


	@Override
	public ServiceEn getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<ServiceEn> getDataByShopId(String shopId) throws Exception {
		return dao.selectByShopId(shopId);
	}

	@Override
	public int updateData(ServiceEn o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}


	@Override
	public int addData(ServiceEn o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public void addSomeData(String shopId, String repairService, String cleanService, String maintainService, String tyreService) throws Exception {
		
		ServiceEn serviceEn = new ServiceEn();
		// 修车
		if (StringUtils.equals("1", repairService)) {
			serviceEn.setId(CommonUtil.getUID());
			serviceEn.setShopId(shopId);
			serviceEn.setServiceType(ApplicationConfig.REPAIR_SERVICE);
			serviceEn.setDeleteFlag(0);
			dao.insert(serviceEn);
		}
		// 洗车
		if (StringUtils.equals("1", cleanService)) {
			serviceEn = new ServiceEn();
			serviceEn.setId(CommonUtil.getUID());
			serviceEn.setShopId(shopId);
			serviceEn.setServiceType(ApplicationConfig.CLEAN_SERVICE);
			serviceEn.setDeleteFlag(0);
			dao.insert(serviceEn);
		}
		// 保养
		if (StringUtils.equals("1", maintainService)) {
			serviceEn = new ServiceEn();
			serviceEn.setId(CommonUtil.getUID());
			serviceEn.setShopId(shopId);
			serviceEn.setServiceType(ApplicationConfig.MAINTAIN_SERVICE);
			serviceEn.setDeleteFlag(0);
			dao.insert(serviceEn);
		}
		// 轮胎
		if (StringUtils.equals("1", maintainService)) {
			serviceEn = new ServiceEn();
			serviceEn.setId(CommonUtil.getUID());
			serviceEn.setShopId(shopId);
			serviceEn.setServiceType(ApplicationConfig.TYRE_SERVICE);
			serviceEn.setDeleteFlag(0);
			dao.insert(serviceEn);
		}	
	}
}
