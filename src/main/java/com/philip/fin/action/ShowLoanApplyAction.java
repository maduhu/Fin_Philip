package com.philip.fin.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.philip.fin.loan.Company_Info;
import com.philip.fin.loan.LoanManager;
import com.philip.fin.loan.Loan_Apply_Info;

public class ShowLoanApplyAction implements Action{
	
	public static final String SUCCESS = "success";
	
	private Company_Info company;
	
	private int loan_id;
	
	private static final Logger logger = Logger.getLogger(ShowLoanApplyAction.class);
	
	private LoanManager loanManager = LoanManager.getInstance();

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("show the application form of loan");
		Loan_Apply_Info loan_apply = null;
		int user_id = 26;
		
		loan_apply = loanManager.getLoanApplyDraft(user_id);
		if(loan_apply!=null){
			company = loanManager.getCompany(loan_apply.getCompany_id());
		}
		
		return SUCCESS;
	}
	
	public Company_Info getCompany(){
		return company;
	}
	
	public void setCompany(Company_Info newCompany){
		company = newCompany;
	}

}
