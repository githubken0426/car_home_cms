package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Spec;


@Repository
public interface SpecMapper {
	/**
     * 查询商规格列表
     * @param map
     * @return
     */
    List<Spec> selectGoodsSpec(Map<String,Object> map);
    
    @Deprecated
    List<Spec> selectAllSpec();
    
    Spec selectByPrimaryKey(String id);
    
    /**
     * 查询某商品的规格列表
     * @param goodsId
     * @return
     */
    List<Spec> selectDetailSpecByGoodsId(String goodsId);
}