package com.philip.fin.accounting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;

public class AccountingManager {
		
	private AccountingDAO accountingDAO = null;
	
	private static final Logger logger = Logger.getLogger(AccountingManager.class);
	
	public AccountingManager() {
		accountingDAO = new AccountingDAO();
	}
	
	public boolean createUserAccount(int user_id, String user_name) {
		boolean b = false;
		
		try {
			//create deposit account:
			Account account1 = new Account();
			//account1.setAccount_num("0100000101");
			account1.setAccount_name("User " + user_id + "'s deposit account");
			account1.setChinese_name("" + user_name + "的储蓄账户");
			account1.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			account1.setType_description("user deposit account for saving money");
			account1.setDescription("User " + user_name + "'s deposit account to save money to be used in the platform");
			account1.setCreate_time(new Date());
			account1.setUpdate_time(new Date());
			account1.setUser_account(user_id);
			//account1.getAccount_bal().setAccount_num(account_num);
			account1.getAccount_bal().setAccount_name("User " + user_id + "'s deposit account");
			account1.getAccount_bal().setCreate_time(new Date());
			account1.getAccount_bal().setUpdate_time(new Date());
			accountingDAO.createAccount(account1);
			
			//create invest account:
			Account account2 = new Account();
			//account2.setAccount_num("0100000102");
			account2.setAccount_name("User " + user_id + "'s invest account");
			account2.setChinese_name("" + user_name + "的投资账户");
			account2.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			account2.setType_description("user invest account for investing project");
			account2.setDescription("User " + user_name + "'s invest account to invest different projects");
			account2.setCreate_time(new Date());
			account2.setUpdate_time(new Date());
			account2.setUser_account(user_id);
			//account2.getAccount_bal().setAccount_num(account_num);
			account2.getAccount_bal().setAccount_name("User " + user_id + "'s invest account");
			account2.getAccount_bal().setCreate_time(new Date());
			account2.getAccount_bal().setUpdate_time(new Date());
			accountingDAO.createAccount(account2);
			
			//create loan account:
			Account account3 = new Account();
			//account3.setAccount_num("0100000103");
			account3.setAccount_name("User " + user_id + "'s loan account");
			account3.setChinese_name("" + user_name + "的借款账户");
			account3.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_LOAN);
			account3.setType_description("user loan account for accept platform loan");
			account3.setDescription("User " + user_name + "'s loan account to accept platform loan");
			account3.setCreate_time(new Date());
			account3.setUpdate_time(new Date());
			account3.setUser_account(user_id);
			//account3.getAccount_bal().setAccount_num(account_num);
			account3.getAccount_bal().setAccount_name("User " + user_id + "'s loan account");
			account3.getAccount_bal().setCreate_time(new Date());
			account3.getAccount_bal().setUpdate_time(new Date());
			accountingDAO.createAccount(account3);
			
			//create account payable account:
			Account account4 = new Account();
			//account4.setAccount_num("0100000104");
			account4.setAccount_name("User " + user_id + "'s account payable account");
			account4.setChinese_name("" + user_name + "的预计还款账户");
			account4.setAccount_type(AccountConstants.ACCOUNT_TYPE_USER_ACCOUNT_PAYABLE);
			account4.setType_description("user payable account for repay the loan");
			account4.setDescription("User " + user_name +"'s payable account for repay loan");
			account4.setCreate_time(new Date());
			account4.setUpdate_time(new Date());
			account4.setUser_account(user_id);
			//account4.getAccount_bal().setAccount_num(account_num);
			account4.getAccount_bal().setAccount_name("User " + user_id + "'s account payable account");
			account4.getAccount_bal().setCreate_time(new Date());
			account4.getAccount_bal().setUpdate_time(new Date());
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
		
		
		try {
			//Get the deposit account:
			account = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			
			//and the platform account is 0:
			//debit the platform account and credit the deposit account:
			Document document = new Document();
			document.setBusiness_event(AccountConstants.BIZ_OPER_DEPOSIT_MONEY);
			document.setDescription("user " + user_id + " has deposit " + amount + "RMB to the platform, so his account will increase");
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
		} finally {
			
		}
		
		return b;
	}
	
	public boolean drawMoney (int user_id, BigDecimal amount) {
		boolean b = false;
		Account account = null;
		
		//Get the deposit account:
		try {
			account = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			
			//and the platform account is 0:
			//credit the platform account and debit the deposit account:
			Document document = new Document();
			document.setBusiness_event(AccountConstants.BIZ_OPER_DRAW_MONEY);
			document.setDescription("user " + user_id + " has draw " + amount + "RMB from the platform, so his account will decrease");
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
		}
				
		return b;
	}
	
	public boolean invest (int user_id, BigDecimal amount) {
		boolean b = false;
		Account accountD = null;
		Account accountI = null;
		
		//Get the deposit account:
		try {
			accountD = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			accountI = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			
			
			//credit the invest account and debit the deposit account:
			Document document = new Document();
			document.setBusiness_event(AccountConstants.BIZ_OPER_INVEST_MONEY);
			document.setDescription("user " + user_id + " has invest " + amount + "RMB to a project, so his amout will transfer from deposit to invest");
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
		}

		return b;
	}
	
	public boolean raiseFailed (int user_id, BigDecimal amount) {
		boolean b = false;
		Account accountD = null;
		Account accountI = null;
		
		//Get the deposit&invest account:
		try {
			accountD = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
			accountI = accountingDAO.getAccount(user_id, AccountConstants.ACCOUNT_TYPE_USER_INVEST);
			
			//debit the invest account and credit the deposit account:
			Document document = new Document();
			document.setBusiness_event(AccountConstants.BIZ_OPER_RAISE_FAILED_RETURN);
			document.setDescription("the project failed to raise money, so return moeny back to " + user_id + ":" + amount + "RMB from invest account to deposit account");
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
		}

		return b;
	}
	
}
