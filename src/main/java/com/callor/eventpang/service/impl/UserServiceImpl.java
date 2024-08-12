package com.callor.eventpang.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.eventpang.models.UserVO;
import com.callor.eventpang.dao.UserDao;
import com.callor.eventpang.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	protected final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public int join(UserVO userVO) {
		List<UserVO> users = userDao.selectAll();
		int ret = userDao.insert(userVO);
		return ret;
	}

	@Override
	public UserVO findById(String id) {
		if (id==null) {
			return null;
		}
		UserVO userVO = userDao.findById(id);
		return userVO;
	}

	@Override
	public int deleteById(String id) {
		int ret = userDao.deleteById(id);
		return ret;
	}

	@Override
	public int modify(UserVO userVO) {
		int ret = userDao.update(userVO);
		return ret;
	}

}
