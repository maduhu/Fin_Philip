package com.philip.fin.user;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

public class RegisterAction implements Action {
	
	private User user;
	
	public static final String SUCESS = "success";
	
	public static final String ERROR = "error";
	
	private static final Logger logger = Logger.getLogger(RegisterAction.class);
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\nAction is invoked............"); 
		logger.debug("begin to create user");
		
		System.out.println(user.getUser_name());
		
		return null;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User new_user){
		user = new_user;
	}
}
