package com.gtercn.carhome.cms.service.apiuser;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.APIUser;

public interface APIUserService {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<APIUser> queryAllData(Map<String, Object> map);
	
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:20
	 */
	public int getTotalCount(Map<String, Object> map);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public void insert(APIUser user);
	/**
	 * 修改
	 * @param user
	 * @return
	 * 2017-2-22 上午11:30:04
	 */
	public int update(APIUser user);
	/**
	 * 删除
	 * @param userId
	 * @return
	 * 2017-2-22 上午11:30:12
	 */
    int deleteByPrimaryKey(String userId);
    
	
	public APIUser getUserById(String userId);
	
	/**
     * 查询用户是否存在
     * @param map
     * @return
     * 2017-2-22 上午11:30:22
     */
    public APIUser getUserByLoginPhone(Map<String,Object> map);
}
