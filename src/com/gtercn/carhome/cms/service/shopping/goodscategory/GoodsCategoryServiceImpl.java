package com.gtercn.carhome.cms.service.shopping.goodscategory;

import java.util.List;

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
	public List<GoodsCategory> selectAllCategory() {
		return categoryDao.selectAllCategory();
	}

}
