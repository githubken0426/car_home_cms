package com.gtercn.carhome.cms.service.promotion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.PromotionMapper;
import com.gtercn.carhome.cms.entity.Promotion;

@Service(value="promotionService")
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionMapper dao;
	
	@Override
	public void deleteBatch(String []ids) {
		for (String id : ids) {
			dao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Integer getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(Promotion promotion) {
		return dao.insert(promotion);
	}

	@Override
	public List<Promotion> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public Promotion selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Promotion promotion) {
		return dao.updateByPrimaryKey(promotion);
	}

}
