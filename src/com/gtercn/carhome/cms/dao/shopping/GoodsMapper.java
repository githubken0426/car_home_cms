package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Goods;

@Repository
public interface GoodsMapper {
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
	 * 根据城市、品牌查询
	 * @param cityid
	 * @param categoryId
	 * @param brandId
	 * @return
	 */
	List<Goods> selectGoodsByCity(@Param("cityId")String cityid,@Param("brandId")String brandId);
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
    int insert(Goods record);
    /**
     * 修改
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int update(Goods record);
    /**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:52:42
	 */
	int deleteBatch(@Param("ids")String []ids);
	
	/**
	 * 根据规格显示不同商品
	 * @param goodsId
	 * @param cityId
	 * @param brandId
	 * @param itemList
	 * @return
	 * @throws 
	 * @date 2018年4月3日 下午8:17:06
	 */
	Integer selectGoodsByItem(@Param("goodsId") String goodsId, @Param("cityId") String cityId,
			@Param("brandId") String brandId, @Param("itemIds") String itemIds);
}