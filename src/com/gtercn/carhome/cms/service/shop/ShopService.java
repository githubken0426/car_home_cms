package com.gtercn.carhome.cms.service.shop;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.Shop;

public interface ShopService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Shop> queryAllData(Map<String, Object> map);

	/**
	 * 查询所有数据条数
	 * 
	 * @return
	 */
	public int getTotalCount(Map<String, Object> map);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public Shop getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(Shop o) throws Exception;
	
	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addSomeData(Shop o, Map<String, Object> map) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Shop o) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateSomeData(Shop o, Map<String, Object> map) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 删除数据
	 * 
	 * @param o
	 * @return
	 */
	public void deleteSomeData(String id) throws Exception;
	
	/**
	 * excel导出
	 * @param out
	 * @throws IOException
	 */
	public void exprotData(OutputStream out)throws IOException ;
	
	/**
	 * 将excel导入数据库
	 * @param list
	 */
	public void addImport(List<Shop> list) throws Exception;
	
	/**
	 * 根据名称查询店铺
	 * @param map
	 * @return
	 * 2017-3-15 上午11:31:37
	 */
	Shop queryShopByName(Map<String,Object> map);
}
