package com.gtercn.carhome.cms.dao.common;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.HomeCarousel;
import com.gtercn.carhome.cms.entity.write.WriteQuestionArticle;

public interface HomeCarouselMapper {
	/**
	 * 查询所有文章数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<HomeCarousel> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有问题数据
	 * @param map
	 * @return
	 * 2017-3-2 下午01:26:13
	 */
	List<HomeCarousel> queryAllQuestionData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	
    int deleteByPrimaryKey(String id);

    int insert(HomeCarousel record);

    HomeCarousel selectByPrimaryKey(String id);
    /**
     * 通过id查询问题
     * @param id
     * @return
     * 2017-3-3 下午01:29:37
     */
    HomeCarousel selectQuestionByPrimaryKey(String id);
    /**
     * 修改文章
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int updateByPrimaryKey(HomeCarousel record);
    
    /**
     * 修改问题
     * @param record
     * @return
     * 2017-3-6 上午11:34:12
     */
    int updateQuestionById(WriteQuestionArticle record);
}