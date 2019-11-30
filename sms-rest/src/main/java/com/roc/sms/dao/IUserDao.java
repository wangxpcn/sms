package com.roc.sms.dao;

import java.util.List;

import com.roc.sms.bean.User;

public interface IUserDao {
	List<User> findAll();

    int add(User user);

    int update(User user);

    int delete(Long id);
}
