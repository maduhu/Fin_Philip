package com.philip.fin.user;

import java.util.Date;

import com.philip.fin.accounting.AccountConstants;
import com.philip.fin.accounting.AccountingManager;

import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	private UserDAO dao = null;
	
	protected void setUp() throws Exception {
		dao = new UserDAO();
		dao.setup();
	}
	
	protected void tearDown() throws Exception {
		dao.clearup();
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
		
		user_id = dao.createUser(user);
		
		//get it from database:
		User tUser = dao.getUser(user_id);
		
		assertEquals(tUser.getUser_name(),"chen peng");
		assertEquals(tUser.getChinese_name(),"³ÂÅô");
		assertEquals(tUser.getPassword(),"test123");
		assertEquals(tUser.getMail_address(),"robbinpeng@163.com");
		assertEquals(tUser.getMobile(),"13561073279");
		assertEquals(tUser.getLast_operation(),AccountConstants.BIZ_OPER_NO_OPERATION);
		assertEquals(tUser.isAlive_flag(),true);
		
		//delete it from database:
		dao.deleteUser(tUser);
	}
}
