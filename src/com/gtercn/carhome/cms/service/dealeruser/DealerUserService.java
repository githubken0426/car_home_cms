package com.gtercn.carhome.cms.service.dealeruser;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.DealerUser;



public interface DealerUserService {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public DealerUser userLogin(DealerUser user) throws Exception;
	
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<DealerUser> queryAllUser(Map<String,Object> map);
	
	/**
	 * 查询所有数据条数
	 * @return
	 *//*
	public int getTotalCount(Map<String,Object> map);*/
	
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
	public DealerUser getUserById(String id)throws Exception;
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public int addUser(DealerUser user)throws Exception;
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int updateUser(DealerUser user)throws Exception;
	
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
	public List<DealerUser> getAllUser();
	
	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<DealerUser> queryAllData(Map<String, Object> map);

	/**
	 * 查询所有数据条数
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String, Object> map);
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public DealerUser getDataById(String id) throws Exception;
	
	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(DealerUser o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(DealerUser o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
}
