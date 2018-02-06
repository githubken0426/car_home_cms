package com.gtercn.carhome.cms.service.shopping.advertisement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.AdvertisementMapper;
import com.gtercn.carhome.cms.entity.shopping.Advertisement;

@Service(value = "advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
	@Autowired
	private AdvertisementMapper dao;
	
	@Override
	public List<Advertisement> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(Advertisement record) {
		return dao.insert(record);
	}

	@Override
	public int update(Advertisement record) {
		return dao.update(record);
	}

	@Override
	public Advertisement selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBatch(String[] ids) {
		return dao.deleteBatch(ids);
	}
}
