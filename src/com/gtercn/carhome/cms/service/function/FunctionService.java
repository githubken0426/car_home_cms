package com.gtercn.carhome.cms.service.function;

import java.util.List;

import com.gtercn.carhome.cms.entity.Function;


public interface FunctionService {
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Function> getAllFunction() throws Exception;
}
