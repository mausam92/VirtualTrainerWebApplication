package com.yash.vta.service;

import javax.naming.NamingException;

import com.yash.vta.model.User;

public interface UserService {
	
	 String validateUser(String username, String password) throws NamingException;
	 public boolean checkExistingUser(User user);
}
