package com.gtercn.carhome.cms.service.city;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.CityMapper;
import com.gtercn.carhome.cms.entity.City;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper dao;

	@Override
	public List<City> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public City getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(City o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int addData(City o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<City> getAllInfo(Map<String, Object> map) {
		return dao.getAllInfo(map);
	}
	
	@Override
	public City getDataByCityCode(String cityCode) throws Exception {
		return dao.getDataByCityCode(cityCode);
	}
	
	@Override
	public City getDataByCityName(String cityName) throws Exception {
		return dao.getDataByCityName(cityName);
	}
}
