package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Spec;


@Repository
public interface SpecMapper {
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Spec> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	
	/**
     * 查询商规格列表
     * @param map
     * @return
     */
    List<Spec> selectGoodsSpec(Map<String,Object> map);
    
    
    Spec selectByPrimaryKey(String id);
    
    /**
     * 查询某商品的规格列表
     * @param goodsId
     * @return
     */
    List<Spec> selectDetailSpecByGoodsId(String goodsId);
}