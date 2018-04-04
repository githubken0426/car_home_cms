package com.gtercn.carhome.cms.service.shopping.goods;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.shopping.Goods;
import com.gtercn.carhome.cms.entity.shopping.SpecItemGoodsRelation;

public interface GoodsService {
	/**
	 * 主键查询
	 * @param id
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:53:15
	 */
	Goods selectByPrimaryKey(String id);
	
	Goods selectBySkuCode(String skuCode);
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Goods> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	/**
	 * 插入
	 * @param record
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:52:54
	 */
    int insert(Goods record,List<SpecItemGoodsRelation> relationList);
    /**
     * 修改
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int update(Goods record,List<SpecItemGoodsRelation> relationList);
    /**
	 * 批量下架
	 * @param ids
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:52:42
	 */
	int deleteBatch(String []ids);
	
	List<Goods> queryGoodsByCity(String cityCode);
	
	Integer selectGoodsByItem(String goodsId, String cityId,
			String brandId, String itemIds);
}