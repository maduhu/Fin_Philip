package com.philip.fin.accounting;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	
	public boolean updateAccounts(Document document) throws AccountException{
		boolean b = false;
		BigDecimal bal = new BigDecimal(0);
		
		this.setup();
		
		ss.beginTransaction();
		Iterator i = document.getDoc_items().iterator();
		while(i.hasNext()){
			Doc_Item item = (Doc_Item)i.next();
			Account account = null;
			
			//1、find the account:
			account = (Account)ss.get(Account.class, item.getAccount_id());
			//2、plus or minus from account:
			//3、if <0, throw Exception:
			bal = bal.add(account.getAccount_bal().getAccount_bal());
			if(account.getD_C()=='d'){
				if(item.getCredit_debit()=='d'){
					bal = bal.add(item.getAmount());
				} else if (item.getCredit_debit()=='c') {
					bal = bal.subtract(item.getAmount());
					if(bal.intValue() < 0)throw new AccountException("0001","the account balance is below zero which is forbiden!");
				}
			} else if (account.getD_C()=='c') {
				if(item.getCredit_debit()=='c'){
					bal = bal.add(item.getAmount());
				} else if (item.getCredit_debit()=='d'){
					bal = bal.subtract(item.getAmount());
					if(bal.intValue() < 0)throw new AccountException("0001","the account balance is below zero which is forbiden!");
				}
			}
			
			//4、update the value to database:
			AccountBalance balance = account.getAccount_bal();
			balance.setAccount_bal(bal);
			balance.setUpdate_time(new Date());
			ss.save(balance);
		}
		//5、commit:
		ss.getTransaction().commit();
		
		this.clearup();
		
		b = true;
		return b;
	}

	public boolean archiveAccounts(Date date){
		boolean b = false;
		List<AccountBalance[]> result=null;
		Iterator i = null;
		
		this.setup();
		
		ss.beginTransaction();
		result = ss.createQuery("from com.philip.fin.accounting.AccountBalance").list();
		i = result.iterator();
		while(i.hasNext()){
			AccountBalance bal = (AccountBalance)i.next();
			AccountBalHistory history = new AccountBalHistory();
			history.setId(bal.getId());
			history.setAccount_num(bal.getAccount_num());
			history.setDate(date);
			history.setAccount_name(bal.getAccount_name());
			history.setAccount_bal(bal.getAccount_bal());
			
			ss.save(history);
		}
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		b = true;
		return b;
	}
	
	public boolean isHistoryBalance(Date date){
		boolean b = false;
		BigDecimal debit = new BigDecimal(0);
		BigDecimal credit = new BigDecimal(0);
		List<Object[]> result=null;
		
		this.setup();
		
		ss.beginTransaction();
		result = ss.createQuery("select acc.id, acc.account_num, acc.D_C, from com.philip.fin.accounting.AccountBalHistory as his join com.philip.fin.accounting.Accout as acc where date=" + date + " and his.id = acc.id").list();
		for(Object[] object : result){
			AccountBalance bal = new AccountBalance();
			
			
		}
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		b = true;
		return b;
	}
}
