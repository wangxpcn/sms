package com.roc.sms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roc.sms.bean.User;
import com.roc.sms.dao.IUserDao;

@Repository
public class UserDaoImpl implements IUserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> find(String userName) {
		String sql = null;
		if (userName == null) {
			sql = "select * from roc_rabbit";
		} else {
			sql = "select * from roc_rabbit WHERE name=\"" + userName + "\"";
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public int add(User user) {
		String sql = "INSERT INTO roc_rabbit (name,age,sex,interesting) VALUES (?,?,?,?)";
		int result = jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getSex(), user.getInteresting());
		return result;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE roc_rabbit SET name=?,age=?,sex=?,interesting=? WHERE id=?";
		int result = jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getSex(), user.getInteresting(),
				user.getId());
		return result;
	}

	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM roc_rabbit WHERE id=?";
		int result = jdbcTemplate.update(sql, id);
		return result;
	}
}
