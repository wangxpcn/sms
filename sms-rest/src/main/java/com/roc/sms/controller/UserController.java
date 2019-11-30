package com.roc.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roc.sms.bean.User;
import com.roc.sms.service.IUserService;

@RestController
public class UserController {
	@Autowired
    private IUserService userService;
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public int add(@RequestBody User user) {
		
		return userService.add(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public int update(@RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public int delete(@RequestParam(value = "id", required = true) Long userId) {
		return userService.delete(userId);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> find(@RequestParam(value = "userName", required = false) String userName) {
		List<User> us=userService.find(userName);
		return us;
	}
}