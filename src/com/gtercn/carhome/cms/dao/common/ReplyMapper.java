package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Reply;

public interface ReplyMapper {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Reply> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	
    int updateByPrimaryKey(Map<String,Object> map);

    Reply selectByPrimaryKey(String id);
    /**
     * 查询问题回复
     * @param quesitonId
     * @return
     * 2017-3-8 下午03:24:18
     */
    List<Reply> selectByQuestionId(Map<String, Object> map);
}