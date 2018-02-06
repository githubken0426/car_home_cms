package com.gtercn.carhome.cms.service.questionArcitlce;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.QuestionArticleMapper;
import com.gtercn.carhome.cms.entity.QuestionArticle;
import com.gtercn.carhome.cms.entity.write.WriteQuestionArticle;

@Service(value = "questionArticleService")
public class QuestionArticleServiceImpl implements QuestionArticleService {
	@Autowired
	private QuestionArticleMapper dao;

	@Override
	public List<QuestionArticle> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public QuestionArticle selectByPrimaryKey(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int insert(WriteQuestionArticle o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public void deleteData(String ids[]) throws Exception {
		for (String id : ids) {
			dao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public int updateByPrimaryKey(WriteQuestionArticle record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<QuestionArticle> queryAllQuestionData(Map<String, Object> map) {
		return dao.queryAllQuestionData(map);
	}

	@Override
	public QuestionArticle selectQuestionByPrimaryKey(String id) {
		return dao.selectQuestionByPrimaryKey(id);
	}

	@Override
	public int updateQuestionById(WriteQuestionArticle record) {
		return dao.updateQuestionById(record);
	}

}
