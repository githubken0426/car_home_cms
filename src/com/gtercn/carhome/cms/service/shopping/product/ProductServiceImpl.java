package com.gtercn.carhome.cms.service.shopping.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.shopping.ProductMapper;
import com.gtercn.carhome.cms.entity.shopping.Product;

@Service(value="productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper dao;
	
	@Override
	public Product selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(Product record) {
		return dao.insert(record);
	}

	@Override
	public int update(Product record) {
		return dao.update(record);
	}

	@Override
	public int deleteBatch(String[] ids) {
		return dao.deleteBatch(ids);
	}

}
