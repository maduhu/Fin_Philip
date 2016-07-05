package com.philip.fin.user;

import org.apache.log4j.Logger;

public class UserManager {
	private UserDAO dao = null;
	
	private static final Logger logger = Logger.getLogger(UserManager.class);
	
	public UserManager() {
		dao = new UserDAO();
	}
	
	public int createUser(User user) {
		boolean b = false;
		String salt;
		String encryptPass;
		int user_id = 0;
		//should change password:
		try {
			salt = PasswordUtil.byte2HexStr(PasswordUtil.getSalt());
			
			encryptPass = PasswordUtil.encrypt(user.getPassword(), salt);
			
			user.setPassword(encryptPass);
			user.setPassword_salt(salt);	
			
			user_id = dao.createUser(user);
			
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("create user failed!");
			logger.error(e);
			e.printStackTrace();
		} finally {
			return user_id;
		}
	}
	
	public User getUser(int user_id) {
		User user = null;
		try {
			user = dao.getUser(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} finally {
			return user;
		}
	}
	
	public boolean deleteUser(User user) {
		boolean b = false;
		try {
			b = dao.deleteUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} finally {
			return b;
		}
	}
}
