package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.APIUser;

@Repository
public interface APIUserMapper {
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
	 * 新增
	 * @param user
	 * @return
	 */
	public int insert(APIUser user);
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
    /**
     * 查询用户是否存在
     * @param map
     * @return
     * 2017-2-22 上午11:30:22
     */
    public APIUser getUserByLoginPhone(Map<String,Object> map);
	
	public APIUser getUserById(String userId);
	
   
}