package com.philip.fin.test;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.test.User;

public class LoginAction implements Action{
	private User user;
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR = "error";
	
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	
	public String execute() throws Exception
	{
		logger.debug("begin to validate user name and password");
		
		if("zhangsan".equals(user.getUsername()) && "123".equals(user.getPassword()))
			return SUCCESS;
		else
			return ERROR;
		
		//logger.debug("validate user name and password ends");
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User new_user){
		user = new_user;
	}
}
