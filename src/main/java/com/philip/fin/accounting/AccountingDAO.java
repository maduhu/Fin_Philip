package com.philip.fin.accounting;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	/*public static void main(String[] arg) throws ParseException{
		Date date = new SimpleDateFormat("yyyy-mm-dd").parse("2016-06-30");
		Calendar cal = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		//date = cal.getTime();
		//System.out.println(sdf.format(date));
		//cal.add(Calendar.DATE, -1);
		//date = cal.getTime();
		//System.out.println(cal.get(Calendar.YEAR));
		//System.out.println(cal.get(Calendar.MONTH)+1);
		//System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
		AccountingDAO dao = new AccountingDAO();
		
		CheckInfo b = dao.checkAllAccountsBalance();
		System.out.println(b.isBalance());
		System.out.println(b.getAccount_num());
	}*/
	
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
		String acc_num = null;
		
		this.setup();
		
		ss.beginTransaction();
		ss.save(account);
		account_id = account.getAccount_id();
		account.getAccount_bal().setId(account_id);
		acc_num = String.format("%03d", account.getAccount_type()) + String.format("%07d", account_id);
		account.setAccount_num(acc_num);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		result = ss.createSQLQuery("select acc.id, acc.account_num, acc.D_C, his.account_bal from account_balance_history his inner join account acc on his.id = acc.id where date_format(his.date,'%Y-%M-%D') = date_format('" + sdf.format(date) +"','%Y-%M-%D')").list();
		for(Object[] object : result){
			String account_num = (String)object[1];
			java.lang.Character D_C = (java.lang.Character)object[2];
			BigDecimal bal = (BigDecimal)object[3];
			
			if(D_C =='d'){
				debit = debit.add(bal);
			} else if(D_C =='c') {
				credit = credit.add(bal);
			}
		}
		
		if(debit.equals(credit)){
			b = true;
		} else {
			b = false;
		}
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		return b;
	}
	
	public boolean isCurrentBalance(){
		boolean b = false;
		BigDecimal debit = new BigDecimal(0);
		BigDecimal credit = new BigDecimal(0);
		List<Object[]> result=null;
		
		this.setup();
		
		ss.beginTransaction();
		result = ss.createSQLQuery("select bal.account_bal, acc.D_C from account_balance bal inner join account acc on bal.id = acc.id").list();
		for(Object[] object : result){
			BigDecimal bal = (BigDecimal)object[0];
			java.lang.Character D_C = (java.lang.Character)object[1];
			
			if(D_C =='d'){
				debit = debit.add(bal);
			} else if(D_C =='c') {
				credit = credit.add(bal);
			}
		}
		
		if(debit.equals(credit)){
			b = true;
		} else {
			b = false;
		}
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		return b;
	}
	
	public boolean isDocumentsBalance(Date date){
		boolean b = false;
		BigDecimal debit = new BigDecimal(0);
		BigDecimal credit = new BigDecimal(0);
		List<Object[]> result=null;
		
		this.setup();
		
		ss.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		result = ss.createQuery("from com.philip.fin.accounting.Doc_Item where date_format(update_time,'%Y-%M-%D') = date_format('" + sdf.format(date) +"','%Y-%M-%D')").list();
		Iterator i = result.iterator();
		while(i.hasNext()){
			Doc_Item item = (Doc_Item)i.next();
			
			if(item.getCredit_debit() == 'd'){
				debit = debit.add(item.getAmount());
			} else if (item.getCredit_debit() == 'c'){
				credit = credit.add(item.getAmount());
			}
		}
		
		if(debit.equals(credit)){
			b = true;
		} else {
			b = false;
		}
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		return b;
	}
	
	public CheckInfo checkAllAccountsBalance(){
		CheckInfo check = new CheckInfo();
		check.setBalance(true);
		List<Object[]> result=null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		//Date yestoday = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String yestoday = "" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +(cal.get(Calendar.DAY_OF_MONTH));
		
		this.setup();
		
		ss.beginTransaction();
		
		//1、Get all accounts:
		result = ss.createQuery("from com.philip.fin.accounting.AccountBalance").list();
		Iterator i = result.iterator();
		while(i.hasNext()){
			//2、Get start、end、middle amount number:
			AccountBalance bal = (AccountBalance)i.next();
			Account account = new Account();
			AccountBalHistory history = new AccountBalHistory();
			
			account = (Account)ss.get(Account.class, bal.getId());
			
			result = ss.createQuery("from com.philip.fin.accounting.AccountBalHistory where id=" + bal.getId() + " and date_format(date,'%Y-%M-%D') = date_format('" + yestoday + "','%Y-%M-%D')").list();
			Iterator i1 = result.iterator();
			if(i1.hasNext()){
				history = (AccountBalHistory)i1.next();
			}
			BigDecimal amount = history.getAccount_bal();
			
			result = ss.createQuery("from com.philip.fin.accounting.Doc_Item where account_id=" + bal.getId()).list();
			Iterator i2 = result.iterator();
			
			//3、Check whether the account is balance, if not break and return false;
			while(i2.hasNext()){
				Doc_Item item = (Doc_Item)i2.next();
				if(account.getD_C() == 'd'){
					if(item.getCredit_debit()=='d'){
						amount = amount.add(item.getAmount());
					} else if (item.getCredit_debit() == 'c'){
						amount = amount.subtract(item.getAmount());
					}
				} else if (account.getD_C() == 'c'){
					if(item.getCredit_debit()=='c'){
						amount = amount.add(item.getAmount());
					} else if (item.getCredit_debit() == 'd'){
						amount = amount.subtract(item.getAmount());
					}
				}
			}
			
			if(!amount.equals(account.getAccount_bal())){
				check.setBalance(false);
				check.setAccount_id(account.getAccount_id());
				check.setAccount_num(account.getAccount_num());
				break;
			}
		}		
		
		ss.getTransaction().commit();
		
		this.clearup();
		
		return check;
	}
}
