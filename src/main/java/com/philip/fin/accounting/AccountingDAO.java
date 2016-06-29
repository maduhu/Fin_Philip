package com.philip.fin.accounting;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.test.LoginAction;

public class AccountingDAO {
	private static final Logger logger = Logger.getLogger(AccountingDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public boolean setup() {
		logger.debug("start to setup the connection to database, first set return to false..");
		boolean b = false;
		
		logger.debug("set configuration..");
		configuration = new Configuration();
		configuration.configure();
		
		logger.debug("open the session..");
		sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sf = configuration.buildSessionFactory();
		ss = sf.openSession();
		
		logger.debug("successly setup the connection");
		b = true;
		return b;
	}
	
	public boolean clearup(){
		logger.debug("task finish,close the session");
		boolean b = false;
		
		ss.close();
		sf.close();
		
		logger.debug("the session successfully closed");
		b = true;
		return b;
	}
	
	/*public boolean createAccount(Account account){
		
	}*/
	
	public boolean postDocuments(ArrayList documents){
		logger.debug("post the document to account book");
		boolean b = false;
		
		this.setup();
		
		ss.beginTransaction();
		Iterator i = documents.iterator();
		while(i.hasNext()){
			Document doc = (Document)i.next();
			
			logger.debug("saveing document " + doc.getId() +": account_num: " + doc.getAccount_num() + ", description: " + doc.getDescription() +" to database" );
			ss.save(doc);
		}
		ss.getTransaction().commit();
		
		logger.debug("successfully post the documents");
		b = true;
		return b;
	}
}
