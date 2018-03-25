package com.gtercn.carhome.cms.dao.shopping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.SpecItem;


@Repository
public interface SpecItemMapper {

    SpecItem selectByPrimaryKey(String id);

    /**
     * 查询商规格列表
     * @param map
     * @return
     */
    List<SpecItem> selectGoodsSpecItems(Map<String,Object> map);
    /**
     * 根据specItemIds,拼接规格名、值
     * @param specItemIds
     * @return
     */
    List<String> selectConcatSpecItems(List<String> specItemIds);
    
    int insert(@Param("items")List<SpecItem> items);
    int deleteBySpec(String specId);
}