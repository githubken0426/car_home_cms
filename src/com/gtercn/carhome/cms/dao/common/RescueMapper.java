package com.gtercn.carhome.cms.dao.common;

import java.util.List;

import com.gtercn.carhome.cms.entity.Rescue;

public interface RescueMapper {
    int deleteByPrimaryKey(String id);

    int insert(Rescue record);

    int insertSelective(Rescue record);

    Rescue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Rescue record);

    int updateByPrimaryKey(Rescue record);
    
    List<Rescue> selectByShopId(String shopId);
}