package com.gtercn.carhome.cms.service.shopping.goodscategory;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;

public interface GoodsCategoryService {
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
	
	GoodsCategory selectByPrimaryKey(String id);
    /**
     * 查询所有商品
     * @return
     * @throws 
     * @date 2017年9月22日 上午9:45:38
     */
    List<GoodsCategory> selectAllCategory();
    
    int inert(GoodsCategory category);
    int update(GoodsCategory category);
    int delete(String categoryId);
}
