package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.User;

@Repository
public interface UserDao {
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public User userLogin(User user);
	
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
	public int getUserByName(Map<String,Object> map);
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(String id);
	
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public int deleteUser(String userId);
	
	/**
	 * 保存用户的登陆信息
	 * @param user
	 * @return
	 */
	public int saveLoginInfo(User user);
	
	/**
	 * 获取用户登录的次数
	 * @param user
	 * @return
	 */
	public int getLoginNum(User user);
	
	/**
	 * 批量更新状态
	 * @param map
	 * @return
	 */
	public int updateUserStatus(Map<String,Object> map);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
	
}
