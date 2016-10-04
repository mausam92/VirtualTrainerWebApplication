package com.yash.vta.dao;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.User;

public interface UserDao {

	public void addUser(User user);
	public List<User> getAllUsers();
	public User deleteUser(int user_id);
	public boolean checkExistingUser(User user);
	Map<String, Integer> getAllUserName();
}
