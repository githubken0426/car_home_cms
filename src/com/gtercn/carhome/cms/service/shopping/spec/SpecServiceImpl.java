package com.gtercn.carhome.cms.service.shopping.spec;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtercn.carhome.cms.dao.shopping.SpecItemGoodsRelationMapper;
import com.gtercn.carhome.cms.dao.shopping.SpecItemMapper;
import com.gtercn.carhome.cms.dao.shopping.SpecMapper;
import com.gtercn.carhome.cms.entity.shopping.Spec;
import com.gtercn.carhome.cms.entity.shopping.SpecItemGoodsRelation;


@Transactional
@Service(value = "specService")
public class SpecServiceImpl implements SpecService {
	@Autowired
	private SpecMapper dao;
	@Autowired
	private SpecItemGoodsRelationMapper relationDao;
	@Autowired
	private SpecItemMapper itemDao;

	@Override
	public List<Spec> selectGoodsSpecItems(Map<String, Object> map) {
		return dao.selectGoodsSpecItems(map);
	}
	@Override
	public Spec selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}
	@Override
	public List<Spec> selectDetailSpecByGoodsId(String goodsId) {
		return dao.selectDetailSpecByGoodsId(goodsId);
	}
	@Override
	public List<SpecItemGoodsRelation> selectByGoodsId(String goodsId) {
		return relationDao.selectByGoodsId(goodsId);
	}
	@Override
	public List<String> selectConcatSpecItems(List<String> specItemIds) {
		return itemDao.selectConcatSpecItems(specItemIds);
	}
}
