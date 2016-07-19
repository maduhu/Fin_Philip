package com.philip.fin.loan;

import junit.framework.TestCase;

public class LoanDAOTest extends TestCase{
	private LoanDAO dao = null;
	
	protected void setUp() throws Exception{
		dao = new LoanDAO();	
		dao.setup();
	}
	
	protected void tearDown() throws Exception {
		dao.clearup();
	}
	
	public void testLoan(){
		
	}
}
