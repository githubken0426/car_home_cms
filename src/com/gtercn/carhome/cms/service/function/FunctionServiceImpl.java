package com.gtercn.carhome.cms.service.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.FunctionDao;
import com.gtercn.carhome.cms.entity.Function;


@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionDao functionDao;

	@Override
	public List<Function> getAllFunction() throws Exception{
		return functionDao.getAllFunction();
	}

}
