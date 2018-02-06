package com.gtercn.carhome.cms.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.cms.entity.Function;


@Repository
public interface FunctionDao {
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Function> getAllFunction();
}
