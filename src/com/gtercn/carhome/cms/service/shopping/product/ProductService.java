package com.gtercn.carhome.cms.service.shopping.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.shopping.Product;

@Repository
public interface ProductService {
	/**
	 * 主键查询
	 * @param id
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:53:15
	 */
	Product selectByPrimaryKey(String id);
	/**
	 * 查询所有数据
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	List<Product> queryAllData(Map<String, Object> map);
	/**
	 * 查询所有数据条数
	 * @param map
	 * @return
	 * 2017-2-21 下午02:48:14
	 */
	public int getTotalCount(Map<String, Object> map);
	/**
	 * 插入
	 * @param record
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:52:54
	 */
    int insert(Product record);
    /**
     * 修改
     * @param record
     * @return
     * 2017-3-1 上午11:14:59
     */
    int update(Product record);
    /**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws 
	 * @date 2018年2月6日 下午1:52:42
	 */
	int deleteBatch(@Param("ids")String []ids);
}