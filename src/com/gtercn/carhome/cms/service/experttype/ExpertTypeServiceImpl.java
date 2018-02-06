package com.gtercn.carhome.cms.service.experttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.ExpertTypeMapper;
import com.gtercn.carhome.cms.entity.ExpertType;

@Service(value = "expertTypeService")
public class ExpertTypeServiceImpl implements ExpertTypeService {
	@Autowired
	private ExpertTypeMapper dao;

	@Override
	public List<ExpertType> queryAllData() {
		return dao.queryAllData();
	}

	@Override
	public ExpertType getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int insert(ExpertType o) throws Exception {
		return dao.insert(o);
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

}
