package com.gtercn.carhome.cms.service.shopping.brand;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.GoodsBrandMapper;
import com.gtercn.carhome.cms.entity.shopping.GoodsBrand;

@Service(value = "goodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
	@Autowired
	private GoodsBrandMapper dao;

	@Override
	public GoodsBrand selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<GoodsBrand> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(GoodsBrand record) {
		return dao.insert(record);
	}

	@Override
	public int update(GoodsBrand record) {
		return dao.update(record);
	}

	@Override
	public int deleteBatch(String[] ids) {
		return dao.deleteBatch(ids);
	}

	@Override
	public List<GoodsBrand> queryDataByCategory(String categoryId) {
		return dao.queryDataByCategory(categoryId);
	}
}
