package com.gtercn.carhome.cms.service.shopping.spec;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.shopping.Spec;


public interface SpecService {

    /**
     * 查询商规格列表
     * @param map
     * @return
     */
    List<Spec> selectGoodsSpecItems(Map<String,Object> map);

	Spec selectByPrimaryKey(String id);
	
	 /**
     * 查询某商品的规格列表
     * @param goodsId
     * @return
     */
    List<Spec> selectDetailSpecByGoodsId(String goodsId);
}
