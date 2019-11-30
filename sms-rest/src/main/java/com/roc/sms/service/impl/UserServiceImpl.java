package com.roc.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roc.sms.bean.User;
import com.roc.sms.dao.IUserDao;
import com.roc.sms.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public int update(User user) {
		
		return userDao.update(user);
	}

	@Override
	public int delete(Long id) {
		return userDao.delete(id);
	}
}
