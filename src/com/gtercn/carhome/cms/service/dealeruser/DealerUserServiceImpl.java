package com.gtercn.carhome.cms.service.dealeruser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.cms.util.ComputerIp;
import com.gtercn.carhome.cms.dao.common.DealerUserDao;
import com.gtercn.carhome.cms.entity.DealerUser;


@Service(value = "dealerUserService")
public class DealerUserServiceImpl implements DealerUserService {
	@Autowired
	private DealerUserDao userDao;

	/**
	 * 用户登录后保存登陆信息
	 */
	@SuppressWarnings({ "finally" })
	@Override
	public DealerUser userLogin(DealerUser user) throws Exception {
		DealerUser loginUser = userDao.userLogin(user);
		try {
			String loginIp = ComputerIp.getComputerIp();
			loginUser.setLoginTime(new Date());
			loginUser.setLoginIp(loginIp);
			int loginNum = userDao.getLoginNum(loginUser);
			loginUser.setLoginNum(loginNum + 1);
			userDao.saveLoginInfo(loginUser);// 保存登陆信息
		} catch (Exception e) {
			throw new RuntimeException();
		}finally{
			//即使登陆信息保存失败，登陆也正常
			return loginUser;
		}
	}

	@Override
	public List<DealerUser> queryAllUser(Map<String, Object> map) {
		return userDao.queryAllUser(map);
	}

	@Override
	public int getUserByName(Map<String, Object> map) throws Exception {
		return userDao.getUserByName(map);
	}

	@Override
	public int addUser(DealerUser user) throws Exception {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(DealerUser user) throws Exception {
		return userDao.updateUser(user);
	}

	/*@Override
	public int getTotalCount(Map<String,Object> map) {
		return userDao.getTotalCount(map);
	}*/

	@Override
	public DealerUser getUserById(String id) throws Exception {
		return userDao.getUserById(id);
	}

	@Override
	public void deleteUser(String[] userIds) throws Exception {
		for (String id : userIds) {
			userDao.deleteUser(id);
		}
	}

	@Override
	public void updateUserStatus(String[] ids, String status) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		for (String id : ids) {
			map.put("id", id);
			userDao.updateUserStatus(map);
		}
	}

	@Override
	public List<DealerUser> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public List<DealerUser> queryAllData(Map<String, Object> map) {
		return userDao.queryAllData(map);
	}

	@Override
	public DealerUser getDataById(String id) throws Exception {
		return userDao.getUserById(id);
	}

	@Override
	public int updateData(DealerUser o) throws Exception {
		return userDao.updateUser(o);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		return userDao.getTotalCount(map);
	}

	@Override
	public int addData(DealerUser o) throws Exception {
		return userDao.addUser(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("id", id);
		return userDao.updateUserStatus(map);
	}

}
