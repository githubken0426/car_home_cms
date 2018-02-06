package com.gtercn.carhome.cms.service.group;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Group;


public interface GroupService {
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Group getGroupById(Integer id);
	
	/**
	 * 获取所有用户组
	 * @param map
	 * @return
	 */
	public List<Group> queryAllGroup(Map<String,Object> map);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Group> getAllGroup();
	
	/**
	 * 查询所有数据条数
	 * @return
	 */
	public int getTotalCount();
	
	/**
	 * 根据组名获取
	 * @param userName
	 * @return
	 */
	public int getByName(Map<String,Object> map);
	
	/**
	 * 插入用户组
	 * @param group
	 * @return
	 */
	public int insertGroup(Group group) throws Exception;
	
	/**
	 * 修改
	 * @param group
	 * @return
	 */
	public int updateGroup(Group group);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteGroup(String id);
}
