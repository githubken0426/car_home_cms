package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.write.WriteExpertTop;

public interface ExpertTopMapper {
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
	
    int deleteByPrimaryKey(String id);

    int insert(WriteExpertTop record);

    ExpertTop selectByPrimaryKey(String id);

    int updateByPrimaryKey(WriteExpertTop record);
    
    /**
     * 通过用户id,达人id获取达人
     * 用户一对一达人
     * @param map
     * @return
     * 2017-2-22 下午01:53:30
     */
    int getExcludeExpert(Map<String,Object> map);
    
    /**
     * 查询所有达人
     * 文章页面用
     * @return
     * 2017-2-24 下午03:25:02
     */
    List<ExpertTop> queryAllExpert(String cityCode);
}