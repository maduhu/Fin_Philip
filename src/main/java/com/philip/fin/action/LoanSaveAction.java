package com.philip.fin.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.loan.Company_Info;
import com.philip.fin.loan.LoanManager;
import com.philip.fin.loan.Loan_Apply_Info;
import com.philip.fin.loan.Loan_Info;
import com.philip.fin.user.User;

public class LoanSaveAction implements Action{

	public static final String SUCCESS = "success";
	
	private Company_Info company;
	
	private int loan_id;
	
	private static final Logger logger = Logger.getLogger(LoanSaveAction.class);
	
	private LoanManager loanManager = LoanManager.getInstance();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("start to create loan apply info");
		if (company.getLoan_id() == 0) {
			Loan_Apply_Info loan_apply = new Loan_Apply_Info();
			Loan_Info loan_info = new Loan_Info();
			loan_apply.setLoan_info(loan_info);
			loan_info.setLoan_apply(loan_apply);
			loanManager.createLoanWithCompany(loan_apply, company);
		} else {
			
		}
		return SUCCESS;
	}
	public Company_Info getCompany(){
		return company;
	}
	
	public void setCompany(Company_Info newCompany){
		company = newCompany;
	}
	
	public String getLoad_id() {
		return "" + loan_id;
	}
	
	public void setLoan_id(String new_loan_id){
		loan_id = new Integer(new_loan_id).intValue();
	}
}
