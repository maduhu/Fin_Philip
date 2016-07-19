package com.philip.fin.user;

import java.util.Date;

import com.philip.fin.accounting.AccountConstants;
import com.philip.fin.accounting.AccountingManager;

import junit.framework.TestCase;

public class UserManagerTest extends TestCase {
	private UserManager manager = null;
	
	protected void setUp() throws Exception {
		manager = new UserManager();
	}
	
	protected void tearDown() throws Exception {
		manager = null;
	}
	
	public void testUser() throws Exception {
		int user_id;
		
		//create user:
		User user = new User();
		user.setUser_name("chen peng");
		user.setChinese_name("³ÂÅô");
		user.setPassword("test123");
		user.setMail_address("robbinpeng@163.com");
		user.setMobile("13561073279");
		user.setCreate_time(new Date());
		user.setUpdate_time(new Date());
		user.setLast_login(new Date());
		user.setLast_operation(AccountConstants.BIZ_OPER_NO_OPERATION);
		user.setAlive_flag(true);
		user.setDelete_time(null);
		
		user_id = manager.createUser(user);
		
		//get it from database:
		User tUser = manager.getUser(user_id);
		
		assertEquals(tUser.getUser_name(),"chen peng");
		assertEquals(tUser.getChinese_name(),"³ÂÅô");
		assertEquals(tUser.getPassword(),PasswordUtil.encrypt("test123", tUser.getPassword_salt()));
		assertEquals(tUser.getMail_address(),"robbinpeng@163.com");
		assertEquals(tUser.getMobile(),"13561073279");
		assertEquals(tUser.getLast_operation(),AccountConstants.BIZ_OPER_NO_OPERATION);
		assertEquals(tUser.isAlive_flag(),true);
		
		//delete it from database:
		manager.deleteUser(tUser);
	}
}
