package com.gtercn.carhome.cms.dao.shopping;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.SpecItemGoodsRelation;

@Repository
public interface SpecItemGoodsRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpecItemGoodsRelation record);

    int insertSelective(SpecItemGoodsRelation record);

    SpecItemGoodsRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpecItemGoodsRelation record);

    int updateByPrimaryKey(SpecItemGoodsRelation record);
}