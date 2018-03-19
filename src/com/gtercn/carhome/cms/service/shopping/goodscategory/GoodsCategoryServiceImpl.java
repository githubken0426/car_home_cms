package com.gtercn.carhome.cms.service.shopping.goodscategory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.GoodsCategoryMapper;
import com.gtercn.carhome.cms.entity.shopping.GoodsCategory;

@Service(value = "categoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	@Autowired
	private GoodsCategoryMapper categoryDao;
	
	@Override
	public GoodsCategory selectByPrimaryKey(String id) {
		return categoryDao.selectByPrimaryKey(id);
	}

	@Override
	public List<GoodsCategory> queryAllData(Map<String, Object> map) {
		return categoryDao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return categoryDao.getTotalCount(map);
	}

	@Override
	public int insert(GoodsCategory category) {
		return categoryDao.insert(category);
	}

	@Override
	public int update(GoodsCategory category) {
		return categoryDao.update(category);
	}

	@Override
	public int deleteBatch(String []categoryIds) {
		return categoryDao.deleteBatch(categoryIds);
	}

	@Override
	public List<GoodsCategory> selectAllCategory() {
		return categoryDao.selectAllCategory();
	}
}
