package com.gtercn.carhome.cms.service.information;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.FavorMapper;
import com.gtercn.carhome.cms.dao.common.InformationMapper;
import com.gtercn.carhome.cms.entity.Information;
@Service(value="informationServic")
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationMapper dao;
	@Autowired
	private FavorMapper favorDao;
	

	@Override
	public Integer getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(Information information) {
		return dao.insert(information);
	}

	@Override
	public List<Information> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public Information selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Information information) {
		if(1==information.getDeleteFlag())
			favorDao.deleteByCondition(information.getId(),"4");
		return dao.updateByPrimaryKey(information);
	}

	@Override
	public void deleteByPrimaryKey(String[] ids) {
		for (String id : ids) {
			favorDao.deleteByCondition(id,"4");
			dao.deleteByPrimaryKey(id);
		}
	}

}
