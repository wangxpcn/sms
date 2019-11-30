package com.roc.sms.service;

import java.util.List;

import com.roc.sms.bean.User;

public interface IUserService {
	List<User> findAll();

    int add(User user);

    int update(User user);

    int delete(Long id);
}
