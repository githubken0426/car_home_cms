package com.gtercn.carhome.cms.service.version;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.VersionMapper;
import com.gtercn.carhome.cms.entity.Version;

@Service(value = "versionService")
public class VersionServiceImpl implements VersionService {
	@Autowired
	private VersionMapper dao;

	@Override
	public List<Version> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public Version getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(Version o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int addData(Version o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

}
