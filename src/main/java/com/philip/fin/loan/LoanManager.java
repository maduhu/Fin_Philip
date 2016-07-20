package com.philip.fin.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.philip.fin.accounting.AccountingManager;
import com.philip.fin.invest.Invest;
import com.philip.fin.invest.InvestManager;

public class LoanManager {
	
	private LoanDAO loanDAO = null;
	
	private static LoanManager manager = null;
	
	private AccountingManager accountingManager = AccountingManager.getInstance();
	private InvestManager investManager = InvestManager.getInstance();
	
	private static final Logger logger = Logger.getLogger(LoanManager.class);
	
	public LoanManager getInstance(){
		if( manager == null ) manager = new LoanManager();
		return manager;
	}
	
	private LoanManager() {
		loanDAO = new LoanDAO();
	}
	
	public ArrayList calculateRepayPlan(Loan_Info loan) {
		ArrayList plan = new ArrayList();
		
		return plan;
	}
	
	public boolean createLoanApply(Loan_Apply_Info loan) {
		boolean b = false;
		
		try {
			loan.setCompany_id(LoanConstants.LOAN_NO_COMPANY_SELECTED_ID);
			loan.getLoan_info().setVerify_status(LoanConstants.LOAN_IS_IN_FILING);
			
			loanDAO.createLoanWithoutCompany(loan);
			b = true;
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		
		return b;
	}
	
	public ArrayList listAllLoansByUser(int user_id) {
		ArrayList al = null;
		try {
			al = loanDAO.getLoanApplyListByUser(user_id);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		
		return al;
	}
	
	public Loan_Apply_Info getLoanApplyById(int loan_id){
		Loan_Apply_Info loan = null;
		try {
			loan = loanDAO.getLoanApply(loan_id);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		
		return loan;
	}
	
	public boolean addCompanyInfo(int loan_id, Company_Info company){
		boolean b = false;
		try {
			b = loanDAO.updateLoan_Company(company, loan_id);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		
		return b;
	}
	
	public ArrayList getLoanApplyByStatus(char status) {
		ArrayList al = null;
		try {
			al = loanDAO.getLoanApplyListByStatus(status);
		} catch (LoanException e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return al;
	}
	
	public void loanSuccessfulRaised(int loan_id) {
		try {
			loanDAO.updateLoanStatus(loan_id, LoanConstants.LOAN_IS_RAISED_SUCCESS);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void payToLender(int loan_id) {
		Loan_Apply_Info loan = null;
		
		loan = manager.getLoanApplyById(loan_id);
		//accounting:
		accountingManager.payToLender(loan.getUser_id(), loan.getAmount());
		
		try {
			loanDAO.updateLoanStatus(loan_id, LoanConstants.LOAN_IS_LEND_TO_APPLIER);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void repayMoney(int loan_id) {
		Loan_Apply_Info loan = null;
		
		loan = manager.getLoanApplyById(loan_id);
		//accounting:
		//calculate amount to be paid:
		//BigDecimal amount = loan.getAmount().multiply(new BigDecimal( 1 + loan.getInterest().doubleValue()/100));
		accountingManager.repayToPlatform(loan.getUser_id(), loan.getAmount(), loan.getAmount().multiply(loan.getInterest()).divide(new BigDecimal(100)));
		
		try {
			loanDAO.updateLoanStatus(loan_id, LoanConstants.LOAN_IS_REPAIDED);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void payMoneyToUsers(int loan_id) {
		HashSet investors = null;
		Invest invest = null;
		Loan_Apply_Info loan = null;
		float interest;

		loan = manager.getLoanApplyById(loan_id);
		interest = loan.getInterest().floatValue();
		
		investors = investManager.getInvestRecords(loan_id);
		Iterator i = investors.iterator();
		
		while(i.hasNext()){
			invest = (Invest)i.next();
						
			//accounting:
			accountingManager.payToUsers(invest.getUser(), invest.getAmount(), invest.getAmount().multiply(new BigDecimal(interest)));
		}
		
		//update status:
		try {
			loanDAO.updateLoanStatus(loan_id, LoanConstants.LOAN_IS_PAID_TO_USERS);
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	
}
