package com.philip.fin.user;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDAO {
	
	private static final Logger logger = Logger.getLogger(UserDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public UserDAO() {
		logger.debug("Construct User DAO");
		logger.debug("set configuration..");
		if(configuration == null)configuration=new Configuration(); 
		configuration.configure();
		
		logger.debug("open the session..");
		//if(sr == null)sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		if(sf == null){sf = configuration.buildSessionFactory();ss = sf.openSession();}
		else ss = sf.openSession();
	}
	
	public boolean setup() {
		logger.debug("start to setup the connection to database, first set return to false..");
		boolean b = false;
		
		logger.debug("set configuration..");
		if(configuration == null){
			configuration=new Configuration();
			configuration.configure();
		}
		
		logger.debug("open the session..");
		//if(sr == null)sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sf = configuration.buildSessionFactory();
		ss = sf.openSession();
				
		logger.debug("successly setup the connection");
		b = true;
		return b;
	}
	
	public boolean clearup(){
		logger.debug("task finish,close the session");
		boolean b = false;
		
		if(ss!=null&&ss.isConnected())ss.close();
		if(sf!=null&&ss.isConnected())sf.close();
		
		logger.debug("the session successfully closed");
		b = true;
		return b;
	}
	
	public int createUser(User user) throws UserException {
		logger.debug("start to create user");
		int user_id;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			ss.save(user);
			user_id = user.getId();
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new UserException();
		}

		return user_id;
	}
	
	public User getUser(int user_id) throws UserException {
		logger.debug("try to get user");
		User user = null;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			user = ss.get(User.class, user_id);
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new UserException();
		}
			
		return user;
	}
	
	public boolean deleteUser(User user) throws UserException {
		logger.debug("try to delete user");
		boolean b = false;
		
		this.setup();
		
		try{
			ss.beginTransaction();
			ss.delete(user);
			ss.getTransaction().commit();
			
			b = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new UserException(e);
		}

		return b;
	}
}
