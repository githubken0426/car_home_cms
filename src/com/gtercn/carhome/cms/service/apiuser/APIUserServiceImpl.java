package com.gtercn.carhome.cms.service.apiuser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.APIUserMapper;
import com.gtercn.carhome.cms.entity.APIUser;

@Service(value="apiUserService")
public class APIUserServiceImpl implements APIUserService{
	@Autowired
	private APIUserMapper apiUserDao;
	
	@Override
	public int deleteByPrimaryKey(String userId) {
		return apiUserDao.deleteByPrimaryKey(userId);
	}

	@Override
	public APIUser getUserById(String userId) {
		return apiUserDao.getUserById(userId);
	}

	@Override
	public int update(APIUser user) {
		return apiUserDao.update(user);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return apiUserDao.getTotalCount(map);
	}

	@Override
	public List<APIUser> queryAllData(Map<String, Object> map) {
		return apiUserDao.queryAllData(map);
	}

	@Override
	public APIUser getUserByLoginPhone(Map<String, Object> map) {
		return apiUserDao.getUserByLoginPhone(map);
	}

	@Override
	public void insert(APIUser user) {
		apiUserDao.insert(user);
	}
}
