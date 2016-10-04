package com.yash.vta.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yash.vta.model.User;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static User getUser() {
		HttpSession session = getSession();
		
		return (User) session.getAttribute("user");
	}

/*	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("userid");
		else
			return null;
	}*/
	
	public static String getUsername() {
		
		HttpSession session = getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			
			return null;
		}else {
			
			String username = user.getUser_name();
			return username;
		}
	}
	
	public static int getUserID() {
		
		HttpSession session = getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUser_id();
		
		return  userId;
	}
	
	public static int getRoleID() {
		
		HttpSession session = getSession();
		User user = (User) session.getAttribute("user");
		int roleId = user.getRole_id();
		
		return roleId;
	}
}