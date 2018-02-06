package com.gtercn.carhome.cms.service.questionArcitlce;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.QuestionArticle;
import com.gtercn.carhome.cms.entity.write.WriteQuestionArticle;

public interface QuestionArticleService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<QuestionArticle> queryAllData(Map<String, Object> map);
	
	/**
	 * 查询所有问题数据
	 * @param map
	 * @return
	 * 2017-3-2 下午01:26:13
	 */
	List<QuestionArticle> queryAllQuestionData(Map<String, Object> map);

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
	public QuestionArticle selectByPrimaryKey(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int insert(WriteQuestionArticle o) throws Exception;

	/**
     * 修改文章
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int updateByPrimaryKey(WriteQuestionArticle record);
    
    /**
     * 修改问题
     * @param record
     * @return
     * 2017-3-6 上午11:34:12
     */
    int updateQuestionById(WriteQuestionArticle record);

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public void deleteData(String ids[]) throws Exception;
	
	QuestionArticle selectQuestionByPrimaryKey(String id);
}
