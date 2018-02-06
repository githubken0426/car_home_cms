package com.gtercn.carhome.cms.service.group;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.GroupDao;
import com.gtercn.carhome.cms.entity.Group;


@Service
public class GroupServiceIml implements GroupService {
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public Group getGroupById(Integer id) {
		return groupDao.getGroupById(id);
	}

	@Override
	public List<Group> queryAllGroup(Map<String, Object> map) {
		return groupDao.queryAllGroup(map);
	}

	@Override
	public int getTotalCount() {
		return groupDao.getTotalCount();
	}

	@Override
	public int getByName(Map<String, Object> map) {
		return groupDao.getByName(map);
	}

	@Override
	public int insertGroup(Group group)throws Exception{
		return groupDao.insertGroup(group);
	}

	@Override
	public int updateGroup(Group group) {
		return groupDao.updateGroup(group);
	}

	@Override
	public List<Group> getAllGroup() {
		return groupDao.getAllGroup();
	}

	@Override
	public int deleteGroup(String id) {
		return groupDao.deleteGroup(id);
	}

}
