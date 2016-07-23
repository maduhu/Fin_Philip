package com.philip.fin.invest;

import java.util.HashSet;

import org.apache.log4j.Logger;

import com.philip.fin.accounting.AccountingManager;

public class InvestManager {
	private InvestDAO investDAO = null;
	
	private static InvestManager manager = null;
	
	private AccountingManager accountingManager = null;
	
	private static final Logger logger = Logger.getLogger(InvestManager.class);
	
	public static InvestManager getInstance(){
		if( manager == null ) manager = new InvestManager();
		return manager;
	}
	
	private InvestManager() {
		investDAO = new InvestDAO();
	}
	
	public void invest(Invest invest) {
		try {
			investDAO.createInvest(invest);
			
			//accounting:
			accountingManager = AccountingManager.getInstance();
			accountingManager.invest(invest.getUser(), invest.getAmount());
		} catch (InvestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public HashSet getInvestRecords(int loan_id) {
		HashSet investors = null;
		
		investors = manager.getInvestRecords(loan_id);
		
		return investors;
	}
}
