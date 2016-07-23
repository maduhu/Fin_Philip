package com.philip.fin.accounting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.philip.fin.event.Event;
import com.philip.fin.event.EventException;
import com.philip.fin.event.EventManager;
import com.philip.fin.invest.Invest;
import com.philip.fin.invest.InvestManager;

public class AccountingManager {
		
	private AccountingDAO accountingDAO = null;
	
	private static AccountingManager manager = null;
	
	private InvestManager investManager = null; 
	
	private EventManager eventManager = null;
	
	private static final Logger logger = Logger.getLogger(AccountingManager.class);
	
	public static AccountingManager getInstance(){
		if( manager == null ) manager = new AccountingManager();
		return manager;
	}
	
	private AccountingManager() {
		accountingDAO = new AccountingDAO();
	}
	
	public boolean createUserAccount(int user_id, String user_name) {
		boolean b = false;
		
		try {
			
			//create deposit account:
			Account account1 = new Account();
			//account1.setAccount_num("0100000101");
			//account1.setAccount_num(account_num);
			account1.setAccount_name("User " + user_id + " deposit");
			account1.setChinese_name("" + user_name + "的储蓄账户");
			account1.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			account1.setType_description("user save");
			account1.setDescription("User " + user_name + "'s deposit account to save money");
			account1.setCreate_time(new Date());
			account1.setUpdate_time(new Date());
			account1.setUser_account(user_id);
			//account1.getAccount_bal().setAccount_num(account_num);
			account1.getAccount_bal().setAccount_name("User " + user_id + " deposit");
			account1.getAccount_bal().setCreate_time(new Date());
			account1.getAccount_bal().setUpdate_time(new Date());
			account1.getAccount_bal().setAccount(account1);
			account1.setD_C('c');
			accountingDAO.createAccount(account1);
			
			//create invest account:
			Account account2 = new Account();
			//account2.setAccount_num("0100000102");
			account2.setAccount_name("User " + user_id + " invest");
			account2.setChinese_name("" + user_name + "的投资账户");
			account2.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			account2.setType_description("investing");
			account2.setDescription("User " + user_name + "'s account to invest money");
			account2.setCreate_time(new Date());
			account2.setUpdate_time(new Date());
			account2.setUser_account(user_id);
			//account2.getAccount_bal().setAccount_num(account_num);
			account2.getAccount_bal().setAccount_name("User " + user_id + "invest");
			account2.getAccount_bal().setCreate_time(new Date());
			account2.getAccount_bal().setUpdate_time(new Date());
			account2.getAccount_bal().setAccount(account2);
			account2.setD_C('c');
			accountingDAO.createAccount(account2);
			
			//create loan account:
			Account account3 = new Account();
			//account3.setAccount_num("0100000103");
			account3.setAccount_name("User " + user_id + "loan");
			account3.setChinese_name("" + user_name + "的借款账户");
			account3.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_LOAN);
			account3.setType_description("loan acc");
			account3.setDescription("User " + user_name + "'s loan account to accept platform loan");
			account3.setCreate_time(new Date());
			account3.setUpdate_time(new Date());
			account3.setUser_account(user_id);
			//account3.getAccount_bal().setAccount_num(account_num);
			account3.getAccount_bal().setAccount_name("User " + user_id + " loan");
			account3.getAccount_bal().setCreate_time(new Date());
			account3.getAccount_bal().setUpdate_time(new Date());
			account3.getAccount_bal().setAccount(account3);
			account3.setD_C('d');
			accountingDAO.createAccount(account3);
			
			//create account payable account:
			Account account4 = new Account();
			//account4.setAccount_num("0100000104");
			account4.setAccount_name("User " + user_id + " ap acc");
			account4.setChinese_name("" + user_name + "的预计还款账户");
			account4.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_ACCOUNT_PAYABLE);
			account4.setType_description("acc pay");
			account4.setDescription("User " + user_name +"'s payable account for repay loan");
			account4.setCreate_time(new Date());
			account4.setUpdate_time(new Date());
			account4.setUser_account(user_id);
			//account4.getAccount_bal().setAccount_num(account_num);
			account4.getAccount_bal().setAccount_name("User " + user_id + "ap");
			account4.getAccount_bal().setCreate_time(new Date());
			account4.getAccount_bal().setUpdate_time(new Date());
			account4.getAccount_bal().setAccount(account4);
			account4.setD_C('d');
			accountingDAO.createAccount(account4);
		
