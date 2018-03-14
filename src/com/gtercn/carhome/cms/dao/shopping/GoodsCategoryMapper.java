package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;

@Repository
public interface GoodsCategoryMapper {
    
    GoodsCategory selectByPrimaryKey(String id);
    
    /**
     * 查询所有商品分类
     * @return
     * @throws 
     * @date 2017年9月22日 上午9:45:38
     */
    List<GoodsCategory> selectAllCategory();
}