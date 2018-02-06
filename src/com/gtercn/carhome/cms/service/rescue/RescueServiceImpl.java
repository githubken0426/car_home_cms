package com.gtercn.carhome.cms.service.rescue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.RescueMapper;
import com.gtercn.carhome.cms.entity.Rescue;

@Service(value = "rescueService")
public class RescueServiceImpl implements RescueService {
	@Autowired
	private RescueMapper dao;

	/*@Override
	public List<Rescue> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}*/

	@Override
	public Rescue getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(Rescue o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	/*@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}*/

	@Override
	public int addData(Rescue o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Rescue> getDataByShopId(String shopId) throws Exception {
		return dao.selectByShopId(shopId);
	}
}
