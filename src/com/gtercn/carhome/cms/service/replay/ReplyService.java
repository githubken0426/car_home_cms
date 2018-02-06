package com.gtercn.carhome.cms.service.replay;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Reply;

public interface ReplyService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Reply> queryAllData(Map<String, Object> map);

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
	public Reply getDataById(String id) throws Exception;

	
	/**
	 * 修改状态
	 * 
	 * @param o
	 * @return
	 */
	public int updateByPrimaryKey(Map<String,Object> map) ;
	
	 /**
     * 查询问题回复
     * @param quesitonId
     * @return
     * 2017-3-8 下午03:24:18
     */
    List<Reply> selectByQuestionId(Map<String, Object> map);
	
}
