package com.yash.vta.serviceimpl;

import javax.naming.NamingException;

import com.yash.vta.dao.UserDao;
import com.yash.vta.daoimpl.UserDaoImpl;
import com.yash.vta.ldap.LDAPInform;
import com.yash.vta.ldap.LDAPTest;
import com.yash.vta.model.User;
import com.yash.vta.service.UserService;

public class UserServiceImpl implements UserService{
	

	
	public String validateUser(String username, String password) throws NamingException{
		
		LDAPTest ldap = new LDAPTest();
		ldap.validateUser(username, password);
		User user = getUserInfo(username);
		user.setUser_status("inactive");
		
		if(user.getUser_designation().contains("Manager") || user.getUser_designation().contains("manager")){
			user.setRole_id(3);
		}else{
				if(user.getUser_designation().contains("Trainer") || user.getUser_designation().contains("trainer")){
					user.setRole_id(2);
				}else{
					user.setRole_id(5);
					}
		}
		
		
		
		 UserDao userDao = new UserDaoImpl();
				userDao.addUser(user);
		return null;
	}
	
	public User getUserInfo(String username)throws NamingException{
		
		LDAPInform ldapInform = new LDAPInform();
		String[] lookupName = username.split("@");
	    User user = ldapInform.getUserInfo(lookupName[0]);
	    user.setUser_manager_mail(ldapInform.getUserManagerInfo(user.getUser_manager()));
	    
	    return user;
	}
	
	public boolean checkExistingUser(User user){
		UserDao userdao = new UserDaoImpl();
		boolean userExist = userdao.checkExistingUser(user);
		return userExist;
	}
	
}
