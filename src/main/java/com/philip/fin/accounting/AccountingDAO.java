package com.philip.fin.accounting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
	
	public AccountingDAO() {
		logger.debug("Construct Accounting DAO");
		logger.debug("set configuration..");
		if(configuration == null)configuration=new Configuration(); 
		configuration.configure();
		
		logger.debug("open the session..");
		if(sr == null)sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		if(sf == null){sf = configuration.buildSessionFactory();ss = sf.openSession();}
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
		if(sr == null)sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
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
	
	public int createAccount(Account account) throws Exception{
		logger.debug("create one account;");
		boolean b=false;
		int account_id = 0;
		
		this.setup();
		
		ss.beginTransaction();
		ss.save(account);
		account_id = account.getAccount_id();
		account.getAccount_bal().setId(account_id);
		ss.save(account);
		ss.getTransaction().commit();
		
		logger.debug("the account has been successfully created!");
		this.clearup();
		b = true;
		
		return account_id;
	}
	
	public Account getAccount(int account_id){
		logger.debug("get account " + account_id + " from database;");
		boolean b=false;
		Account account = new Account();
		
		this.setup();
		
		ss.beginTransaction();
		account = (Account)ss.get(Account.class, account_id);
		ss.getTransaction().commit();
		
		logger.debug("get account " + account_id + " successfully!");
		this.clearup();
		b = true;
		
		return account;
	}
	
	public Account getAccount(int user_id, int type) {
		logger.debug("get account by user_id:" + user_id +" & type:" + type);
		Account account = new Account();
		List result;
		
		this.setup();
		
		ss.beginTransaction();
		result = ss.createQuery("from com.philip.fin.accounting.Account where user_account=" + user_id + " and account_type=" + type).list();
		Iterator i = result.iterator();
		if(i.hasNext()){
			account = (Account)i.next();
			result = ss.createQuery("from com.philip.fin.accounting.AccountBalance where id=" + account.getAccount_id()).list();
			Iterator i1 = result.iterator();
			if(i1.hasNext())account.setAccount_bal((AccountBalance)i1.next());
		}
		
		ss.getTransaction().commit();
		
		logger.debug("get account for the condition");
		this.clearup();
		
		return account;
	}
	
	public boolean deleteAccount(Account account){
		logger.debug("delete account " + account.getAccount_name() + " from database;");
		boolean b = false;
		
		this.setup();
		
		ss.beginTransaction();
		ss.delete(account);
		ss.getTransaction().commit();
		
		logger.debug("the account " + account.getAccount_name() + " has been succesfully deleted!");
		this.clearup();
		b = true;
		
		return b;
	}
	
	public int postDocument(Document document){
		logger.debug("post the document to account book");
		//boolean b = false;
		int doc_id;
		
		this.setup();
		
		ss.beginTransaction();
		ss.save(document);
		Iterator i = document.getDoc_items().iterator();
		HashSet items = new HashSet();
		while(i.hasNext()){
			Doc_Item item = (Doc_Item)i.next();
			item.setDoc_id(document.getId());
			
			items.add(item);
		}
		document.setDoc_items(items);
		ss.save(document);
		doc_id = document.getId();
		ss.getTransaction().commit();
		
		logger.debug("successfully post the document");
		return doc_id;
	}
	
	public Document getDocument(int doc_id){
		logger.debug("get document " + doc_id + " from database;");
		boolean b=false;
		Document document = new Document();
		
		this.setup();
		
		ss.beginTransaction();
		document = (Document)ss.get(Document.class, doc_id);
		ss.getTransaction().commit();
		
		logger.debug("get document " + doc_id + " successfully!;");
		this.clearup();
		b = true;
		
		return document;
	}
	
	public boolean deleteDocument(Document document){
		logger.debug("delete document " + document.getDescription() + " from database;");
		boolean b = false;
		
		this.setup();
		
		ss.beginTransaction();
		ss.delete(document);
		ss.getTransaction().commit();
		
		logger.debug("successfully delete document " + document.getDescription() + " from database!");
		this.clearup();
		b = true;
		
		return b;
	}
}
