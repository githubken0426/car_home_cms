package com.gtercn.carhome.cms.service.shopping.spec;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtercn.carhome.cms.dao.shopping.SpecMapper;
import com.gtercn.carhome.cms.entity.shopping.Spec;


@Transactional
@Service(value = "specService")
public class SpecServiceImpl implements SpecService {
	@Autowired
	private SpecMapper dao;


	@Override
	public List<Spec> selectGoodsSpecItems(Map<String, Object> map) {
		return dao.selectGoodsSpecItems(map);
	}
	@Override
	public Spec selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}
}
