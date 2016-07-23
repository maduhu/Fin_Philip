package com.philip.fin.action;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.accounting.Account;
import com.philip.fin.accounting.AccountBalance;
import com.philip.fin.accounting.AccountConstants;
import com.philip.fin.accounting.AccountingManager;

public class DepositAction implements Action{
	
	public static final String SUCCESS = "success";
	
	private static final Logger logger = Logger.getLogger(DepositAction.class);
	
	private AccountingManager accountingManager = AccountingManager.getInstance();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("deposit money to account");
		int user_id = 26;
		
		accountingManager.depositMoney(user_id, new BigDecimal(100));
		
		Account account = accountingManager.getAccountBalance(user_id, AccountConstants.ACCOUNT_TYPE_USER_DEPOSIT);
		
		AccountBalance balance = account.getAccount_bal();
				
		return SUCCESS;
	}

}
