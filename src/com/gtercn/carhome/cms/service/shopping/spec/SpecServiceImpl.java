package com.gtercn.carhome.cms.service.shopping.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtercn.carhome.cms.dao.shopping.SpecItemGoodsRelationMapper;
import com.gtercn.carhome.cms.dao.shopping.SpecItemMapper;
import com.gtercn.carhome.cms.dao.shopping.SpecMapper;
import com.gtercn.carhome.cms.entity.shopping.Spec;
import com.gtercn.carhome.cms.entity.shopping.SpecItem;
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
	public List<Spec> selectGoodsSpec(Map<String, Object> map) {
		return dao.selectGoodsSpec(map);
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
	
	@Override
	public List<Spec> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}
	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}
	@Override
	public int insert(Spec spec,String items[]) {
		List<SpecItem> itemList=new ArrayList<SpecItem>();
		String specId=spec.getId();
		for (String str : items) {
			SpecItem it=new SpecItem();
			it.setItem(str);
			it.setSpecId(specId);
			itemList.add(it);
		}
		itemDao.insert(itemList);
		return dao.insert(spec);
	}
	@Override
	public int update(Spec spec,String items[]) {
		List<SpecItem> itemList=new ArrayList<SpecItem>();
		String specId=spec.getId();
		itemDao.deleteBySpec(specId);
		for (String str : items) {
			SpecItem it=new SpecItem();
			it.setItem(str);
			it.setSpecId(specId);
			itemList.add(it);
		}
		itemDao.insert(itemList);
		return dao.update(spec);
	}
	@Override
	public int delteBatch(String[] ids) {
		return dao.delteBatch(ids);
	}
}
