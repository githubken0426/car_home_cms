package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.APIUser;
import com.gtercn.carhome.cms.entity.ExpertTop;

public interface ExpertMapper {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<ExpertTop> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:20
	 */
	public int getTotalCount(Map<String, Object> map);
	
    int insert(ExpertTop record);
    int update(ExpertTop record);
    int deleteBatch(String[] ids);
    ExpertTop selectByPrimaryKey(String id);
    
    int getExcludeExpert(Map<String,Object> map);
	public int registerUserAndExpert(ExpertTop o, APIUser user,
			boolean isAdd);
}