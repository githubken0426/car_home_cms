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
    List<Spec> selectGoodsSpecItems(Map<String,Object> map);
    
    Spec selectByPrimaryKey(String id);
}