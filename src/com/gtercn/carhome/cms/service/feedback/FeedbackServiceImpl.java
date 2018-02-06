package com.gtercn.carhome.cms.service.feedback;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.FeedbackMapper;
import com.gtercn.carhome.cms.entity.Feedback;

@Service(value = "feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper dao;

	@Override
	public List<Feedback> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public Feedback getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(Feedback o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int addData(Feedback o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		Feedback feedback = dao.selectByPrimaryKey(id);
		feedback.setDeleteFlag(1);
		return dao.updateByPrimaryKeySelective(feedback);
	}

}
