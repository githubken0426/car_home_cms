package com.gtercn.carhome.cms.service.experttop;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.cms.entity.APIUser;
import com.gtercn.carhome.cms.entity.ExpertTop;
import com.gtercn.carhome.cms.entity.write.WriteExpertTop;

public interface ExpertTopService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<ExpertTop> queryAllData(Map<String, Object> map);

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
	public ExpertTop getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int registerUserAndExpert(WriteExpertTop o,APIUser user,boolean isAdd);

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(WriteExpertTop o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public void deleteData(String []ids) throws Exception;
	
    
    /**
     * 通过用户id,达人id获取达人
     * 用户一对一达人
     * @param map
     * @return
     * 2017-2-22 下午01:53:30
     */
    int getExcludeExpert(Map<String,Object> map);
	
    /**
     * 查询所有达人
     * 文章页面用
     * @return
     * 2017-2-24 下午03:25:02
     */
    List<ExpertTop> queryAllExpert(String cityCode);
}
