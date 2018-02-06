package com.gtercn.carhome.cms.service.replay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.dao.common.ReplyMapper;
import com.gtercn.carhome.cms.entity.Reply;

@Service(value = "replyService")
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper dao;

	@Override
	public List<Reply> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public Reply getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return dao.getTotalCount(map);
	}

	@Override
	public int updateByPrimaryKey(Map<String,Object> map){
		return dao.updateByPrimaryKey(map);
	}

	@Override
	public List<Reply> selectByQuestionId(Map<String, Object> map) {
		return dao.selectByQuestionId(map);
	}

}
