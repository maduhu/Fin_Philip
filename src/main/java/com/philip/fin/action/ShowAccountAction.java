package com.philip.fin.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.accounting.Account;
import com.philip.fin.accounting.AccountBalance;
import com.philip.fin.accounting.AccountConstants;
import com.philip.fin.accounting.AccountingManager;

public class ShowAccountAction implements Action{
	
	public static final String SUCCESS = "success";
	
	private static final Logger logger = Logger.getLogger(ShowAccountAction.class);
	
	private AccountingManager accountManager = AccountingManager.getInstance();

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int user_id = 26;   //for test;
		logger.debug("start to show the account information¡£");
		
		Account account = accountManager.getAccountBalance(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
		
		AccountBalance balance = account.getAccount_bal();
		
		return SUCCESS;
	}
}
