package com.gtercn.carhome.cms.service.user;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.User;



public interface UserService {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public User userLogin(User user) throws Exception;
	
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> queryAllUser(Map<String,Object> map);
	
	/**
	 * 查询所有数据条数
	 * @return
	 */
	public int getTotalCount(Map<String,Object> map);
	
	/**
	 * 根据用户名获取
	 * @param userName
	 * @return
	 */
	public int getUserByName(Map<String,Object> map)throws Exception;
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(String id)throws Exception;
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user)throws Exception;
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user)throws Exception;
	
	/**
	 * 批量删除用户
	 * @param userId
	 * @return
	 */
	public void deleteUser(String []userIds)throws Exception;
	
	/**
	 * 批量更新状态
	 * @param status
	 * @return
	 */
	public void updateUserStatus(String ids[],String status)throws Exception;
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
	
	
}
