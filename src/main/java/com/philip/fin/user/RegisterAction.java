package com.philip.fin.user;

import java.util.Date;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.accounting.AccountingManager;

public class RegisterAction implements Action {
	
	private User user;
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR = "error";
	
	private static final Logger logger = Logger.getLogger(RegisterAction.class);
	
	private UserManager userManager = UserManager.getInstance();
	private AccountingManager accountingManager = AccountingManager.getInstance();
	
	public String execute() {
		// TODO Auto-generated method stub
		String forward = null;
		try {
			logger.debug("begin to create user");
			int user_id;
			
			user.setCreate_time(new Date());
			user.setUpdate_time(new Date());
			user.setAlive_flag(true);
			
			user_id = userManager.createUser(user);
			
			accountingManager.createUserAccount(user_id, user.getUser_name());
			forward = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			forward = ERROR;
		}
		
		return forward;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User new_user){
		user = new_user;
	}
}