			b = true;
		} catch (AccountException e) {
			e.printStackTrace();
			logger.error(e);
		} 

		return b;
	}
	
	public boolean depositMoney(int user_id, BigDecimal amount){
		boolean b = false;
		Account account = null;
		int event_id;
		Event event = new Event();
		
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_DEPOSIT_MONEY);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			
			//Get the deposit account:
			account = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			
			//and the platform account is 0:
			//debit the platform account and credit the deposit account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("user " + user_id + " has deposit " + amount + "RMB.");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
			
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item1.setAmount(amount);
			item1.setCredit_debit('d');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
			
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(account.getAccount_id());
			item2.setAmount(amount);
			item2.setCredit_debit('c');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
			
			document.setDoc_items(items);
			
			//1,update balance; 2,post document
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
			
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		} finally {
			
		}
		
		return b;
	}
	
	public boolean drawMoney (int user_id, BigDecimal amount) {
		boolean b = false;
		Account account = null;
		Event event = new Event();
		int event_id;
		
		//Get the deposit account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_DRAW_MONEY);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			account = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			
			//and the platform account is 0:
			//credit the platform account and debit the deposit account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("user " + user_id + " has draw " + amount + "RMB.");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item1.setAmount(amount);
			item1.setCredit_debit('c');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
					
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(account.getAccount_id());
			item2.setAmount(amount);
			item2.setCredit_debit('d');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
				
		return b;
	}
	
	public boolean invest (int user_id, BigDecimal amount) {
		boolean b = false;
		Account accountD = null;
		Account accountI = null;
		Event event = new Event();
		int event_id;
		
		//Get the deposit account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_INVEST_MONEY);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			
			accountD = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			accountI = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			
			
			//credit the invest account and debit the deposit account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("user " + user_id + " has invest " + amount + "RMB.");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(accountI.getAccount_id());
			item1.setAmount(amount);
			item1.setCredit_debit('c');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
					
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(accountD.getAccount_id());
			item2.setAmount(amount);
			item2.setCredit_debit('d');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);

			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

		return b;
	}
	
	public boolean raiseFailed (int user_id, BigDecimal amount) {
		boolean b = false;
		Account accountD = null;
		Account accountI = null;
		Event event = new Event();
		int event_id;
		
		//Get the deposit&invest account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_RAISE_FAILED_RETURN);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			
			accountD = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			accountI = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			
			//debit the invest account and credit the deposit account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("return " + user_id + ":" + amount + "RMB.");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(accountI.getAccount_id());
			item1.setAmount(amount);
			item1.setCredit_debit('d');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
					
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(accountD.getAccount_id());
			item2.setAmount(amount);
			item2.setCredit_debit('c');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

		return b;
	}
	
	public boolean payToLender (int user_id, BigDecimal amount) {
		boolean b = false;
		Account accountL = null;
		Account accountP = null;
		Event event = new Event();
		int event_id;
		
		//Get the deposit&invest account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_PAY_TO_LENDER_ACCOUNT);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			accountL = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_LOAN);
			accountP = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			
			//debit the user loan account and credit the platform account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("transfer to " + user_id + ":" + amount + "RMB");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(accountL.getAccount_id());
			item1.setAmount(amount);
			item1.setCredit_debit('d');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
					
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item2.setAmount(amount);
			item2.setCredit_debit('c');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

		return b;
	}
	
	public boolean repayToPlatform (int user_id, BigDecimal amount, BigDecimal interest) {
		boolean b = false;
		Account accountL = null;
		Account accountP = null;
		Account accountI = null;
		Event event = new Event();
		int event_id;
		
		//Get the deposit&invest account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_REPAY_TO_PLATFORM);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			accountL = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_LOAN);
			accountP = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			
			//debit the user loan account and credit the platform account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("the user repay back.");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(accountL.getAccount_id());
			item1.setAmount(amount);
			item1.setCredit_debit('c');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
					
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item2.setAmount(amount);
			item2.setCredit_debit('d');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
			
			Doc_Item item3 = new Doc_Item();
			item3.setItem_id(3);
			item3.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item3.setAmount(interest);
			item3.setCredit_debit('d');
			item3.setCreate_time(new Date());
			item3.setUpdate_time(new Date());
			items.add(item3);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

		return b;
	}
	
	public boolean payToUsers(int user_id, BigDecimal amount, BigDecimal interest) {
		boolean b = false;
		Account accountD = null;
		Account accountP = null;
		Event event = new Event();
		int event_id;
			
		//Get the deposit&invest account:
		try {
			eventManager = EventManager.getInstance();
			event.setBusiness_type(AccountConstants.BIZ_OPER_PAY_BACK_TO_USER);
			event.setCreate_date(new Date());
			event.setUpdate_date(new Date());
			event_id = eventManager.createEvent(event);
			accountD = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			accountP = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			
			//debit the user loan account and credit the platform account:
			Document document = new Document();
			document.setBusiness_event(event_id);
			document.setDescription("send money to user");
			document.setCreate_time(new Date());
			document.setUpdate_time(new Date());
					
			HashSet items = new HashSet();
			Doc_Item item1 = new Doc_Item();
			item1.setItem_id(1);
			item1.setAccount_id(accountD.getAccount_id());
			item1.setAmount(amount);
			item1.setCredit_debit('d');
			item1.setCreate_time(new Date());
			item1.setUpdate_time(new Date());
			items.add(item1);
			
			Doc_Item item2 = new Doc_Item();
			item2.setItem_id(2);
			item2.setAccount_id(accountD.getAccount_id());
			item2.setAmount(interest);
			item2.setCredit_debit('d');
			item2.setCreate_time(new Date());
			item2.setUpdate_time(new Date());
			items.add(item2);
					
			Doc_Item item3 = new Doc_Item();
			item3.setItem_id(3);
			item3.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item3.setAmount(amount);
			item3.setCredit_debit('c');
			item3.setCreate_time(new Date());
			item3.setUpdate_time(new Date());
			items.add(item3);
			
			Doc_Item item4 = new Doc_Item();
			item4.setItem_id(4);
			item4.setAccount_id(AccountConstants.ACCOUNT_PLATFORM_ACCOUNT);
			item4.setAmount(interest);
			item4.setCredit_debit('c');
			item4.setCreate_time(new Date());
			item4.setUpdate_time(new Date());
			items.add(item4);
					
			document.setDoc_items(items);
			
			accountingDAO.updateAccounts(document);
			accountingDAO.postDocument(document);
			
			b = true;
		} catch (AccountException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		} catch (EventException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

		return b;
	}
	
	public Account getAccountBalance(int user_id, int acc_type) throws AccountException{
		return accountingDAO.getAccount(user_id, acc_type);
	}
}
