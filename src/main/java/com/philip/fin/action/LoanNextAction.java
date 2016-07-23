package com.philip.fin.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.loan.Company_Info;
import com.philip.fin.loan.LoanManager;
import com.philip.fin.loan.Loan_Apply_Info;
import com.philip.fin.loan.Loan_Info;

public class LoanNextAction implements Action{

	public static final String SUCCESS = "success";
	
	private Company_Info company;
	
	private static final Logger logger = Logger.getLogger(LoanNextAction.class);
	
	private LoanManager loanManager = LoanManager.getInstance();
	
	public String execute() throws Exception {
		logger.debug("start to create loan apply info and go to the next step");
		Loan_Apply_Info loan_apply = new Loan_Apply_Info();
		Loan_Info loan_info = new Loan_Info();
		loan_apply.setLoan_info(loan_info);
		loan_info.setLoan_apply(loan_apply);
		loanManager.createLoanWithCompany(loan_apply, company);
		
		return SUCCESS;
	}

	public Company_Info getCompany(){
		return company;
	}
	
	public void setCompany(Company_Info newCompany){
		company = newCompany;
	}
}
