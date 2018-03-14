package com.gtercn.carhome.cms.service.shopping.expert;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.APIUserMapper;
import com.gtercn.carhome.cms.dao.shopping.ExpertMapper;
import com.gtercn.carhome.cms.entity.APIUser;
import com.gtercn.carhome.cms.entity.ExpertTop;

@Service(value="expertService")
public class ExpertServiceImpl implements ExpertService {
	@Autowired
	private ExpertMapper expertDao;
	@Autowired
	private APIUserMapper userDao;
	
	@Override
	public List<ExpertTop> queryAllData(Map<String, Object> map) {
		return expertDao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return expertDao.getTotalCount(map);
	}

	@Override
	public int insert(ExpertTop record) {
		return expertDao.insert(record);
	}

	@Override
	public ExpertTop selectByPrimaryKey(String id) {
		return expertDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(ExpertTop record) {
		return expertDao.updateByPrimaryKey(record);
	}

	@Override
	public int registerUserAndExpert(ExpertTop o, APIUser user, boolean isAdd) {
		if(isAdd){
			userDao.insert(user);
		}
		return expertDao.insert(o);
	}

	@Override
	public int deleteBatch(String[] ids) {
		return expertDao.deleteBatch(ids);
	}

	@Override
	public int update(ExpertTop record) {
		return expertDao.update(record);
	}

	@Override
	public int getExcludeExpert(Map<String, Object> map) {
		return expertDao.getExcludeExpert(map);
	}

}
