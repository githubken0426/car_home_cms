package com.gtercn.carhome.cms.service.selfDrivingTrowelling;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.SelfDrivingTrowellingMapper;
import com.gtercn.carhome.cms.entity.SelfDrivingExtend;
import com.gtercn.carhome.cms.entity.SelfDrivingTrowelling;

@Service(value = "selfDrivingTrowellingService")
public class SelfDrivingTrowellingServiceImpl implements SelfDrivingTrowellingService {
	@Autowired
	private SelfDrivingTrowellingMapper dao;

	@Override
	public List<SelfDrivingTrowelling> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public SelfDrivingTrowelling getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(SelfDrivingTrowelling o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int addData(SelfDrivingTrowelling o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public void deleteData(String id) throws Exception {
		
		SelfDrivingTrowelling selfDrivingTrowelling = dao.selectByPrimaryKey(id);
		selfDrivingTrowelling.setDeleteFlag(1);
		dao.updateByPrimaryKeySelective(selfDrivingTrowelling);
		
		List<SelfDrivingExtend> list =dao.selectByTrowellingId(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setDeleteFlag(1);
			dao.update(list.get(i));
		}
	}

}
