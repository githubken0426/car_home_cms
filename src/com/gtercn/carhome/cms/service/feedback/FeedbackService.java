package com.gtercn.carhome.cms.service.feedback;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Feedback;

public interface FeedbackService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Feedback> queryAllData(Map<String, Object> map);

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
	public Feedback getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(Feedback o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Feedback o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
}
