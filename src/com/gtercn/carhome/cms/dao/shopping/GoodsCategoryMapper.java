package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;

@Repository
public interface GoodsCategoryMapper {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<GoodsCategory> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	/**
	 * 主键查询
	 * @param id
	 * @return
	 * @throws 
	 * @date 2018年3月19日 上午8:51:28
	 */
    GoodsCategory selectByPrimaryKey(String id);
    /**
     * 查询所有商品分类
     * @return
     * @throws 
     * @date 2017年9月22日 上午9:45:38
     */
    List<GoodsCategory> selectAllCategory();
    
    int insert(GoodsCategory category);
    int update(GoodsCategory category);
    int deleteBatch(String []categoryIds);
}